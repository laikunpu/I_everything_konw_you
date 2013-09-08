package com.smith.activity;

import com.smith.inter.DataCallback;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;

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

	}

	DataCallback mCallback = new DataCallback() {

		@Override
		public void onPrepare() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onStart() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onFinish() {
			// TODO Auto-generated method stub

		}
	};

}
