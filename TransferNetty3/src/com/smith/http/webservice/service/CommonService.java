package com.smith.http.webservice.service;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.smith.http.webservice.entity.Bean_common;
import com.smith.http.webservice.entity.Bean_common_detail;
import com.smith.http.webservice.entity.Bean_common_detail_content;
import com.smith.http.webservice.entity.Bean_common_detail_online;
import com.smith.http.webservice.entity.Bean_common_page;
import com.smith.http.webservice.entity.Bean_common_socketToHttp;
import com.smith.http.webservice.entity.Bean_module;
import com.smith.http.webservice.entity.Bean_socket_requestProperty;
import com.smith.http.webservice.entity.Selenium_info;
import com.smith.http.webservice.util.TNUrl;
import com.smith.http.webservice.util.TNUtil;

public class CommonService {

	public Bean_module getMedule(String url, String module_name, int module_num) {
		Bean_module module = null;
		try {
			Document doc = TNUtil.loadUrlForLevelOne(url);
			List<Bean_common> commons = getCommons(url, 1);
			String max = doc.select("div.result-count").first().select("strong").get(1).text();
			int dataNumMax = Integer.parseInt(max);
			boolean isMoreData = false;
			if (dataNumMax > 1) {
				isMoreData = true;
			}
			module = new Bean_module(module_name, module_num, commons, url, isMoreData, 2, dataNumMax,
					TNUrl.ACTION_SEEMH_MOREDATA);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return module;
	}

	public List<Bean_common> getCommons(String url, int num) {
		List<Bean_common> commons = null;
		try {
			String preUrl = url.substring(0, url.lastIndexOf("."));
			String midUrl = "_p" + num;
			String postUrl = url.substring(url.lastIndexOf("."), url.length());
			url = preUrl + midUrl + postUrl;
			Document doc = TNUtil.loadUrlForLevelOne(url);
			commons = new ArrayList<Bean_common>();
			String name; // 动漫名字
			int type; // 动漫类型 0：漫画 1：动画
			String detail_action;
			String summary; // 简介
			String cover_url; // 封面url
			String update_title;
			String update_time;
			String detail_url;

			Elements lis = doc.select("#contList").first().select("li");
			for (int i = 0; i < lis.size(); i++) {

				Element li = lis.get(i);
				name = li.select("a.bcover").attr("title");
				type = 0;
				detail_action = TNUrl.ACTION_SEEMH_DETAIL;
				summary = "";
				cover_url = li.select("a.bcover").select("img").first().attr("data-src");
				update_title = li.select("span.tt").first().text();
				update_time = li.select("span.updateon").first().childNode(0).toString();
				detail_url = TNUrl.SEEMH_COMIC + li.select("a.bcover").first().attr("href");
				Bean_common common = new Bean_common(name, type, detail_action, summary, cover_url, update_title,
						update_time, detail_url);
				commons.add(common);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return commons;
	}

	public Bean_common_detail getDetail(String url) {

		Bean_common_detail detail = null;
		try {
			Document doc = null;
			doc = TNUtil.loadUrlForLevelTwo(url);

			int type = 0;
			String cover_url;
			String status;
			String name;
			String author;
			String modify;
			String action;
			String collection;
			String summary;
			String related;
			String comment;
			List<Bean_common_detail_content> contents = null;

			Element bookInfo = doc.select("div.book-cont.cf").first();

			cover_url = doc.select("p.hcover").select("img").attr("src");
			status = bookInfo.select("li.status").first().select("span.red").first().text();
			name = bookInfo.select("div.book-title").first().child(0).text();
			author = bookInfo.select("ul.detail-list.cf").select("li").get(1).select("span").get(1).text();
			modify = "最近更新  :  " + bookInfo.select("li.status").first().select("span.red").get(1).text();
			action = TNUrl.ACTION_SEEMH_DETAI_NEXT;
			collection = "";
			summary = bookInfo.select("#intro-cut").first().text();
			related = "";
			comment = "";
			contents = new ArrayList<Bean_common_detail_content>();

			Elements chapters = doc.select("div.chapter.cf.mt16").first().select("div.chapter-list.cf.mt10");
			for (int i = 0; i < chapters.size(); i++) {
				Elements uls = chapters.get(i).select("ul");
				for (int j = uls.size() - 1; j >= 0; j--) {
					Elements lis = uls.get(j).select("li");
					for (int k = 0; k < lis.size(); k++) {
						String contet_name = lis.get(k).select("a").first().attr("title");
						String contet_url = TNUrl.SEEMH_COMIC + lis.get(k).select("a").first().attr("href");
						Bean_common_detail_content detail_content = new Bean_common_detail_content(contet_name,
								contet_url, action);
						contents.add(detail_content);
					}
				}

			}

			Bean_common_detail_online online = new Bean_common_detail_online("在线观看", 1, null);
			detail = new Bean_common_detail(type, cover_url, status, name, author, modify, action, collection, summary,
					related, comment, online, null, contents);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return detail;
	}

	public List<Bean_common_page> getPage(String url) {

		boolean isCollect = false;
		List<Bean_common_page> common_pages = null;
		try {
			if (TNUtil.urltable.size() > 1000) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.err.println("收集列表已大于1000           列表大小：" + TNUtil.urltable.size());
				return null;
			}
			if (null != TNUtil.urltable.get(url)) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.err.println("列表大小：" + TNUtil.urltable.size());
				System.err.println("正在收集：" + url);

				return null;
			}
			isCollect = true;
			TNUtil.urltable.put(url, url);
			Document doc = null;
			doc = TNUtil.loadUrlForLevelThree(url);
			int maximum = doc.select("#pageSelect").select("option").size();
			common_pages = new ArrayList<Bean_common_page>();

			String originUrl = doc.select("#mangaFile").first().attr("src");

			String preUrl = originUrl.substring(0, originUrl.lastIndexOf("/") + 1);
			String midUrl = originUrl.substring(originUrl.lastIndexOf("/") + 1, originUrl.lastIndexOf("."));
			String postUrl = originUrl.substring(originUrl.lastIndexOf("."));
			if (midUrl.length() == 1) {
				for (int i = 1; i < maximum; i++) {
					String page_url = url + "?p=" + i;
					midUrl = i + "";
					String page_img_url = preUrl + midUrl + postUrl;
					collectPage(common_pages, maximum, page_img_url, page_url);
				}
			} else if (midUrl.length() == 3) {
				for (int i = 1; i < maximum; i++) {
					String page_url = url + "?p=" + i;
					midUrl = i + "";
					while (midUrl.length() < 3) {
						midUrl = "0" + midUrl;
					}
					String page_img_url = preUrl + midUrl + postUrl;
					collectPage(common_pages, maximum, page_img_url, page_url);
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
					String page_img_url = preUrl + midUrl + postUrl;
					collectPage(common_pages, maximum, page_img_url, page_url);
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
							String page_img_url = preUrl + midUrl + postUrl;

							collectPage(common_pages, maximum, page_img_url, page_url);
						}
					} else {

						for (int i = 1; i < maximum + 1; i++) {

							String page_url = url + "?p=" + i;
							String page_img_url = TNUtil.loadUrlForLevelThreeForPage(page_url, new Selenium_info(true,
									"mangaFile"));

							collectPage(common_pages, maximum, page_img_url, page_url);

						}
					}
				} else {
					System.err.println("未知收集错误的漫画     url：" + url);
				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (isCollect) {
				System.err.println("已经收集：" + url);
				TNUtil.urltable.remove(url);
				System.err.println("还剩任务：" + TNUtil.urltable.size());
			}
		}

		return common_pages;
	}

	private void collectPage(List<Bean_common_page> common_pages, int maximum, String page_img_url, String page_url) {
		Bean_socket_requestProperty property = new Bean_socket_requestProperty("Referer", page_url);
		List<Bean_socket_requestProperty> properties = new ArrayList<Bean_socket_requestProperty>();
		properties.add(property);
		Bean_common_socketToHttp socketToHttp = new Bean_common_socketToHttp(true, properties);
		Bean_common_page common_page = new Bean_common_page(page_url, maximum, "", "", socketToHttp, page_img_url);
		common_pages.add(common_page);
	}

	public List<Bean_common> getSearch(String words) {
		List<Bean_common> commons = null;
		try {
			Document doc = null;
			doc = Jsoup.connect(TNUrl.SEEMH_SEARCH + URLEncoder.encode(words, "GBK") + ".html").get();
			System.out.println("搜索请求" + TNUrl.SEEMH_SEARCH + URLEncoder.encode(words, "GBK") + ".html");
			Element bookList = doc.select("div.book-result").first();
			Elements bookSearchs = bookList.select("li");
			commons = new ArrayList<Bean_common>();

			String name; // 动漫名字
			int type; // 动漫类型 0：漫画 1：动画
			String detail_action;
			String summary; // 简介
			String cover_url; // 封面url
			String update_title;
			String update_time;
			String detail_url;

			Elements lis = bookSearchs.select("li");
			for (int i = 0; i < lis.size(); i++) {

				Element li = lis.get(i);
				name = li.select("a.bcover").attr("title");
				type = 0;
				detail_action = TNUrl.ACTION_SEEMH_DETAIL;
				summary = "";
				cover_url = li.select("a.bcover").select("img").first().attr("src");
				update_title = li.select("span.tt").first().text();
				update_time = li.select("span.updateon").first().childNode(0).toString();
				detail_url = TNUrl.SEEMH_COMIC + li.select("a.bcover").first().attr("href");
				Bean_common common = new Bean_common(name, type, detail_action, summary, cover_url, update_title,
						update_time, detail_url);
				commons.add(common);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return commons;
	}
}
