package com.smith.http.webservice.action;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.smith.http.webservice.entity.Bean_Result;
import com.smith.http.webservice.entity.Bean_common;
import com.smith.http.webservice.entity.Bean_common_Req;
import com.smith.http.webservice.entity.Bean_common_Res;
import com.smith.http.webservice.entity.Bean_common_detail;
import com.smith.http.webservice.entity.Bean_common_moreData;
import com.smith.http.webservice.entity.Bean_common_moreData_Req;
import com.smith.http.webservice.entity.Bean_common_page;
import com.smith.http.webservice.entity.Bean_common_page_Res;
import com.smith.http.webservice.entity.Bean_common_search;
import com.smith.http.webservice.entity.Bean_common_search_Res;
import com.smith.http.webservice.entity.Bean_common_sou_Req;
import com.smith.http.webservice.entity.Bean_common_url;
import com.smith.http.webservice.entity.Bean_module;
import com.smith.http.webservice.entity.heard.Bean_Response_Heard;
import com.smith.http.webservice.global.Msg_Type;
import com.smith.http.webservice.global.TN_Constant;
import com.smith.http.webservice.service.CommonService;
import com.smith.http.webservice.util.TNUrl;
import com.smith.http.webservice.util.TNUtil;

public class CommonAction {
	private CommonService service = new CommonService();
	private Gson gson = new Gson();

	public Bean_Result getCommons(String json) {

		Bean_common_moreData_Req req = gson.fromJson(json, Bean_common_moreData_Req.class);
		if (null == req)
			return null;
		Bean_Result<Bean_common_Res> result = new Bean_Result<Bean_common_Res>(TN_Constant.TYPE_JSON, null);
		Bean_common_Res common_Res = new Bean_common_Res(new Bean_Response_Heard(Msg_Type.CARTOON_CODE),
				service.getCommons(req.getMoreData().getUrl(), req.getMoreData().getNum()));
		result.setT(common_Res);
		return result;
	}

	public Bean_Result getDetail(String json) {

		Bean_common_Req req = gson.fromJson(json, Bean_common_Req.class);
		if (null == req)
			return null;
		Bean_Result<Bean_common_detail> result = new Bean_Result<Bean_common_detail>(TN_Constant.TYPE_JSON, null);
		Bean_common_detail detail = service.getDetail(req.getUrl().getUrl());
		result.setT(detail);
		return result;
	}

	public Bean_Result getDetail_next(String json) {

		Bean_common_Req req = gson.fromJson(json, Bean_common_Req.class);
		if (null == req)
			return null;
		Bean_Result<Bean_common_page_Res> result = new Bean_Result<Bean_common_page_Res>(TN_Constant.TYPE_JSON, null);
		Bean_common_page_Res common_page_Res = new Bean_common_page_Res(new Bean_Response_Heard(Msg_Type.CARTOON_CODE),
				null);
		List<Bean_common_page> bean_common_pages = service.getPage(req.getUrl().getUrl());
		common_page_Res.setBean_common_pages(bean_common_pages);
		result.setT(common_page_Res);
		return result;
	}
	
	public Bean_Result getSearch(String json) {

		Bean_common_sou_Req req = gson.fromJson(json, Bean_common_sou_Req.class);
		if (null == req)
			return null;
		Bean_Result<Bean_common_Res> result = new Bean_Result<Bean_common_Res>(TN_Constant.TYPE_JSON, null);
		Bean_common_Res common_Res = new Bean_common_Res(new Bean_Response_Heard(Msg_Type.CARTOON_CODE),
				service.getSearch(req.getCommon_sou().getWords()));
		result.setT(common_Res);
		return result;
	}

}
