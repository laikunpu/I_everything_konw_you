package com.smith.http.webservice.entity;

public class Bean_second_module {
	private String second_module_name;
	private int second_module_num;
	private Bean_common common;

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

	public Bean_common getCommon() {
		return common;
	}

	public void setCommon(Bean_common common) {
		this.common = common;
	}

	public Bean_second_module(String second_module_name, int second_module_num, Bean_common common) {
		this.second_module_name = second_module_name;
		this.second_module_num = second_module_num;
		this.common = common;
	}
	public Bean_second_module() {
	}
}
