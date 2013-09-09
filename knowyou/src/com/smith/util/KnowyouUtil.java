package com.smith.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class KnowyouUtil {
	/**
	 * 判断网络是否可用
	 * 
	 * @param context
	 * @return
	 */
	public static boolean ConnectivityIsAvailable(Context context) {
		// 判断网络是否可用
		NetworkInfo info = null;
		ConnectivityManager cManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		info = cManager.getActiveNetworkInfo();
		if (info != null && info.isAvailable()) {
			return true;
		}
		return false;
	}

	/**
	 * 判断wifi网络是否可用
	 * 
	 * @param context
	 * @return
	 */
	public static boolean WifiIsAvailable(Context context) {
		// 判断网络是否可用
		NetworkInfo info = null;
		ConnectivityManager cManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		info = cManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (info != null && info.isAvailable()) {
			return true;
		}
		return false;
	}

	/**
	 * 判断moblie网络是否可用
	 * 
	 * @param context
	 * @return
	 */
	public static boolean MobileIsAvailable(Context context) {
		// 判断网络是否可用
		NetworkInfo info = null;
		ConnectivityManager cManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		info = cManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (info != null && info.isAvailable()) {
			return true;
		}
		return false;
	}
}
