package com.smith.entity;

public class Bean_Result<T> {
	private String content_type;
	private T t;

	public String getContent_type() {
		return content_type;
	}

	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	public Bean_Result(String content_type, T t) {
		this.content_type = content_type;
		this.t = t;
	}

	public Bean_Result() {
	}
}
