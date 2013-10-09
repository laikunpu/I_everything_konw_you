package com.smith.entity;

import java.util.List;

import com.smith.entity.heard.Bean_Response_Heard;

public class Bean_module_Res {
	private Bean_Response_Heard bean_Heard;

	private List<Bean_module> modules;

	public Bean_Response_Heard getBean_Heard() {
		return bean_Heard;
	}

	public void setBean_Heard(Bean_Response_Heard bean_Heard) {
		this.bean_Heard = bean_Heard;
	}

	public List<Bean_module> getModules() {
		return modules;
	}

	public void setModules(List<Bean_module> modules) {
		this.modules = modules;
	}

	public Bean_module_Res(Bean_Response_Heard bean_Heard, List<Bean_module> modules) {
		this.bean_Heard = bean_Heard;
		this.modules = modules;
	}
	public Bean_module_Res() {
	}
}
