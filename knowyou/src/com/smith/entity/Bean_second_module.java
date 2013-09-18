package com.smith.entity;

import java.util.List;


public class Bean_second_module {
	private String second_module_name;
	private int second_module_num;
	private String adUrl;
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


	public String getAdUrl() {
		return adUrl;
	}


	public void setAdUrl(String adUrl) {
		this.adUrl = adUrl;
	}


	public List<Bean_common> getCommons() {
		return commons;
	}


	public void setCommons(List<Bean_common> commons) {
		this.commons = commons;
	}


	public Bean_second_module(String second_module_name, int second_module_num, String adUrl, List<Bean_common> commons) {
		super();
		this.second_module_name = second_module_name;
		this.second_module_num = second_module_num;
		this.adUrl = adUrl;
		this.commons = commons;
	}


	public Bean_second_module() {
	}
}
