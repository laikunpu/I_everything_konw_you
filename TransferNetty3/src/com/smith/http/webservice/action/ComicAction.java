package com.smith.http.webservice.action;

import com.smith.http.webservice.entity.Bean_Result;
import com.smith.http.webservice.service.ComicService;

public class ComicAction {
	private ComicService service = new ComicService();

	public Bean_Result getComic_Recommend(String json) {

		return service.getComic_Recommend(json);
	}
}
