package com.smith.http.webservice.entity;

public class Bean_common_detail_online {
	private String onlineText;
	private int onlineType;
	private String onlineUrl;

	public String getOnlineText() {
		return onlineText;
	}

	public void setOnlineText(String onlineText) {
		this.onlineText = onlineText;
	}

	public int getOnlineType() {
		return onlineType;
	}

	public void setOnlineType(int onlineType) {
		this.onlineType = onlineType;
	}

	public String getOnlineUrl() {
		return onlineUrl;
	}

	public void setOnlineUrl(String onlineUrl) {
		this.onlineUrl = onlineUrl;
	}

	public Bean_common_detail_online(String onlineText, int onlineType, String onlineUrl) {
		this.onlineText = onlineText;
		this.onlineType = onlineType;
		this.onlineUrl = onlineUrl;
	}

	public Bean_common_detail_online() {
	}

}
