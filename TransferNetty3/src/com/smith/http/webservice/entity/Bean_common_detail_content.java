package com.smith.http.webservice.entity;

public class Bean_common_detail_content {
	private String contet_name;
	private String contet_url;

	public String getContet_name() {
		return contet_name;
	}

	public void setContet_name(String contet_name) {
		this.contet_name = contet_name;
	}

	public String getContet_url() {
		return contet_url;
	}

	public void setContet_url(String contet_url) {
		this.contet_url = contet_url;
	}

	public Bean_common_detail_content(String contet_name, String contet_url) {
		this.contet_name = contet_name;
		this.contet_url = contet_url;
	}

	public Bean_common_detail_content() {
	}

}
