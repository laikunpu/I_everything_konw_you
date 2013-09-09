package com.smith.entity;

import java.util.List;

import com.smith.entity.heard.Bean_Heard;

public class Bean_ComicAndMovie_Res {

	private Bean_Heard bean_Heard;

	private List<Bean_ComicAndMovie> comicAndMovies;

	public Bean_Heard getBean_Heard() {
		return bean_Heard;
	}

	public void setBean_Heard(Bean_Heard bean_Heard) {
		this.bean_Heard = bean_Heard;
	}

	public List<Bean_ComicAndMovie> getComicAndMovies() {
		return comicAndMovies;
	}

	public void setComicAndMovies(List<Bean_ComicAndMovie> comicAndMovies) {
		this.comicAndMovies = comicAndMovies;
	}

	public Bean_ComicAndMovie_Res(Bean_Heard bean_Heard, List<Bean_ComicAndMovie> comicAndMovies) {
		this.bean_Heard = bean_Heard;
		this.comicAndMovies = comicAndMovies;
	}

	public Bean_ComicAndMovie_Res() {
	}
}
