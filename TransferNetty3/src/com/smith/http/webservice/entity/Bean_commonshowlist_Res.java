package com.smith.http.webservice.entity;

import java.util.List;

import com.smith.http.webservice.entity.heard.Bean_Heard;

public class Bean_commonshowlist_Res {
	private Bean_Heard bean_Heard;

	private List<Bean_commonshowlist> bean_commonshowlists;

	public Bean_Heard getBean_Heard() {
		return bean_Heard;
	}

	public void setBean_Heard(Bean_Heard bean_Heard) {
		this.bean_Heard = bean_Heard;
	}

	public List<Bean_commonshowlist> getBean_commonshowlists() {
		return bean_commonshowlists;
	}

	public void setBean_commonshowlists(List<Bean_commonshowlist> bean_commonshowlists) {
		this.bean_commonshowlists = bean_commonshowlists;
	}

	public Bean_commonshowlist_Res(Bean_Heard bean_Heard, List<Bean_commonshowlist> bean_commonshowlists) {
//		this.bean_Heard = bean_Heard;
		this.bean_commonshowlists = bean_commonshowlists;
	}

	public Bean_commonshowlist_Res() {
	}
}
