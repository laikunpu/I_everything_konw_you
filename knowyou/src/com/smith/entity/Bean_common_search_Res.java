package com.smith.entity;

import java.util.List;

import com.smith.entity.heard.Bean_Response_Heard;

public class Bean_common_search_Res {

	private Bean_Response_Heard bean_Heard;

	private List<Bean_common_search> bean_common_searchs;

	public Bean_Response_Heard getBean_Heard() {
		return bean_Heard;
	}

	public void setBean_Heard(Bean_Response_Heard bean_Heard) {
		this.bean_Heard = bean_Heard;
	}

	public List<Bean_common_search> getBean_common_searchs() {
		return bean_common_searchs;
	}

	public void setBean_common_searchs(List<Bean_common_search> bean_common_searchs) {
		this.bean_common_searchs = bean_common_searchs;
	}

	public Bean_common_search_Res(Bean_Response_Heard bean_Heard, List<Bean_common_search> bean_common_searchs) {
		this.bean_Heard = bean_Heard;
		this.bean_common_searchs = bean_common_searchs;
	}

	public Bean_common_search_Res() {
	}
}
