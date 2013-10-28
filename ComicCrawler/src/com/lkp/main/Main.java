package com.lkp.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.lkp.db.DaoImp;
import com.lkp.entity.HtmlLevelOne;
import com.lkp.entity.HtmlLevelThree;
import com.lkp.entity.HtmlLevelThreeNotCollect;
import com.lkp.entity.HtmlLevelTwo;
import com.lkp.entity.Htmlpagecollect;
import com.lkp.entity.Selenium_info;

public class Main {
	public final static String SEEMH_COMIC = "http://www.seemh.com";
	private final static String ORIGINAL_URL = "http://www.seemh.com/list/view.html";
	private static int count = 0;
	private static String BLOCK = "block";
	private static ExecutorService downloadExecutor = Executors.newFixedThreadPool(100);
	private static DaoImp dao = new DaoImp();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DaoImp daoImp = new DaoImp();

		List<HtmlLevelOne> levelOneUrls = getWillCollectUrlsForLevelOne(ORIGINAL_URL);
		System.err.println("levelOneUrls.size()=" + levelOneUrls.size());
		List<HtmlLevelTwo> levelTwoUrls = new ArrayList<HtmlLevelTwo>();
		for (int j = 0; j < levelOneUrls.size(); j++) {
			levelTwoUrls.addAll(getWillCollectUrlsForLevelTwo(levelOneUrls.get(j)));

		}
		System.err.println("levelTwoUrls.size()=" + levelTwoUrls.size());
		for (int k = 0; k < levelTwoUrls.size(); k++) {
			getWillCollectUrlsForLevelThree(levelTwoUrls.get(k));
		}

