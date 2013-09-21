package com.smith.activity;

import java.util.ArrayList;
import java.util.List;

import com.smith.db.DBHelper;
import com.smith.db.DaoImpl;
import com.smith.db.IDao;
import com.smith.entity.Bean_UI;
import com.smith.entity.Bean_UI_Res;
import com.smith.inter.DataCallback;
import com.smith.util.HandleResp;
import com.smith.util.KyHttpClient;
import com.smith.util.ServiceApi;
import com.smith.util.ThreadDataLoader;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;

public class SplashActivity extends BaseActivity {
	private IDao dao;

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
				Intent intent = new Intent(SplashActivity.this, BackgroundActivity.class);
				startActivity(intent);
				finish();
			}
		}, 3000);

		new ThreadDataLoader(new Handler(), mCallback).excute();

	}

	private void initData() {
		// TODO Auto-generated method stub
		
		
//		System.out.println("programDir="+KyApplication.programDir);
		
		dao = new DaoImpl(this);
		KyApplication.getApplication().uis = dao.get_ui();
		if (null == KyApplication.getApplication().uis || KyApplication.getApplication().uis.size() == 0) {
			KyApplication.getApplication().uis = new ArrayList<Bean_UI>();
			Bean_UI ui1 = new Bean_UI("漫画", "", "", "", "");
			Bean_UI ui2 = new Bean_UI("动画", "", "", "", "");
			Bean_UI ui3 = new Bean_UI("电影", "", "", "", "");
			KyApplication.getApplication().uis.add(ui1);
			KyApplication.getApplication().uis.add(ui2);
			KyApplication.getApplication().uis.add(ui3);
		}
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
			List<Bean_UI> uis = null;
			try {
				Bean_UI_Res bean_UI_Res = KyApplication.getApplication().gson.fromJson(
						KyHttpClient.get(ServiceApi.MODULE), Bean_UI_Res.class);
				uis = bean_UI_Res.getUis();
				if (null != uis) {
					dao.delete_table(DBHelper.TABLE_BEAN_UI);
					dao.add_uis(uis);
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
