package com.smith.http.webservice.entity;

import java.util.List;

import com.smith.http.webservice.entity.heard.Bean_Response_Heard;

public class Bean_common_Res {

	private Bean_Response_Heard bean_Heard;
	private List<Bean_common> bean_commons;

	public Bean_Response_Heard getBean_Heard() {
		return bean_Heard;
	}

	public void setBean_Heard(Bean_Response_Heard bean_Heard) {
		this.bean_Heard = bean_Heard;
	}

	public List<Bean_common> getBean_commons() {
		return bean_commons;
	}

	public void setBean_commons(List<Bean_common> bean_commons) {
		this.bean_commons = bean_commons;
	}

	public Bean_common_Res(Bean_Response_Heard bean_Heard, List<Bean_common> bean_commons) {
		this.bean_Heard = bean_Heard;
		this.bean_commons = bean_commons;
	}

	public Bean_common_Res() {
	}
}