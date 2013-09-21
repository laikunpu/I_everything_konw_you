package com.smith.http.webservice.entity;

import com.smith.http.webservice.entity.heard.Bean_Request_Head;

public class Bean_common_moreData_Req<T> {
	private Bean_Request_Head request_Head;
	private Bean_common_moreData moreData;

	public Bean_Request_Head getRequest_Head() {
		return request_Head;
	}

	public void setRequest_Head(Bean_Request_Head request_Head) {
		this.request_Head = request_Head;
	}

	public Bean_common_moreData getMoreData() {
		return moreData;
	}

	public void setMoreData(Bean_common_moreData moreData) {
		this.moreData = moreData;
	}

	public Bean_common_moreData_Req(Bean_Request_Head request_Head, Bean_common_moreData moreData) {
		this.request_Head = request_Head;
		this.moreData = moreData;
	}

	public Bean_common_moreData_Req() {
	}

}
