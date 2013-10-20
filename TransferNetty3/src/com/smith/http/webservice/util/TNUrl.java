package com.smith.http.webservice.util;

public class TNUrl {
	// public final static String IP = "192.168.0.134";
	public final static String IP = "192.168.137.206";
	public final static int PORT = 8080;

	
	public final static String SEEMH_COMIC = "http://www.seemh.com";
	public final static String SEEMH_SEARCH = "http://www.seemh.com/search/";
	public final static String ACTION_SEEMH_MODULE = "http://" + TNUrl.IP + ":" + TNUrl.PORT
			+ "/tn/CommonAction/getSeemhmodule_manhua";
	public final static String ACTION_SEEMH_DETAIL = "http://" + TNUrl.IP + ":" + TNUrl.PORT
			+ "/tn/CommonAction/getDetail";
	public final static String ACTION_SEEMH_DETAI_NEXT = "http://" + TNUrl.IP + ":" + TNUrl.PORT
			+ "/tn/CommonAction/getDetail_next";
	public final static String ACTION_SEEMH_MOREDATA = "http://" + TNUrl.IP + ":" + TNUrl.PORT
			+ "/tn/CommonAction/getCommons";
	public final static String ACTION_SEEMH_SEARCH = "http://" + TNUrl.IP + ":" + TNUrl.PORT
			+ "/tn/CommonAction/getSearch";
}
