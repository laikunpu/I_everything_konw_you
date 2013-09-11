package com.smith.entity;

import java.util.List;

import com.smith.entity.heard.Bean_Heard;


public class Bean_common_Res {

	private Bean_Heard bean_Heard;

	private List<Bean_second_module> bean_second_modules;

	public Bean_Heard getBean_Heard() {
		return bean_Heard;
	}

	public void setBean_Heard(Bean_Heard bean_Heard) {
		this.bean_Heard = bean_Heard;
	}

	public List<Bean_second_module> getBean_second_modules() {
		return bean_second_modules;
	}

	public void setBean_second_modules(List<Bean_second_module> bean_second_modules) {
		this.bean_second_modules = bean_second_modules;
	}

	public Bean_common_Res(Bean_Heard bean_Heard, List<Bean_second_module> bean_second_modules) {
		this.bean_Heard = bean_Heard;
		this.bean_second_modules = bean_second_modules;
	}

	public Bean_common_Res() {
	}
}
