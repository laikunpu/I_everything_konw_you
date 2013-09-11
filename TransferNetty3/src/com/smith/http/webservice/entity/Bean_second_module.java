package com.smith.http.webservice.entity;

import java.util.List;

public class Bean_second_module {
	private String second_module_name;
	private int second_module_num;
	private List<Bean_common> commons;

	public String getSecond_module_name() {
		return second_module_name;
	}

	public void setSecond_module_name(String second_module_name) {
		this.second_module_name = second_module_name;
	}

	public int getSecond_module_num() {
		return second_module_num;
	}

	public void setSecond_module_num(int second_module_num) {
		this.second_module_num = second_module_num;
	}

	public List<Bean_common> getCommons() {
		return commons;
	}

	public void setCommons(List<Bean_common> commons) {
		this.commons = commons;
	}

	public Bean_second_module(String second_module_name, int second_module_num, List<Bean_common> commons) {
		this.second_module_name = second_module_name;
		this.second_module_num = second_module_num;
		this.commons = commons;
	}

	public Bean_second_module() {
	}
}
