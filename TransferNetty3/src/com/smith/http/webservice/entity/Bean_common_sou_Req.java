package com.smith.http.webservice.entity;

import com.smith.http.webservice.entity.heard.Bean_Request_Head;


public class Bean_common_sou_Req {
	private Bean_Request_Head request_Head;
	private Bean_common_sou common_sou;

	public Bean_Request_Head getRequest_Head() {
		return request_Head;
	}

	public void setRequest_Head(Bean_Request_Head request_Head) {
		this.request_Head = request_Head;
	}

	public Bean_common_sou getCommon_sou() {
		return common_sou;
	}

	public void setCommon_sou(Bean_common_sou common_sou) {
		this.common_sou = common_sou;
	}

	public Bean_common_sou_Req(Bean_Request_Head request_Head, Bean_common_sou common_sou) {
		this.request_Head = request_Head;
		this.common_sou = common_sou;
	}

	public Bean_common_sou_Req() {
	}

}
