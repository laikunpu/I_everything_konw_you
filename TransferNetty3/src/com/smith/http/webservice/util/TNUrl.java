package com.smith.http.webservice.util;

public class TNUrl {
	// public final static String IP = "192.168.0.134";
	public final static String IP = "192.168.1.105";
	public final static int PORT = 8080;

	public final static String ONLINE_COMIC = "http://www.imanhua.com";
	
	public final static String ONLINE_COMIC_SEARCH = "http://www.imanhua.com/v2/user/search.aspx?key=";
	
	public final static String ONLINE_COMIC_LIST = "http://www.imanhua.com/comic/japan/index.html#best";

	public final static String ACTION_UI = "http://" + TNUrl.IP + ":" + TNUrl.PORT
			+ "/tn/CommonAction/getOnline_manhua";
	public final static String ACTION_DETAIL = "http://" + TNUrl.IP + ":" + TNUrl.PORT
			+ "/tn/CommonAction/getOnline_manhua_detail";
	public final static String ACTION_DEATIL_NEXT = "http://" + TNUrl.IP + ":" + TNUrl.PORT
			+ "/tn/CommonAction/getOnline_manhua_detail_next";
	public final static String ACTION_MOREDATA = "http://" + TNUrl.IP + ":" + TNUrl.PORT
			+ "/tn/CommonAction/getOnline_manhua_moreData";
	public final static String ACTION_SEARCH = "http://" + TNUrl.IP + ":" + TNUrl.PORT
			+ "/tn/CommonAction/getOnline_manhua_search";

}
