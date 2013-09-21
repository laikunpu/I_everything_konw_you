package com.smith.activity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import uk.co.senab.bitmapcache.BitmapLruCache;

import com.google.gson.Gson;
import com.smith.entity.Bean_UI;
import com.smith.entity.Bean_common_Res;
import com.smith.entity.Bean_common_detail;
import com.smith.entity.Bean_common_detail_content;
import com.smith.entity.Bean_common_page_Res;
import com.smith.entity.Bean_common_search_Res;
import com.smith.entity.Bean_second_module;

import android.app.Application;
import android.os.Environment;
import android.os.Handler;

public class KyApplication extends Application {

	private static KyApplication instance;

	private BitmapLruCache mCache;

	public static String programDir = "";

	public final Gson gson = new Gson();
	public final static Handler handler = new Handler();

	public List<Bean_UI> uis = new ArrayList<Bean_UI>();

	Bean_common_Res common_Res = null;

	Bean_common_detail common_detail = null;
	
	Bean_common_search_Res search_Res = null;

	Bean_common_detail_content content = null;

	Bean_common_page_Res common_page_Res = null;

	public static KyApplication getApplication() {
		return instance;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		instance = this;

		programDir = getApplicationContext().getFilesDir().getAbsolutePath();

		File cacheLocation;

		// If we have external storage use it for the disk cache. Otherwise we
		// use
		// the cache dir
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			cacheLocation = new File(Environment.getExternalStorageDirectory() + "/Android-BitmapCache");
		} else {
			cacheLocation = new File(getFilesDir() + "/Android-BitmapCache");
		}
		cacheLocation.mkdirs();

		BitmapLruCache.Builder builder = new BitmapLruCache.Builder(this);
		builder.setMemoryCacheEnabled(true).setMemoryCacheMaxSizeUsingHeapSize();
		builder.setDiskCacheEnabled(true).setDiskCacheLocation(cacheLocation);

		mCache = builder.build();
	}

	public BitmapLruCache getBitmapCache() {
		return mCache;
	}
}
