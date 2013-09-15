package com.smith.http.webservice.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.smith.http.webservice.entity.Bean_common;
import com.smith.http.webservice.entity.Bean_common_detail;
import com.smith.http.webservice.entity.Bean_common_detail_content;
import com.smith.http.webservice.entity.Bean_common_page;
import com.smith.http.webservice.entity.Bean_common_page_Res;
import com.smith.http.webservice.entity.Bean_common_socketToHttp;
import com.smith.http.webservice.entity.Bean_commonshowlist;
import com.smith.http.webservice.entity.Bean_commonshowlist_Res;
import com.smith.http.webservice.entity.Bean_second_module;
import com.smith.http.webservice.util.TNUrl;
import com.smith.http.webservice.util.TNUtil;

public class CommonService {

	public Bean_second_module getComic_Recommend(String s) {

		Bean_second_module second_module = new Bean_second_module("推荐漫画", 0, new ArrayList<Bean_common>());
		try {
			Document doc = null;
			doc = TNUtil.loadUrl(TNUrl.ONLINE_COMIC, TNUtil.dao);

			Elements topHits = doc.select(".topHits");
			Elements manhuas = topHits.get(1).select("a");
			for (int i = 0; i < manhuas.size(); i++) {
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

				Document doc_second = TNUtil.loadUrl(second_url, TNUtil.dao);
				summary = doc_second.select("div.intro").first().text();
				if (summary != null)
					summary = summary.replaceAll(",由爱漫画收集自互联网－爱漫画，让你爱上漫画！", "");
				cover_url = TNUrl.ONLINE_COMIC
						+ doc_second.select("div.bookCover").first().select("img").first().attr("src");
				detail_url = second_url;

				Bean_common comic = new Bean_common(name, type, TNUrl.ACTION_DETAIL, summary, cover_url, detail_url,
						null, null);

				second_module.getCommons().add(comic);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return second_module;
	}

	public Bean_second_module getComic_Toplist(String s) {

		Bean_second_module second_module = new Bean_second_module("漫画排行", 1, new ArrayList<Bean_common>());
		try {
			Document doc = null;
			doc = TNUtil.loadUrl(TNUrl.ONLINE_COMIC, TNUtil.dao);

			Elements topHits = doc.select(".topHits");
			Elements manhuas = topHits.get(0).select("a");
			for (int i = 0; i < manhuas.size(); i++) {
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

				Document doc_second = TNUtil.loadUrl(second_url, TNUtil.dao);
				summary = doc_second.select("div.intro").first().text();
				if (summary != null)
					summary = summary.replaceAll(",由爱漫画收集自互联网－爱漫画，让你爱上漫画！", "");
				cover_url = TNUrl.ONLINE_COMIC
						+ doc_second.select("div.bookCover").first().select("img").first().attr("src");
				detail_url = second_url;

				Bean_common comic = new Bean_common(name, type, TNUrl.ACTION_DETAIL, summary, cover_url, detail_url,
						null, null);

				second_module.getCommons().add(comic);

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
			doc = TNUtil.loadUrl(url, TNUtil.dao);

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

			detail = new Bean_common_detail(type, cover_url, status, name, author, modify, action, collection, summary,
					related, comment, contents);

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
			doc = TNUtil.loadUrl(url, TNUtil.dao);
			int maximum = doc.select("#pageSelect").select("option").size();
			common_pages = new ArrayList<Bean_common_page>();


			for (int i = 1; i < maximum + 1; i++) {

				String name = "";
				String page_url = url+"?p=" + i;
				String condition = "/Files/Images/";
				String action = "";
//				Bean_common_socketToHttp socketToHttp = new Bean_common_socketToHttp(true, "Referer: " + page_url + "\r\n");
				Bean_common_socketToHttp socketToHttp = new Bean_common_socketToHttp(true,  page_url );
				Bean_common_page common_page = new Bean_common_page(page_url, maximum, name, condition, action,
						socketToHttp);
				common_pages.add(common_page);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return common_pages;
	}
}
