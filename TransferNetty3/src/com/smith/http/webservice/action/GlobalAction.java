package com.smith.http.webservice.action;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.smith.http.webservice.entity.Bean_Result;
import com.smith.http.webservice.entity.Bean_module;
import com.smith.http.webservice.entity.Bean_module_Res;
import com.smith.http.webservice.entity.heard.Bean_Response_Heard;
import com.smith.http.webservice.global.Msg_Type;
import com.smith.http.webservice.service.CommonService;
import com.smith.http.webservice.util.TNUrl;

public class GlobalAction {
	private CommonService service = new CommonService();
	private Gson gson = new Gson();

	public Bean_Result getModule(String s) {
		Bean_Response_Heard type = new Bean_Response_Heard(Msg_Type.UI_CODE);
		Bean_module module1 = new Bean_module("全部", 0, null, service.getMedule("http://www.seemh.com/list/"), true, 1, 249, "");
		Bean_module module2 = new Bean_module("热血", 0, null, service.getMedule("http://www.seemh.com/list/rexue/"), true, 1, 249, "");
		List<Bean_module> modules = new ArrayList<Bean_module>();
		modules.add(module1);
		modules.add(module2);
		Bean_module_Res module_Res = new Bean_module_Res(type, modules);
		Bean_Result<Bean_module_Res> result = new Bean_Result<Bean_module_Res>("text/json", module_Res);
		return result;
	}
}
