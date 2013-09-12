package com.smith.http.webservice.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.smith.http.webservice.entity.Bean_common;
import com.smith.http.webservice.entity.Bean_common_Res;
import com.smith.http.webservice.entity.Bean_UI_Res;
import com.smith.http.webservice.entity.Bean_Result;
import com.smith.http.webservice.entity.Bean_second_module;
import com.smith.http.webservice.entity.heard.Bean_Heard;
import com.smith.http.webservice.global.Msg_Type;
import com.smith.http.webservice.global.TN_Constant;
import com.smith.http.webservice.util.TNUrl;
import com.smith.http.webservice.util.TNUtil;

public class CommonService {

	public Bean_second_module getComic_Recommend(String s) {

		Bean_second_module second_module = new Bean_second_module("推荐漫画", 1, new ArrayList<Bean_common>());
		try {
			Document doc = null;
			doc = TNUtil.loadUrl(TNUrl.ONLINE_COMIC_RECOMMEND);

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
				second_url = TNUrl.ONLINE_COMIC_RECOMMEND + manhuas.get(i).attr("href");

				Document doc_second = TNUtil.loadUrl(second_url);
				summary = doc_second.select("div.intro").first().text();
				if (summary != null)
					summary = summary.replaceAll(",由爱漫画收集自互联网－爱漫画，让你爱上漫画！", "");
				cover_url = TNUrl.ONLINE_COMIC_RECOMMEND
						+ doc_second.select("div.bookCover").first().select("img").first().attr("src");
				detail_url = second_url;

				Bean_common comic = new Bean_common(name, type, summary, cover_url, detail_url, null, null);

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
			doc = TNUtil.loadUrl(TNUrl.ONLINE_COMIC_RECOMMEND);

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
				second_url = TNUrl.ONLINE_COMIC_RECOMMEND + manhuas.get(i).attr("href");

				Document doc_second = TNUtil.loadUrl(second_url);
				summary = doc_second.select("div.intro").first().text();
				if (summary != null)
					summary = summary.replaceAll(",由爱漫画收集自互联网－爱漫画，让你爱上漫画！", "");
				cover_url = TNUrl.ONLINE_COMIC_RECOMMEND
						+ doc_second.select("div.bookCover").first().select("img").first().attr("src");
				detail_url = second_url;

				Bean_common comic = new Bean_common(name, type, summary, cover_url, detail_url, null, null);

				second_module.getCommons().add(comic);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return second_module;
	}

}
