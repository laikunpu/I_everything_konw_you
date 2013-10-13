package com.smith.http.webservice.action;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.smith.http.webservice.entity.Bean_Result;
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

	public Bean_Result getOnline_manhua(String json) {

		// 推荐漫画
		Bean_module recommend_module = service.getComic_Recommend(json);
		// 漫画排行
		Bean_module toplist_module = service.getComic_Toplist(json);

		Bean_module booklist_module = service.getOnline_Comic_list(0);

		List<Bean_module> second_modules = new ArrayList<Bean_module>();
		second_modules.add(recommend_module);
		second_modules.add(toplist_module);
		second_modules.add(booklist_module);
		Bean_common_Res res = new Bean_common_Res(new Bean_Response_Heard(Msg_Type.CARTOON_CODE), true,
				TNUrl.ACTION_SEARCH, second_modules);
		Bean_Result<Bean_common_Res> result = new Bean_Result<Bean_common_Res>(TN_Constant.TYPE_JSON, res);
		return result;
	}

	public Bean_Result getOnline_manhua_moreData(String json) {

		Bean_common_moreData_Req<Bean_common_moreData> req = gson.fromJson(json, Bean_common_moreData_Req.class);
		if (null == req)
			return null;
		int num = 0;
		if (null != req) {
			num = req.getMoreData().getNum();
		}

		Bean_module booklist_module = service.getOnline_Comic_list(num);

		List<Bean_module> second_modules = new ArrayList<Bean_module>();
		second_modules.add(booklist_module);
		Bean_common_Res res = new Bean_common_Res(new Bean_Response_Heard(Msg_Type.CARTOON_CODE), false, null,
				second_modules);
		Bean_Result<Bean_common_Res> result = new Bean_Result<Bean_common_Res>(TN_Constant.TYPE_JSON, res);
		return result;
	}

	public Bean_Result getOnline_manhua_detail(String json) {

		Bean_common_Req req = gson.fromJson(json, Bean_common_Req.class);
		if (null == req)
			return null;
		Bean_Result<Bean_common_detail> result = new Bean_Result<Bean_common_detail>(TN_Constant.TYPE_JSON, null);
		Bean_common_detail detail = service.getComic_Detail(req.getUrl().getUrl());
		result.setT(detail);
		return result;
	}

	public Bean_Result getOnline_manhua_detail_next(String json) {

		Bean_common_Req req = gson.fromJson(json, Bean_common_Req.class);
		if (null == req)
			return null;
		Bean_Result<Bean_common_page_Res> result = new Bean_Result<Bean_common_page_Res>(TN_Constant.TYPE_JSON, null);
		Bean_common_page_Res common_page_Res = new Bean_common_page_Res(new Bean_Response_Heard(Msg_Type.CARTOON_CODE),
				null);
		List<Bean_common_page> bean_common_pages = service.getComic_Page(req.getUrl().getUrl());
		common_page_Res.setBean_common_pages(bean_common_pages);
		result.setT(common_page_Res);
		return result;
	}

	public Bean_Result getOnline_manhua_search(String json) {

		Bean_common_sou_Req req = gson.fromJson(json, Bean_common_sou_Req.class);
		if (null == req)
			return null;
		Bean_Result<Bean_common_search_Res> result = new Bean_Result<Bean_common_search_Res>(TN_Constant.TYPE_JSON,
				null);
		Bean_common_search_Res common_search_Res = new Bean_common_search_Res(new Bean_Response_Heard(
				Msg_Type.CARTOON_CODE), null);
		List<Bean_common_search> bean_common_searchs = service.getOnline_Comic_search(req.getCommon_sou().getWords());
		common_search_Res.setBean_common_searchs(bean_common_searchs);
		result.setT(common_search_Res);
		return result;
	}

	
	
	public Bean_Result getPhilosophy_manhua(String json) {

		// 哲学推荐
		Bean_module recommend_module = service.getPhilosophy_Recommend(json);

		List<Bean_module> second_modules = new ArrayList<Bean_module>();
		second_modules.add(recommend_module);
		Bean_common_Res res = new Bean_common_Res(new Bean_Response_Heard(Msg_Type.CARTOON_CODE), true,
				TNUrl.ACTION_SEARCH, second_modules);
		Bean_Result<Bean_common_Res> result = new Bean_Result<Bean_common_Res>(TN_Constant.TYPE_JSON, res);
		return result;
	}

	public Bean_Result getPhilosophy_manhua_detail(String json) {

		Bean_common_Req req = gson.fromJson(json, Bean_common_Req.class);
		if (null == req)
			return null;
		Bean_Result<Bean_common_detail> result = new Bean_Result<Bean_common_detail>(TN_Constant.TYPE_JSON, null);
		Bean_common_detail detail = service.getPhilosophy_Detail(req.getUrl().getUrl());
		result.setT(detail);
		return result;
	}
	public Bean_Result getPhilosophy_manhua_detail_next(String json) {

		Bean_common_Req req = gson.fromJson(json, Bean_common_Req.class);
		if (null == req)
			return null;
		Bean_Result<Bean_common_page_Res> result = new Bean_Result<Bean_common_page_Res>(TN_Constant.TYPE_JSON, null);
		Bean_common_page_Res common_page_Res = new Bean_common_page_Res(new Bean_Response_Heard(Msg_Type.CARTOON_CODE),
				null);
		List<Bean_common_page> bean_common_pages = service.getPhilosophy_Page(req.getUrl().getUrl());
		common_page_Res.setBean_common_pages(bean_common_pages);
		result.setT(common_page_Res);
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
	
}
