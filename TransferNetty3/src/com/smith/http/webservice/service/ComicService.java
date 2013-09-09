package com.smith.http.webservice.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.smith.http.webservice.entity.Bean_ComicAndMovie;
import com.smith.http.webservice.entity.Bean_ComicAndMovie_Res;
import com.smith.http.webservice.entity.Bean_UI_Res;
import com.smith.http.webservice.entity.Bean_Result;
import com.smith.http.webservice.entity.heard.Bean_Heard;
import com.smith.http.webservice.global.Msg_Type;
import com.smith.http.webservice.global.TN_Constant;
import com.smith.http.webservice.util.TNUrl;

public class ComicService {
	public Bean_Result<Bean_ComicAndMovie_Res> getComic_Recommend(String s) {
		Bean_Result<Bean_ComicAndMovie_Res> result = new Bean_Result<Bean_ComicAndMovie_Res>(TN_Constant.TYPE_JSON,
				null);
		List<Bean_ComicAndMovie> comics = initResData(result);
		
		try {
			Document doc = Jsoup.connect(TNUrl.ONLINE_COMIC_RECOMMEND).get();
			Elements topHits=doc.select(".topHits");
			Elements manhuas=topHits.get(1).select("a");
			for (int i = 0; i < manhuas.size(); i++) {
				System.out.println("漫画名字 = "+manhuas.get(i).attr("title"));
				String name; // 动漫名字
				int type; // 动漫类型 0：漫画 1：动画
				String summary; // 简介
				String cover_url; // 封面url
				String detail_url; // 详情url
				String download_url; // 下载url
				String size; // 大小
				
				name=manhuas.get(i).attr("title");
				
				Bean_ComicAndMovie comic = new Bean_ComicAndMovie("海贼王", 0, "世间的一切,都是为了将你斩尽杀绝.", "http://hzw.com",
						"http://hzwdetail.com", "http://hzwdownload.com", "172.3m");
				comics.add(comic);
				
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return result;
	}

	private List<Bean_ComicAndMovie> initResData(Bean_Result<Bean_ComicAndMovie_Res> result) {

		Bean_ComicAndMovie_Res res = new Bean_ComicAndMovie_Res();
		List<Bean_ComicAndMovie> comics = new ArrayList<Bean_ComicAndMovie>();
		Bean_Heard heard = new Bean_Heard(Msg_Type.CARTOON_CODE);

		res.setBean_Heard(heard);
		res.setComicAndMovies(comics);
		result.setT(res);
		return comics;
	}
}
