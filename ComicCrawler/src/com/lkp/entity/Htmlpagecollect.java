package com.lkp.entity;

public class Htmlpagecollect {
	private String name;
	private String imageurl;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public Htmlpagecollect(String name, String imageurl) {
		this.name = name;
		this.imageurl = imageurl;
	}

	public Htmlpagecollect() {
	}
}