		// final List<HtmlLevelThreeNotCollect> pages =
		// dao.findAllLevelThreeNotCollect();
		// for (int i = 0; i < pages.size(); i++) {
		// System.err.println("开始第" + i +
		// "页------------------------------------------------------------------>");
		// final int j = i;
		// getPage(pages.get(j).getName());
		// }

	}

	private static List<HtmlLevelOne> getWillCollectUrlsForLevelOne(String url) {
		List<HtmlLevelOne> urls = new ArrayList<HtmlLevelOne>();
		try {
			Document doc = Jsoup.connect(url).get();

			String max = doc.select("div.result-count").first().select("strong").get(1).text();
			int dataNumMax = Integer.parseInt(max);
			// System.out.println(dataNumMax);
			String preUrl;
			String midUrl;
			String postUrl;
			for (int i = 1; i <= dataNumMax; i++) {
				preUrl = url.substring(0, url.lastIndexOf("."));
				midUrl = "_p" + i;
				postUrl = url.substring(url.lastIndexOf("."), url.length());
				String nextUrl = preUrl + midUrl + postUrl;
				// System.out.println((count()) + "  OneUrl :" + nextUrl);
				HtmlLevelOne one = dao.findLevelOne(nextUrl);
				if (null == one) {
					Document docForOne = Jsoup.connect(nextUrl).get();
					one = new HtmlLevelOne(nextUrl, docForOne.toString());
					dao.insertLevelOne(one);
					System.out.println((count()) + "插入未收集的  一级url =" + nextUrl);
				}
				urls.add(one);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return urls;
	}

	private static List<HtmlLevelTwo> getWillCollectUrlsForLevelTwo(HtmlLevelOne one) {
		List<HtmlLevelTwo> urls = new ArrayList<HtmlLevelTwo>();
		try {

			Document doc;
			HtmlLevelTwo html = dao.findLevelTwo(one.getName());
			if (null != html) {
				doc = Jsoup.parse(html.getContent());
			} else {
				doc = Jsoup.connect(one.getName()).get();
			}
			Elements lis = doc.select("#contList").first().select("li");
			String detail_url;
			for (int i = 0; i < lis.size(); i++) {

				Element li = lis.get(i);
				detail_url = SEEMH_COMIC + li.select("a.bcover").first().attr("href");
				HtmlLevelTwo two = dao.findLevelTwo(detail_url);
				if (null == two) {
					Document docForTwo = Jsoup.connect(detail_url).get();
					two = new HtmlLevelTwo(detail_url, docForTwo.toString());
					dao.insertLevelTwo(two);
					System.out.println((count()) + "插入未收集的  二级url =" + detail_url);
				} else {
					System.out.println((count()) + "已收集的  二级url =" + detail_url);
				}
				urls.add(two);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return urls;
	}

	private static void getWillCollectUrlsForLevelThree(HtmlLevelTwo two) {
		try {
			Document doc;
			HtmlLevelTwo html = dao.findLevelTwo(two.getName());
			if (null != html) {
				doc = Jsoup.parse(html.getContent());
			} else {
				doc = Jsoup.connect(two.getName()).get();
			}
			Elements chapters = doc.select("div.chapter.cf.mt16").first().select("div.chapter-list.cf.mt10");
			for (int i = 0; i < chapters.size(); i++) {
				Elements uls = chapters.get(i).select("ul");
				for (int j = uls.size() - 1; j >= 0; j--) {
					Elements lis = uls.get(j).select("li");
					for (int k = 0; k < lis.size(); k++) {
						String contet_url = SEEMH_COMIC + lis.get(k).select("a").first().attr("href");
						insertNotCollect(contet_url);
					}
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void insertNotCollect(String url) {
		if (null == dao.findLevelThreeNotCollect(url)) {
			dao.insertLevelThreeNotCollect(new HtmlLevelThreeNotCollect(url));
			System.out.println((count()) + "插入未收集的三级  url =" + url);
		} else {
			System.out.println((count()) + "已收集的三级  url =" + url);
		}
	}

	private static void insertPageCollect(Htmlpagecollect page) {
		if (null == dao.findPage(page.getName())) {
			dao.insertPage(page);
			System.out.println("插入漫画页url成功：   url=" + page.getName() + "     imageurl=" + page.getImageurl());
		} else {
			System.out.println("已经存在的漫画页url=" + page.getName() + "     imageurl=" + page.getImageurl());
		}
	}

	public static void getPage(String url) {
		try {
			Document doc = null;
			HtmlLevelThree html = dao.findLevelThree(url);
			if (null != html) {
				System.err.println("已收集的漫画     url：" + url);
				return;
			} else {
				System.err.println("开始收集的漫画     url：" + url);
				doc = loadUrlForLevelThreeForDoc(url, new Selenium_info(true, "mangaFile"));
			}

			int maximum = doc.select("#pageSelect").select("option").size();

			String originUrl = doc.select("#mangaFile").first().attr("src");

			String preUrl = originUrl.substring(0, originUrl.lastIndexOf("/") + 1);
			String midUrl = originUrl.substring(originUrl.lastIndexOf("/") + 1, originUrl.lastIndexOf("."));
			String postUrl = originUrl.substring(originUrl.lastIndexOf("."));
			if (midUrl.length() == 1) {
				for (int i = 1; i < maximum; i++) {
					String page_url = url + "?p=" + i;
					midUrl = i + "";
					String imageurl = preUrl + midUrl + postUrl;
					insertPageCollect(new Htmlpagecollect(page_url, imageurl));
				}
			} else if (midUrl.length() == 3) {
				for (int i = 1; i < maximum; i++) {
					String page_url = url + "?p=" + i;
					midUrl = i + "";
					while (midUrl.length() < 3) {
						midUrl = "0" + midUrl;
					}
					String imageurl = preUrl + midUrl + postUrl;
					insertPageCollect(new Htmlpagecollect(page_url, imageurl));
				}
			} else if (midUrl.length() == 6) {
				for (int i = 1; i < maximum; i++) {
					String page_url = url + "?p=" + i;
					String midUrlOne = (i - 1) + "";
					String midUrlTwo = i + "";
					while (midUrlOne.length() < 3) {
						midUrlOne = "0" + midUrlOne;
					}
					while (midUrlTwo.length() < 3) {
						midUrlTwo = "0" + midUrlTwo;
					}
					midUrl = midUrlOne + midUrlTwo;
					String imageurl = preUrl + midUrl + postUrl;
					insertPageCollect(new Htmlpagecollect(page_url, imageurl));
				}
			} else {
				if (midUrl.length() > 6) {
					String midUrlNoCut = midUrl.substring(0, midUrl.length() - 6);
					String midUrlCut = midUrl.substring(midUrl.length() - 6, midUrl.length());
					int cut = -1;
					try {
						cut = Integer.parseInt(midUrlCut);
					} catch (Exception e) {
					}
					if (cut >= 0 && cut < 10) {
						for (int i = 1; i < maximum; i++) {
							String page_url = url + "?p=" + i;
							String midUrlOne = (i - 1) + "";
							String midUrlTwo = i + "";
							while (midUrlOne.length() < 3) {
								midUrlOne = "0" + midUrlOne;
							}
							while (midUrlTwo.length() < 3) {
								midUrlTwo = "0" + midUrlTwo;
							}
							midUrlCut = midUrlOne + midUrlTwo;
							midUrl = midUrlNoCut + midUrlCut;
							String imageurl = preUrl + midUrl + postUrl;
							insertPageCollect(new Htmlpagecollect(page_url, imageurl));
						}
					} else {
						// for (int i = 1; i < maximum + 1; i++) {
						// String page_url = url + "?p=" + i;
						// String page_img_url =
						// loadUrlForLevelThreeForPage(page_url, new
						// Selenium_info(true,
						// "mangaFile"));
						// }
						System.err.println(count() + "需打一个个开火狐收集的漫画     url：" + url);
						return;
					}
				} else {
					System.err.println(count() + "未知收集错误的漫画     url：" + url);
				}

			}

			html = new HtmlLevelThree(url, doc.toString());
			dao.insertLevelThree(html);
			System.err.println(count() + "插入未收集的漫画     url：" + url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}

	}

	public static String loadUrlForLevelThreeForPage(final String url, final Selenium_info selenium_info)
			throws IOException {
		Htmlpagecollect collect = dao.findPage(url);
		if (null != collect) {
			System.out.println("已经存在的漫画页url=" + url + "     imageurl=" + collect.getImageurl());
			return null;
		} else {

		}
		final Document doc;
		String imageurl = null;

		try {
			WebDriver driver = new FirefoxDriver();
			// 设置10秒
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get(url);
			driver.findElement(By.id(selenium_info.getTag())).click();
			String normal_html = driver.getPageSource();
			doc = Jsoup.parse(normal_html);
			imageurl = doc.select("#mangaFile").first().attr("src");
			driver.quit();
			dao.insertPage(new Htmlpagecollect(url, imageurl));
			System.out.println("插入漫画页url成功：   url=" + url + "     imageurl=" + imageurl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("插入漫画页url失败：   url=" + url + "     imageurl=" + imageurl);
			e.printStackTrace();
		}
		return imageurl;
	}

	public static Document loadUrlForLevelThreeForDoc(final String url, final Selenium_info selenium_info)
			throws IOException {
		Document doc = null;
		Htmlpagecollect collect = dao.findPage(url);
		if (null != collect) {
			System.out.println("已经存在的漫画页url=" + url + "     imageurl=" + collect.getImageurl());
			return null;
		} else {

		}

		String imageurl = null;

		try {
			WebDriver driver = new FirefoxDriver();
			// 设置10秒
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get(url);
			driver.findElement(By.id(selenium_info.getTag())).click();
			String normal_html = driver.getPageSource();
			doc = Jsoup.parse(normal_html);
			driver.quit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("插入漫画页url失败：   url=" + url + "     imageurl=" + imageurl);
			e.printStackTrace();
		}
		return doc;
	}

	private static int count() {
		synchronized (BLOCK) {
			count++;
		}
		return count;
	}

}
