package com.lkp.entity;

public class HtmlLevelThree {
	private String name;
	private String content;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public HtmlLevelThree(String name, String content) {
		this.name = name;
		this.content = content;
	}

	public HtmlLevelThree() {
	}
}
