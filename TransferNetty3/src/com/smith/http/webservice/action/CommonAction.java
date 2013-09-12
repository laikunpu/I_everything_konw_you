package com.smith.http.webservice.action;

import java.util.ArrayList;
import java.util.List;

import com.smith.http.webservice.entity.Bean_Result;
import com.smith.http.webservice.entity.Bean_common_Res;
import com.smith.http.webservice.entity.Bean_second_module;
import com.smith.http.webservice.entity.heard.Bean_Heard;
import com.smith.http.webservice.global.Msg_Type;
import com.smith.http.webservice.global.TN_Constant;
import com.smith.http.webservice.service.CommonService;

public class CommonAction {
	private CommonService service = new CommonService();

	public Bean_Result getOnlin_manhua(String json) {

		// 推荐漫画
		Bean_second_module recommend_module = service.getComic_Recommend(json);
		// 漫画排行
		Bean_second_module toplist_module = service.getComic_Toplist(json);

		List<Bean_second_module> second_modules = new ArrayList<Bean_second_module>();
		second_modules.add(recommend_module);
		second_modules.add(toplist_module);
		Bean_common_Res res = new Bean_common_Res(new Bean_Heard(Msg_Type.CARTOON_CODE), second_modules);
		Bean_Result<Bean_common_Res> result = new Bean_Result<Bean_common_Res>(TN_Constant.TYPE_JSON, res);
		return result;
	}
}
