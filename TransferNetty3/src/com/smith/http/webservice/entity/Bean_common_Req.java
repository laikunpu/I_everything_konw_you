package com.smith.http.webservice.entity;

import com.smith.http.webservice.entity.heard.Bean_Request_Head;

public class Bean_common_Req {
	private Bean_Request_Head request_Head;
	private Bean_common_url url;

	public Bean_Request_Head getRequest_Head() {
		return request_Head;
	}

	public void setRequest_Head(Bean_Request_Head request_Head) {
		this.request_Head = request_Head;
	}

	public Bean_common_url getUrl() {
		return url;
	}

	public void setUrl(Bean_common_url url) {
		this.url = url;
	}

	public Bean_common_Req(Bean_Request_Head request_Head, Bean_common_url url) {
		this.request_Head = request_Head;
		this.url = url;
	}

	public Bean_common_Req() {
	}

}
