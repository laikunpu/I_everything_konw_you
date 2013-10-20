package com.smith.entity;

import java.util.List;

public class Bean_module {
	private String module_name;
	private int module_num;
	private List<Bean_common> commons;
	private String url;
	private boolean isMoreData;
	private int dataNum;
	private int dataNumMax;
	private String moreData_action;

	public String getModule_name() {
		return module_name;
	}

	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}

	public int getModule_num() {
		return module_num;
	}

	public void setModule_num(int module_num) {
		this.module_num = module_num;
	}

	public List<Bean_common> getCommons() {
		return commons;
	}

	public void setCommons(List<Bean_common> commons) {
		this.commons = commons;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isMoreData() {
		return isMoreData;
	}

	public void setMoreData(boolean isMoreData) {
		this.isMoreData = isMoreData;
	}

	public int getDataNum() {
		return dataNum;
	}

	public void setDataNum(int dataNum) {
		this.dataNum = dataNum;
	}

	public int getDataNumMax() {
		return dataNumMax;
	}

	public void setDataNumMax(int dataNumMax) {
		this.dataNumMax = dataNumMax;
	}

	public String getMoreData_action() {
		return moreData_action;
	}

	public void setMoreData_action(String moreData_action) {
		this.moreData_action = moreData_action;
	}

	public Bean_module(String module_name, int module_num, List<Bean_common> commons, String url, boolean isMoreData,
			int dataNum, int dataNumMax, String moreData_action) {
		this.module_name = module_name;
		this.module_num = module_num;
		this.commons = commons;
		this.url = url;
		this.isMoreData = isMoreData;
		this.dataNum = dataNum;
		this.dataNumMax = dataNumMax;
		this.moreData_action = moreData_action;
	}

	public Bean_module() {
	}
}
