package com.smith.http.webservice.entity;

public class Bean_common_moreData {
	private String url;
	private int num;


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}


	public Bean_common_moreData(String url, int num) {
		this.url = url;
		this.num = num;
	}


	public Bean_common_moreData() {
	}

}
