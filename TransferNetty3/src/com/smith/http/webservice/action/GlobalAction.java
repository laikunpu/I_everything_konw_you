package com.smith.http.webservice.action;

import java.util.ArrayList;
import java.util.List;
import com.smith.http.webservice.entity.Bean_Result;
import com.smith.http.webservice.entity.Bean_UI_Res;
import com.smith.http.webservice.entity.Bean_UI;
import com.smith.http.webservice.entity.heard.Bean_Heard;
import com.smith.http.webservice.global.Msg_Type;

public class GlobalAction {

	public Bean_Result getUI_Module(String s) {
		Bean_Heard type = new Bean_Heard(Msg_Type.UI_CODE);
		Bean_UI module = new Bean_UI("动漫", "http://xxx.jpg", "#000000", "http://xxx.com");
		List<Bean_UI> modules = new ArrayList<Bean_UI>();
		modules.add(module);
		Bean_UI_Res ui = new Bean_UI_Res(type, modules);
		Bean_Result<Bean_UI_Res> result = new Bean_Result<Bean_UI_Res>("text/json", ui);
		return result;
	}
}
