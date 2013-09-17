package com.smith.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

import com.smith.activity.KnowyouApplication;
import com.smith.activity.R;
import com.smith.view.ProgressView;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;

public class KnowyouUtil {
	private static String encoding = "UTF-8";
	private static HashMap<Integer, PopupWindow> loadingViews = new HashMap<Integer, PopupWindow>();
	private static HashMap<Integer, ProgressView> pgvViews = new HashMap<Integer, ProgressView>();
	private static int max = 100;
	public final static long delayMillis = 200;
	private final static int preStart = 0;
	private final static int preNum = 80;

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

		View view = LayoutInflater.from(context).inflate(R.layout.loading, null);
		ProgressView pDialog = (ProgressView) view.findViewById(R.id.pgv_load);
		pDialog.setDensity(2);
		pDialog.setMax(max);
		PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT, true);
		loadingViews.put(viewGroup.getId(), popupWindow);
		pgvViews.put(viewGroup.getId(), pDialog);
		popupWindow.showAtLocation(viewGroup, Gravity.CENTER, 0, 0);
		context.setProgressBarVisibility(true);
		sendProgress(viewGroup, preStart, preNum, delayMillis);
	}

	/**
	 * 移除加载数据界面
	 * 
	 * @param viewGroup
	 *            需要加载数据控件的父对象
	 * @param view
	 *            需要加载数据的控件
	 */
	public static void removeLoadingWin(final ViewGroup viewGroup) {

		sendProgress(viewGroup, 100);
		PopupWindow popupWindow = loadingViews.get(viewGroup.getId());
		popupWindow.dismiss();
		loadingViews.remove(viewGroup.getId());
		pgvViews.remove(viewGroup.getId());

	}

	private static void sendProgress(ViewGroup viewGroup, int num) {
		// TODO Auto-generated method stub
		if (0 < num && num <= max) {
			ProgressView pDialog = pgvViews.get(viewGroup.getId());
			pDialog.setProgress(num);
		}

	}

	public static void sendProgress(final ViewGroup viewGroup, final int start, final int num, final long delayMillis) {
		if (0 < num && num <= max) {
			final ProgressView pDialog = pgvViews.get(viewGroup.getId());
			if (null != pDialog) {
				KnowyouApplication.getApplication().handler.postDelayed(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (start < num) {
							pDialog.setProgress(start);
							int newStart = start + 1;
							sendProgress(viewGroup, newStart, num, delayMillis);
						}

					}
				}, delayMillis);

			}
		}
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
