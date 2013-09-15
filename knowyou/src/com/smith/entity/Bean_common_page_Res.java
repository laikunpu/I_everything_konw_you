package com.smith.entity;

import java.util.List;

import com.smith.entity.heard.Bean_Heard;


public class Bean_common_page_Res {
	private Bean_Heard bean_Heard;

	private List<Bean_common_page> bean_common_pages;

	public Bean_Heard getBean_Heard() {
		return bean_Heard;
	}

	public void setBean_Heard(Bean_Heard bean_Heard) {
		this.bean_Heard = bean_Heard;
	}

	public List<Bean_common_page> getBean_common_pages() {
		return bean_common_pages;
	}

	public void setBean_common_pages(List<Bean_common_page> bean_common_pages) {
		this.bean_common_pages = bean_common_pages;
	}

	public Bean_common_page_Res(Bean_Heard bean_Heard, List<Bean_common_page> bean_common_pages) {
		this.bean_Heard = bean_Heard;
		this.bean_common_pages = bean_common_pages;
	}

	public Bean_common_page_Res() {
	}

}
