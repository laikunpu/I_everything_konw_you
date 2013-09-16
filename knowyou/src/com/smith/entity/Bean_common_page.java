package com.smith.entity;

import java.util.List;

public class Bean_common_page {
	private String page_url;
	private int maximum;
	private String name;
	private String action;
	private Bean_common_socketToHttp socketToHttp;
	private String page_img_url;

	public String getPage_url() {
		return page_url;
	}

	public void setPage_url(String page_url) {
		this.page_url = page_url;
	}

	public int getMaximum() {
		return maximum;
	}

	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Bean_common_socketToHttp getSocketToHttp() {
		return socketToHttp;
	}

	public void setSocketToHttp(Bean_common_socketToHttp socketToHttp) {
		this.socketToHttp = socketToHttp;
	}

	public String getPage_img_url() {
		return page_img_url;
	}

	public void setPage_img_url(String page_img_url) {
		this.page_img_url = page_img_url;
	}

	public Bean_common_page(String page_url, int maximum, String name, String action,
			Bean_common_socketToHttp socketToHttp, String page_img_url) {
		this.page_url = page_url;
		this.maximum = maximum;
		this.name = name;
		this.action = action;
		this.socketToHttp = socketToHttp;
		this.page_img_url = page_img_url;
	}

	public Bean_common_page() {
	}

}
