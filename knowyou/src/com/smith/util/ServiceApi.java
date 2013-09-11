package com.smith.util;

public class ServiceApi {
	private final static String IP = "192.168.1.107";
	private final static int PORT = 8080;
	public final static String MODULE = "http://" + IP + ":" + PORT + "/tn/GlobalAction/getUI_Module";
	public final static String RECOMMEND_MANHUA = "http://" + IP + ":" + PORT + "/tn/ComicAction/getComic_Recommend";
}
