package com.smith.activity;

import java.util.List;
import com.smith.activity.abandon.BaseActivity;
import com.smith.db.DBHelper;
import com.smith.db.DaoImpl;
import com.smith.db.IDao;
import com.smith.entity.Bean_module;
import com.smith.entity.Bean_module_Res;
import com.smith.inter.DataCallback;
import com.smith.util.KyHttpClient;
import com.smith.util.ServiceApi;
import com.smith.util.ThreadDataLoader;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;

public class SplashActivity extends BaseActivity {
	private IDao dao;
	private KyApplication application;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		initData();
		new ThreadDataLoader(new Handler(), mCallback).excute();

	}

	private void initData() {
		// TODO Auto-generated method stub

		// System.out.println("programDir="+KyApplication.programDir);
		application = KyApplication.getApplication();
		dao = new DaoImpl(this);

		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		// System.out.println("dm.widthPixels=" + dm.widthPixels);
		// System.out.println("dm.heightPixels=" + dm.heightPixels);
	}

	DataCallback mCallback = new DataCallback() {
		private long startTime;

		@Override
		public void onPrepare() {
			// TODO Auto-generated method stub
			startTime = System.currentTimeMillis();
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
					application.modules=modules;
					dao.delete_table(DBHelper.TABLE_BEAN_MODULE);
					dao.delete_table(DBHelper.TABLE_BEAN_COMMON);
					for (int i = 0; i < modules.size(); i++) {
						dao.add_module(modules.get(i));
					}
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			} finally {
				if (System.currentTimeMillis() - startTime < 3000) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

		@Override
		public void onFinish() {
			// TODO Auto-generated method stub
			if (null == application.modules||application.modules.size()==0) {
				application.modules=dao.get_modules();
				
			}
			Intent intent = new Intent(SplashActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
		}
	};

}
