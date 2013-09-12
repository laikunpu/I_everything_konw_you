package com.smith.http.webservice.action;

import java.lang.reflect.Method;

import com.smith.http.webservice.entity.Bean_Result;

public class ActionTop {
	public static Bean_Result distributeAction(String[] s,String json) {
		if (s.length == 4) {
			try {
				Class action = Class.forName("com.smith.http.webservice.action." + s[2]);
				Method[] methods = action.getMethods();
				for (int i = 0; i < methods.length; i++) {
					Method method = methods[i];
					if (method.getName().equals(s[3])) {
						Bean_Result result = (Bean_Result) method.invoke(action.newInstance(),json);
//						System.out.println("result=" + result);
						return result;
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.err.println("ActionTop->distributeAction");
				e.printStackTrace();
			}
		}
		return null;
	}
}
