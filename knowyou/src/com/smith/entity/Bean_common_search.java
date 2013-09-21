package com.smith.entity;

public class Bean_common_search {
	private String cover_url;
	private String status;
	private String name;
	private String author;
	private String modify;
	private String action;
	private String summary;
	private String detail_url; 

	public String getCover_url() {
		return cover_url;
	}

	public void setCover_url(String cover_url) {
		this.cover_url = cover_url;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getModify() {
		return modify;
	}

	public void setModify(String modify) {
		this.modify = modify;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDetail_url() {
		return detail_url;
	}

	public void setDetail_url(String detail_url) {
		this.detail_url = detail_url;
	}

	public Bean_common_search(String cover_url, String status, String name, String author, String modify,
			String action, String summary, String detail_url) {
		this.cover_url = cover_url;
		this.status = status;
		this.name = name;
		this.author = author;
		this.modify = modify;
		this.action = action;
		this.summary = summary;
		this.detail_url = detail_url;
	}

	public Bean_common_search() {
	}

}
