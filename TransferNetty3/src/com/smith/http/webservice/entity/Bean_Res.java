package com.smith.http.webservice.entity;

import java.util.List;

public class Bean_Res<T> {

	private Bean_Type bean_Type;

	private List<T> t;


	public Bean_Type getBean_Type() {
		return bean_Type;
	}

	public void setBean_Type(Bean_Type bean_Type) {
		this.bean_Type = bean_Type;
	}

	public List<T> getT() {
		return t;
	}

	public void setT(List<T> t) {
		this.t = t;
	}

	public Bean_Res(Bean_Type bean_Type, List<T> t) {
		this.bean_Type = bean_Type;
		this.t = t;
	}

	public Bean_Res() {
	}
}
