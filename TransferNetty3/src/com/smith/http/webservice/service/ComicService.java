package com.smith.http.webservice.service;

import java.util.ArrayList;
import java.util.List;

import com.smith.http.webservice.entity.Bean_ComicAndMovie;
import com.smith.http.webservice.entity.Bean_ComicAndMovie_Res;
import com.smith.http.webservice.entity.Bean_UI_Res;
import com.smith.http.webservice.entity.Bean_Result;
import com.smith.http.webservice.entity.heard.Bean_Heard;
import com.smith.http.webservice.global.Msg_Type;
import com.smith.http.webservice.global.TN_Constant;

public class ComicService {
	public Bean_Result<Bean_ComicAndMovie_Res> getComic_Recommend() {
		Bean_Result<Bean_ComicAndMovie_Res> result = new Bean_Result<Bean_ComicAndMovie_Res>(TN_Constant.TYPE_JSON,
				null);
		List<Bean_ComicAndMovie> comics = initResData(result);
		Bean_ComicAndMovie comic = new Bean_ComicAndMovie("海贼王", 0, "世间的一切,都是为了将你斩尽杀绝.", "http://hzw.com",
				"http://hzwdetail.com", "http://hzwdownload.com", "172.3m");
		comics.add(comic);
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
