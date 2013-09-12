package com.smith.http.webservice.entity;

import java.util.List;

public class Bean_common_detail {
	private int type;
	private String cover_url;
	private String status;
	private String name;
	private String author;
	private String modify;
	private String action;
	private String collection;
	private String summary;
	private String related;
	private String comment;
	private List<Bean_common_detail_content> contents;


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


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


	public String getCollection() {
		return collection;
	}


	public void setCollection(String collection) {
		this.collection = collection;
	}


	public String getSummary() {
		return summary;
	}


	public void setSummary(String summary) {
		this.summary = summary;
	}


	public String getRelated() {
		return related;
	}


	public void setRelated(String related) {
		this.related = related;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public List<Bean_common_detail_content> getContents() {
		return contents;
	}


	public void setContents(List<Bean_common_detail_content> contents) {
		this.contents = contents;
	}


	public Bean_common_detail(int type, String cover_url, String status, String name, String author, String modify,
			String action, String collection, String summary, String related, String comment,
			List<Bean_common_detail_content> contents) {
		this.type = type;
		this.cover_url = cover_url;
		this.status = status;
		this.name = name;
		this.author = author;
		this.modify = modify;
		this.action = action;
		this.collection = collection;
		this.summary = summary;
		this.related = related;
		this.comment = comment;
		this.contents = contents;
	}


	public Bean_common_detail() {
	}
}
