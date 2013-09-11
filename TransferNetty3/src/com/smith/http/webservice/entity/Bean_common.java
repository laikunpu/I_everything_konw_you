package com.smith.http.webservice.entity;

public class Bean_common {
	private String name; // 动漫名字
	private int type; // 动漫类型 0：漫画 1：动画
	private String summary; // 简介
	private String cover_url; // 封面url
	private String detail_url; // 详情url
	private String download_url; // 下载url
	private String size; // 大小

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getCover_url() {
		return cover_url;
	}

	public void setCover_url(String cover_url) {
		this.cover_url = cover_url;
	}

	public String getDetail_url() {
		return detail_url;
	}

	public void setDetail_url(String detail_url) {
		this.detail_url = detail_url;
	}

	public String getDownload_url() {
		return download_url;
	}

	public void setDownload_url(String download_url) {
		this.download_url = download_url;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Bean_common(String name, int type, String summary, String cover_url, String detail_url, String download_url,
			String size) {
		this.name = name;
		this.type = type;
		this.summary = summary;
		this.cover_url = cover_url;
		this.detail_url = detail_url;
		this.download_url = download_url;
		this.size = size;
	}

	public Bean_common() {
	}
}
