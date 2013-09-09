package com.smith.util;

import com.smith.activity.Knowyou;
import com.smith.entity.Bean_Res;

public class HandleResp {

	public static Object handleMsg(String json, Object object) {
		Bean_Res bean_Res = Knowyou.getApplication().gson.fromJson(json, Bean_Res.class);
		switch (bean_Res.getBean_Heard().getType_code()) {
		case 1:
			object = bean_Res.getT();
			break;

		default:
			break;
		}
		return object;

	}
}
