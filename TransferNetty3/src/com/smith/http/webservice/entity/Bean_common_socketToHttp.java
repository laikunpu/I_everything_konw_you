package com.smith.http.webservice.entity;

public class Bean_common_socketToHttp {
	private boolean isUseSocket;
	private String parameter;

	public boolean isUseSocket() {
		return isUseSocket;
	}

	public void setUseSocket(boolean isUseSocket) {
		this.isUseSocket = isUseSocket;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public Bean_common_socketToHttp(boolean isUseSocket, String parameter) {
		this.isUseSocket = isUseSocket;
		this.parameter = parameter;
	}

	public Bean_common_socketToHttp() {
	}

}
