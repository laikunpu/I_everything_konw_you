package com.smith.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

import com.smith.activity.KyApplication;
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

public class KyUtil {
	private static String encoding = "UTF-8";
	private static HashMap<Integer, PopupWindow> loadingViews = new HashMap<Integer, PopupWindow>();
	private static HashMap<Integer, ProgressView> pgvViews = new HashMap<Integer, ProgressView>();

	public final static long predelayMillis = 100;
	public final static long postdelayMillis = 10;
	private final static int preStart = 0;
	private final static int preNum = 65;
	private static int preNumCount = 0;
	private static int max = 100;

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
	public static void addLoadingWin(Activity context, ViewGroup viewGroup, ProgressStatus preStatus) {
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
		sendProgress(viewGroup, preStart, preNum, predelayMillis, null, preStatus, true);
	}


	public static void removeLoadingWin(final ViewGroup viewGroup, final Runnable runnable,boolean isAdvance) {

		Runnable r = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (null != runnable) {
					runnable.run();
				}

				PopupWindow popupWindow = loadingViews.get(viewGroup.getId());
				popupWindow.dismiss();
				loadingViews.remove(viewGroup.getId());
				pgvViews.remove(viewGroup.getId());
			}
		};
		sendProgress(viewGroup, preNumCount, max, postdelayMillis, r, null, isAdvance);

	}

	private static void sendProgress(ViewGroup viewGroup, int num) {
		// TODO Auto-generated method stub
		if (0 < num && num <= max) {
			ProgressView pDialog = pgvViews.get(viewGroup.getId());
			pDialog.setProgress(num);
		}

	}

	public static void sendProgress(final ViewGroup viewGroup, final int start, final int num, final long delayMillis,
			final Runnable r, final ProgressStatus preStatus, final boolean isAdvance) {
		if (0 < num && num <= max) {
			final ProgressView pDialog = pgvViews.get(viewGroup.getId());
			if (null != pDialog) {
				KyApplication.getApplication().handler.postDelayed(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (null != preStatus && preStatus.cancel) {
							return;
						} else if (0 <= start && start <= num) {
							pDialog.setProgress(start);
							int newStart;
							if (isAdvance) {
								newStart = start + 1;
							} else {
								newStart = start - 1;
							}
							preNumCount = newStart;
							sendProgress(viewGroup, newStart, num, delayMillis, r, preStatus, isAdvance);
						} else if (null != r) {
							r.run();
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
	public static boolean connectivityIsAvailable(Context context) {
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

	public static String replaceImgSuffix(String imgUrl) {
		String frontPartUrl = imgUrl.substring(0, imgUrl.lastIndexOf(".") + 1);
		String preSuffix = imgUrl.substring(imgUrl.lastIndexOf(".") + 1);
		String hindSuffix = "jpg";
		if (preSuffix.toLowerCase().equals("jpg")) {
			hindSuffix = "png";
		} else if (preSuffix.toLowerCase().equals("png")) {
			hindSuffix = "jpg";
		}
		String newImgUrl = frontPartUrl + hindSuffix;

		return newImgUrl;
	}

	public static boolean pingIP(String ip) {
		boolean isReachable = false;
		try {
			InetAddress address = InetAddress.getByName(ip);
			isReachable = address.isReachable(3000);
		} catch (Exception e) {
		}
		return isReachable;
	}

}
