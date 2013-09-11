package com.smith.entity;

import java.util.List;

import com.smith.entity.heard.Bean_Heard;


public class Bean_UI_Res {

	private Bean_Heard bean_Heard;

	private List<Bean_UI> uis;

	public Bean_Heard getBean_Heard() {
		return bean_Heard;
	}

	public void setBean_Heard(Bean_Heard bean_Heard) {
		this.bean_Heard = bean_Heard;
	}

	public List<Bean_UI> getUis() {
		return uis;
	}

	public void setUis(List<Bean_UI> uis) {
		this.uis = uis;
	}

	public Bean_UI_Res(Bean_Heard bean_Heard, List<Bean_UI> uis) {
		this.bean_Heard = bean_Heard;
		this.uis = uis;
	}

	public Bean_UI_Res() {
	}
}
