package com.smith.http.webservice.entity;

import java.util.List;

import com.smith.http.webservice.entity.heard.Bean_Heard;

public class Bean_common_Res {

	private Bean_Heard bean_Heard;

	private List<Bean_common> commons;

	public Bean_Heard getBean_Heard() {
		return bean_Heard;
	}

	public void setBean_Heard(Bean_Heard bean_Heard) {
		this.bean_Heard = bean_Heard;
	}

	public List<Bean_common> getCommons() {
		return commons;
	}

	public void setCommons(List<Bean_common> commons) {
		this.commons = commons;
	}

	public Bean_common_Res(Bean_Heard bean_Heard, List<Bean_common> commons) {
		this.bean_Heard = bean_Heard;
		this.commons = commons;
	}

	public Bean_common_Res() {
	}
}
