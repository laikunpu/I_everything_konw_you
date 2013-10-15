package com.smith.http.webservice.util;

public class TNUrl {
	// public final static String IP = "192.168.0.134";
	public final static String IP = "192.168.137.206";
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

	public final static String PHILOSOPHY_COMIC = "http://www.hhmanhua.com";

	public final static String PHILOSOPHY_ACTION_UI = "http://" + TNUrl.IP + ":" + TNUrl.PORT
			+ "/tn/CommonAction/getPhilosophy_manhua";
	public final static String PHILOSOPHY_ACTION_DETAIL = "http://" + TNUrl.IP + ":" + TNUrl.PORT
			+ "/tn/CommonAction/getPhilosophy_manhua_detail";
	public final static String PHILOSOPHY_ACTION_DEATIL_NEXT = "http://" + TNUrl.IP + ":" + TNUrl.PORT
			+ "/tn/CommonAction/getPhilosophy_manhua_detail_next";

	
	
	
	
	public final static String SEEMH_COMIC = "http://www.seemh.com";
	public final static String ACTION_SEEMH_MODULE = "http://" + TNUrl.IP + ":" + TNUrl.PORT
			+ "/tn/CommonAction/getSeemhmodule_manhua";
	public final static String ACTION_SEEMH_DETAIL = "http://" + TNUrl.IP + ":" + TNUrl.PORT
			+ "/tn/CommonAction/getDetail";
	public final static String ACTION_SEEMH_DETAI_NEXT = "http://" + TNUrl.IP + ":" + TNUrl.PORT
			+ "/tn/CommonAction/getDetail_next";
}
