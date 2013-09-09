package com.smith.http.webservice.entity;

import java.util.List;

public class Bean_Res<T> {

	private Bean_Heard bean_Heard;

	private List<T> t;

	public Bean_Heard getBean_Heard() {
		return bean_Heard;
	}

	public void setBean_Heard(Bean_Heard bean_Heard) {
		this.bean_Heard = bean_Heard;
	}

	public List<T> getT() {
		return t;
	}

	public void setT(List<T> t) {
		this.t = t;
	}

	public Bean_Res(Bean_Heard bean_Heard, List<T> t) {
		this.bean_Heard = bean_Heard;
		this.t = t;
	}

	public Bean_Res() {
	}
}
