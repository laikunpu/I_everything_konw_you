package com.smith.activity;

import java.util.ArrayList;
import java.util.List;

import com.smith.activity.abandon.BackgroundActivity;
import com.smith.activity.abandon.BaseActivity;
import com.smith.db.DBHelper;
import com.smith.db.DaoImpl;
import com.smith.db.IDao;
import com.smith.entity.Bean_module;
import com.smith.entity.Bean_module_Res;
import com.smith.inter.DataCallback;
import com.smith.util.HandleResp;
import com.smith.util.KyHttpClient;
import com.smith.util.ServiceApi;
import com.smith.util.ThreadDataLoader;
import com.smith.util.UserSettings;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;

public class SplashActivity extends BaseActivity {
	private IDao dao;
	private UserSettings settings;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		initData();

		findViewById(R.id.lly_parent).postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SplashActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		}, 3000);

		new ThreadDataLoader(new Handler(), mCallback).excute();

	}

	private void initData() {
		// TODO Auto-generated method stub

		// System.out.println("programDir="+KyApplication.programDir);

		dao = new DaoImpl(this);
		settings = UserSettings.getInstance(this);
		if (settings.isfirstcome()) {
			Bean_module module = new Bean_module("全部", 0, null, null, true, 1, 249, "");
			KyApplication.getApplication().modules.add(module);
			dao.add_module(module);
		}
		KyApplication.getApplication().modules = dao.get_modules();

		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		// System.out.println("dm.widthPixels=" + dm.widthPixels);
		// System.out.println("dm.heightPixels=" + dm.heightPixels);
	}

	DataCallback mCallback = new DataCallback() {

		@Override
		public void onPrepare() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onStart() {
			// TODO Auto-generated method stub
			List<Bean_module> modules = null;
			try {
				Bean_module_Res module_Res = KyApplication.getApplication().gson.fromJson(
						KyHttpClient.get(ServiceApi.MODULE), Bean_module_Res.class);
				modules = module_Res.getModules();
				if (null != modules) {
					dao.delete_table(DBHelper.TABLE_BEAN_MODULE);
					for (int i = 0; i < modules.size(); i++) {
						dao.add_module(modules.get(i));
					}
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {

			}
		}

		@Override
		public void onFinish() {
			// TODO Auto-generated method stub

		}
	};

}
