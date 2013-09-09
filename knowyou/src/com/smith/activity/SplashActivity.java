package com.smith.activity;


import com.smith.inter.DataCallback;
import com.smith.util.HandleResp;
import com.smith.util.KYHttpClient;
import com.smith.util.ServiceApi;
import com.smith.util.ThreadDataLoader;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;

public class SplashActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		// System.out.println("dm.widthPixels=" + dm.widthPixels);
		// System.out.println("dm.heightPixels=" + dm.heightPixels);
		findViewById(R.id.lly_parent).postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SplashActivity.this, BackgroundActivity.class);
				startActivity(intent);
				finish();
			}
		}, 2000);

		new ThreadDataLoader(new Handler(), mCallback).excute();

	}

	DataCallback mCallback = new DataCallback() {

		@Override
		public void onPrepare() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onStart() {
			// TODO Auto-generated method stub
			try {
				HandleResp.handleMsg(KYHttpClient.get(ServiceApi.GET_MODULE), Knowyou.getApplication().uis);
				System.out.println("Knowyou.getApplication().uis  " + Knowyou.getApplication().uis.size());
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
