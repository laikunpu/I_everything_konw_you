package com.smith.http.webservice.entity;

import java.util.List;

import com.smith.http.webservice.entity.heard.Bean_Response_Heard;

public class Bean_common_Res {

	private Bean_Response_Heard bean_Heard;

	private boolean isSearch;
	private String searchAction;

	private List<Bean_second_module> bean_second_modules;

	public Bean_Response_Heard getBean_Heard() {
		return bean_Heard;
	}

	public void setBean_Heard(Bean_Response_Heard bean_Heard) {
		this.bean_Heard = bean_Heard;
	}

	public boolean isSearch() {
		return isSearch;
	}

	public void setSearch(boolean isSearch) {
		this.isSearch = isSearch;
	}

	public String getSearchAction() {
		return searchAction;
	}

	public void setSearchAction(String searchAction) {
		this.searchAction = searchAction;
	}

	public List<Bean_second_module> getBean_second_modules() {
		return bean_second_modules;
	}

	public void setBean_second_modules(List<Bean_second_module> bean_second_modules) {
		this.bean_second_modules = bean_second_modules;
	}

	public Bean_common_Res(Bean_Response_Heard bean_Heard, boolean isSearch, String searchAction,
			List<Bean_second_module> bean_second_modules) {
		this.bean_Heard = bean_Heard;
		this.isSearch = isSearch;
		this.searchAction = searchAction;
		this.bean_second_modules = bean_second_modules;
	}

	public Bean_common_Res() {
	}
}