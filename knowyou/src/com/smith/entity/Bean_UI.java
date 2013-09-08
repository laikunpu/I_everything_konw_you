package com.smith.entity;

public class Bean_UI {
	private String name;  //UI模块名字
	private String background_url;  //如有背景图,换上背景图.
	private String background_color;  //UI模块背景颜色
	private String module_url;  //UI模块数据
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBackground_url() {
		return background_url;
	}

	public void setBackground_url(String background_url) {
		this.background_url = background_url;
	}

	public String getBackground_color() {
		return background_color;
	}

	public void setBackground_color(String background_color) {
		this.background_color = background_color;
	}

	public String getModule_url() {
		return module_url;
	}

	public void setModule_url(String module_url) {
		this.module_url = module_url;
	}

	public Bean_UI(String name, String background_url,
			String background_color, String module_url) {
		this.name = name;
		this.background_url = background_url;
		this.background_color = background_color;
		this.module_url = module_url;
	}
	public Bean_UI() {
	}
}
