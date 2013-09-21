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
import com.smith.http.webservice.entity.Bean_common_page_Res;
import com.smith.http.webservice.entity.Bean_common_search;
import com.smith.http.webservice.entity.Bean_common_socketToHttp;
import com.smith.http.webservice.entity.Bean_commonshowlist;
import com.smith.http.webservice.entity.Bean_commonshowlist_Res;
import com.smith.http.webservice.entity.Bean_second_module;
import com.smith.http.webservice.entity.Bean_socket_requestProperty;
import com.smith.http.webservice.entity.Selenium_info;
import com.smith.http.webservice.global.TN_Constant;
import com.smith.http.webservice.util.TNUrl;
import com.smith.http.webservice.util.TNUtil;

public class CommonService {

	public Bean_second_module getComic_Recommend(String s) {

		Bean_second_module second_module = new Bean_second_module("推荐漫画", 0, null, new ArrayList<Bean_common>(), false,
				-1, -1, null);
		try {
			Document doc = null;
			doc = TNUtil.loadUrl(TNUrl.ONLINE_COMIC, null, TN_Constant.HTML_LEVEL_ONE);

			Elements topHits = doc.select(".topHits");
			Elements manhuas = topHits.get(1).select("a");
			for (int i = 0; i < manhuas.size(); i++) {
				try {
					// System.out.println("漫画名字 = " + manhuas.get(i).attr("title"));
					String name = ""; // 动漫名字
					int type = 0; // 类型 0：漫画 1：动画
					String summary = ""; // 简介
					String cover_url = ""; // 封面url
					String detail_url = ""; // 详情url
					String download_url = ""; // 下载url
					String size = ""; // 大小

					String second_url = "";

					name = manhuas.get(i).attr("title");

					// 二级链接
					second_url = TNUrl.ONLINE_COMIC + manhuas.get(i).attr("href");

					Document doc_second = TNUtil.loadUrl(second_url, null, TN_Constant.HTML_LEVEL_TWO);
					summary = doc_second.select("div.intro").first().text();
					if (summary != null)
						summary = summary.replaceAll(",由爱漫画收集自互联网－爱漫画，让你爱上漫画！", "");
					cover_url = TNUrl.ONLINE_COMIC
							+ doc_second.select("div.bookCover").first().select("img").first().attr("src");
					detail_url = second_url;

					Bean_common comic = new Bean_common(name, type, TNUrl.ACTION_DETAIL, summary, cover_url, detail_url,
							null, null);

					second_module.getCommons().add(comic);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return second_module;
	}

	public Bean_second_module getComic_Toplist(String s) {

		Bean_second_module second_module = new Bean_second_module("漫画排行", 1, null, new ArrayList<Bean_common>(), false,
				-1, -1, null);
		try {
			Document doc = null;
			doc = TNUtil.loadUrl(TNUrl.ONLINE_COMIC, null, TN_Constant.HTML_LEVEL_ONE);

			Elements topHits = doc.select(".topHits");
			Elements manhuas = topHits.get(0).select("a");
			for (int i = 0; i < manhuas.size(); i++) {
				try {
					// System.out.println("漫画名字 = " + manhuas.get(i).attr("title"));
					String name = ""; // 动漫名字
					int type = 0; // 类型 0：漫画 1：动画
					String summary = ""; // 简介
					String cover_url = ""; // 封面url
					String detail_url = ""; // 详情url
					String download_url = ""; // 下载url
					String size = ""; // 大小

					String second_url = "";

					name = manhuas.get(i).attr("title");

					// 二级链接
					second_url = TNUrl.ONLINE_COMIC + manhuas.get(i).attr("href");

					Document doc_second = TNUtil.loadUrl(second_url, null, TN_Constant.HTML_LEVEL_TWO);
					summary = doc_second.select("div.intro").first().text();
					if (summary != null)
						summary = summary.replaceAll(",由爱漫画收集自互联网－爱漫画，让你爱上漫画！", "");
					cover_url = TNUrl.ONLINE_COMIC
							+ doc_second.select("div.bookCover").first().select("img").first().attr("src");
					detail_url = second_url;

					Bean_common comic = new Bean_common(name, type, TNUrl.ACTION_DETAIL, summary, cover_url, detail_url,
							null, null);

					second_module.getCommons().add(comic);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return second_module;
	}

	public Bean_common_detail getComic_Detail(String url) {

		Bean_common_detail detail = null;
		try {
			Document doc = null;
			doc = TNUtil.loadUrl(url, null, TN_Constant.HTML_LEVEL_TWO);

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

			Element bookInfo = doc.select(".bookBG1").get(0).child(1);

			cover_url = TNUrl.ONLINE_COMIC + doc.select("div.bookCover").select("img").attr("src");
			status = bookInfo.child(3).child(0).text();
			name = bookInfo.child(2).text();
			author = bookInfo.child(3).childNode(1).toString().split(" ")[0];
			modify = bookInfo.child(3).childNode(3).toString();
			action = TNUrl.ACTION_DEATIL_NEXT;
			collection = "";
			summary = doc.select("div.intro").first().text();
			related = "";
			comment = "";
			contents = new ArrayList<Bean_common_detail_content>();

			Element element_content = doc.select("#subBookList").first();

			for (int i = 0; i < element_content.children().size(); i++) {
				String contet_name = element_content.children().get(i).select("a").first().attr("title");
				String contet_url = TNUrl.ONLINE_COMIC
						+ element_content.children().get(i).select("a").first().attr("href");
				Bean_common_detail_content detail_content = new Bean_common_detail_content(contet_name, contet_url,
						TNUrl.ACTION_DEATIL_NEXT);
				contents.add(detail_content);
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

	public List<Bean_common_page> getComic_Page(String url) {

		List<Bean_common_page> common_pages = null;
		try {
			Document doc = null;
			doc = TNUtil.loadUrl(url, new Selenium_info(true, "mangaFile"), TN_Constant.HTML_LEVEL_THREE);
			int maximum = doc.select("#pageSelect").select("option").size();
			common_pages = new ArrayList<Bean_common_page>();

			String page_img_url = doc.select("#mangaFile").first().attr("src");

			String pre = page_img_url.substring(0, page_img_url.lastIndexOf("."));
			String after = page_img_url.substring(page_img_url.lastIndexOf("."));

			for (int i = 1; i < maximum + 1; i++) {

				String name = "";
				String page_url = url + "?p=" + i;
				String action = "";

				pre = pre.substring(0, (pre.length() - (i + "").length()));
				pre = pre + i;

				page_img_url = pre + after;
				// page_img_url.replace("_", "%2");

				Bean_socket_requestProperty property = new Bean_socket_requestProperty("Referer", page_url);
				List<Bean_socket_requestProperty> properties = new ArrayList<Bean_socket_requestProperty>();
				properties.add(property);
				Bean_common_socketToHttp socketToHttp = new Bean_common_socketToHttp(true, properties);
				Bean_common_page common_page = new Bean_common_page(page_url, maximum, name, action, socketToHttp,
						page_img_url);
				common_pages.add(common_page);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return common_pages;
	}

	public Bean_second_module getOnline_Comic_list(int num) {
		String url = TNUrl.ONLINE_COMIC_LIST;
		if (num > 1) {
			String preUrl = url.substring(0, url.lastIndexOf("."));
			String midUrl = "_p" + num;
			String postUrl = url.substring(url.lastIndexOf("."), url.length());
			url = preUrl + midUrl + postUrl;
		}
		Bean_second_module second_module = new Bean_second_module("全部漫画", 0, null, new ArrayList<Bean_common>(), true,
				2, -1, TNUrl.ACTION_MOREDATA);
		try {
			Document doc = null;
			doc = TNUtil.loadUrl(url, null, TN_Constant.HTML_LEVEL_ONE);

			Elements bookList = doc.select(".bookList");
			Elements manhuas = bookList.get(0).select("li");

			String max;
			Element pagerFoot = doc.select(".pagerFoot").get(0);
			max = pagerFoot.select("span").get(0).select("strong").get(0).text();
			second_module.setDataNumMax(Integer.parseInt(max));
			for (int i = 0; i < manhuas.size(); i++) {
				try {
					String name = ""; // 动漫名字

					// System.out.println("漫画名字 = " + manhuas.get(i).attr("title"));

					int type = 0; // 类型 0：漫画 1：动画
					String summary = ""; // 简介
					String cover_url = ""; // 封面url
					String detail_url = ""; // 详情url
					String download_url = ""; // 下载url
					String size = ""; // 大小

					String second_url = "";

					name = manhuas.get(i).child(0).attr("title");

					// 二级链接
					second_url = TNUrl.ONLINE_COMIC + manhuas.get(i).child(0).attr("href");

					Document doc_second = TNUtil.loadUrl(second_url, null, TN_Constant.HTML_LEVEL_TWO);
					summary = doc_second.select("div.intro").first().text();
					if (summary != null)
						summary = summary.replaceAll(",由爱漫画收集自互联网－爱漫画，让你爱上漫画！", "");
					cover_url = TNUrl.ONLINE_COMIC
							+ doc_second.select("div.bookCover").first().select("img").first().attr("src");
					detail_url = second_url;

					Bean_common comic = new Bean_common(name, type, TNUrl.ACTION_DETAIL, summary, cover_url, detail_url,
							null, null);

					second_module.getCommons().add(comic);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return second_module;
	}

	public List<Bean_common_search> getOnline_Comic_search(String words) {
		List<Bean_common_search> searchs = null;
		try {
			Document doc = null;
			doc = Jsoup.connect(TNUrl.ONLINE_COMIC_SEARCH + URLEncoder.encode(words, "GBK")).get();

			Elements bookChrList = doc.select(".bookChrList");
			Elements manhuas = bookChrList.get(0).select("li");
			searchs = new ArrayList<Bean_common_search>();
			for (int i = 0; i < manhuas.size(); i++) {

				// System.out.println("漫画名字 = " + manhuas.get(i).attr("title"));

				try {
					String name = "";
					String author;
					String modify;
					String summary = "";
					String cover_url = "";
					String detail_url = "";

					String second_url = "";

					name = manhuas.get(i).select("div.intro").get(0).select("a").get(0).attr("title");
					author = manhuas.get(i).select("div.intro").get(0).select("em").get(2).text();
					modify = manhuas.get(i).select("div.intro").get(0).select("em").get(1).text();
					// 二级链接
					second_url = TNUrl.ONLINE_COMIC
							+ manhuas.get(i).select("div.intro").get(0).select("a").get(0).attr("href");

					Document doc_second = TNUtil.loadUrl(second_url, null, TN_Constant.HTML_LEVEL_TWO);
					summary = doc_second.select("div.intro").first().text();
					if (summary != null)
						summary = summary.replaceAll(",由爱漫画收集自互联网－爱漫画，让你爱上漫画！", "");
					cover_url = TNUrl.ONLINE_COMIC
							+ doc_second.select("div.bookCover").first().select("img").first().attr("src");
					detail_url = second_url;

					Bean_common_search search = new Bean_common_search(cover_url, null, name, author, modify,
							TNUrl.ACTION_DETAIL, summary, detail_url);
					searchs.add(search);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return searchs;
	}
}
