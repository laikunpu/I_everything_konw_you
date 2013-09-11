package com.smith.activity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import uk.co.senab.bitmapcache.BitmapLruCache;

import com.google.gson.Gson;
import com.smith.entity.Bean_UI;
import com.smith.entity.Bean_second_module;

import android.app.Application;
import android.os.Environment;
import android.os.Handler;

public class KnowyouApplication extends Application {
	private static KnowyouApplication instance;

	private BitmapLruCache mCache;

	public final Gson gson = new Gson();

	public List<Bean_UI> uis = new ArrayList<Bean_UI>();
	
	public List<Bean_second_module> second_modules = new ArrayList<Bean_second_module>();

	public static KnowyouApplication getApplication() {
		return instance;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		instance = this;

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
