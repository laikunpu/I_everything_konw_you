package com.smith.activity;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.smith.entity.Bean_UI;

import android.app.Application;
import android.os.Handler;

public class Knowyou extends Application {
	private static Knowyou instance;


	public final Gson gson = new Gson();

	public List<Bean_UI> uis=new ArrayList<Bean_UI>();


	public static Knowyou getApplication() {
		return instance;
	}


	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		instance=this;
	}


}
