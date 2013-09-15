package com.smith.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

import com.smith.activity.R;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;

public class KnowyouUtil {
	private static String encoding = "UTF-8";
	private static HashMap<Integer, PopupWindow> loadingViews = new HashMap<Integer, PopupWindow>();

	/**
	 * 用于显示数据加载提示界面
	 * 
	 * @param context
	 *            界面的上下文信息
	 * @param viewGroup
	 *            需要加载数据控件的父对象
	 * @param view
	 *            需要加载数据的控件
	 * @param text
	 *            需要显示的文字
	 */
	public static void addLoadingWin(Activity context, ViewGroup viewGroup) {
		ProgressBar pDialog = new ProgressBar(context);
		LinearLayout pLayout = new LinearLayout(context);
		pLayout.setLayoutParams(new LinearLayout.LayoutParams(200, 100));
		pLayout.setGravity(Gravity.CENTER);
		pLayout.setBackgroundResource(R.drawable.popmenu_bg_general);
		pLayout.addView(pDialog);
		PopupWindow popupWindow = new PopupWindow(pLayout, 200, 100, true);
		loadingViews.put(viewGroup.getId(), popupWindow);
		popupWindow.showAtLocation(viewGroup, Gravity.CENTER, 0, 0);
		context.setProgressBarVisibility(true);
	}

	/**
	 * 移除加载数据界面
	 * 
	 * @param viewGroup
	 *            需要加载数据控件的父对象
	 * @param view
	 *            需要加载数据的控件
	 */
	public static void removeLoadingWin(ViewGroup viewGroup) {
		PopupWindow popupWindow = loadingViews.get(viewGroup.getId());
		popupWindow.dismiss();
		loadingViews.remove(viewGroup.getId());
	}

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
