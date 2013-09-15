package com.smith.http.webservice.util;

public class TNUrl {
	public final static String IP = "192.168.1.102";
	public final static int PORT = 8080;

	public final static String ONLINE_COMIC = "http://www.imanhua.com";

	public final static String ACTION_UI = "http://" + TNUrl.IP + ":" + TNUrl.PORT
			+ "/tn/CommonAction/getOnline_manhua";
	public final static String ACTION_DETAIL = "http://" + TNUrl.IP + ":" + TNUrl.PORT
			+ "/tn/CommonAction/getOnline_manhua_detail";
	public final static String ACTION_DEATIL_NEXT = "http://" + TNUrl.IP + ":" + TNUrl.PORT
			+ "/tn/CommonAction/getOnline_manhua_detail_next";

}
