package com.smith.http.webservice.entity;

public class Selenium_info {
	private boolean isUseSelenium;
	private String tag;

	public boolean isUseSelenium() {
		return isUseSelenium;
	}

	public void setUseSelenium(boolean isUseSelenium) {
		this.isUseSelenium = isUseSelenium;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Selenium_info(boolean isUseSelenium, String tag) {
		this.isUseSelenium = isUseSelenium;
		this.tag = tag;
	}

	public Selenium_info() {
	}

}
