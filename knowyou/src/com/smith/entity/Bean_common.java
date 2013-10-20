package com.smith.entity;

public class Bean_common {
	private String name; // 动漫名字
	private int type; // 动漫类型 0：漫画 1：动画
	private String detail_action;
	private String summary; // 简介
	private String cover_url; // 封面url
	private String update_title;
	private String update_time;
	private String detail_url;

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

	public String getDetail_action() {
		return detail_action;
	}

	public void setDetail_action(String detail_action) {
		this.detail_action = detail_action;
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

	public String getUpdate_title() {
		return update_title;
	}

	public void setUpdate_title(String update_title) {
		this.update_title = update_title;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public String getDetail_url() {
		return detail_url;
	}

	public void setDetail_url(String detail_url) {
		this.detail_url = detail_url;
	}

	public Bean_common(String name, int type, String detail_action, String summary, String cover_url,
			String update_title, String update_time, String detail_url) {
		this.name = name;
		this.type = type;
		this.detail_action = detail_action;
		this.summary = summary;
		this.cover_url = cover_url;
		this.update_title = update_title;
		this.update_time = update_time;
		this.detail_url = detail_url;
	}

	public Bean_common() {
	}
}
