package com.smith.activity;

import java.util.ArrayList;
import java.util.List;

import com.smith.adapter.UIAdapter;
import com.smith.adapter.MyPagerAdapter;
import com.smith.db.DBHelper;
import com.smith.entity.Bean_UI;
import com.smith.entity.Bean_UI_Res;
import com.smith.entity.Bean_common_Res;
import com.smith.entity.Bean_second_module;
import com.smith.inter.DataCallback;
import com.smith.util.AsyncDataLoader;
import com.smith.util.KyHttpClient;
import com.smith.util.KyUtil;
import com.smith.util.ProgressStatus;
import com.smith.util.ServiceApi;
import com.smith.util.ToastUtils;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

public class MainActivity extends BaseActivity {
	private ViewPager mViewPager;
	private List<View> mViews;
	private MyHandler mMyHandler;

	private List<Bean_UI> uis;
	private Bean_UI ui;
	private int currentPage = 0;
	private int clickUI = 0;
	private List<UIAdapter> adapters = new ArrayList<UIAdapter>();

	private RelativeLayout view_parent;

	private Bean_common_Res common_Res = null;

	private class MyHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			try {
				switch (msg.what) {
				case 1:
					break;
				default:
					break;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
			}
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initData();
		initOnClickListener();
	}

	private void initView() {
		mViewPager = (ViewPager) findViewById(R.id.vp_main);
		view_parent = (RelativeLayout) findViewById(R.id.view_parent);
	}

	private void initData() {
		setResult(-1);
		mViews = new ArrayList<View>();
		uis = KyApplication.getApplication().uis;
		setGridView();
		setViewPager();
	}

	private void initOnClickListener() {
		mViewPager.setOnPageChangeListener(pageChangeListener);
	}

	private void setGridView() {
		GridView mGridView = new GridView(this);
		LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		mGridView.setLayoutParams(lp);
		mGridView.setGravity(Gravity.CENTER);
		mGridView.setNumColumns(2);
		mGridView.setScrollBarStyle(-1);
		mGridView.setVerticalSpacing(5);
		mGridView.setHorizontalSpacing(5);
		UIAdapter moduleAdapter = new UIAdapter(this, uis);
		mGridView.setAdapter(moduleAdapter);
		moduleAdapter.setOnClickListener(clickListener);
		mViews.add(mGridView);
		adapters.add(moduleAdapter);
	}

	private void setViewPager() {
		mViewPager.setAdapter(new MyPagerAdapter(this, mViews));
	}

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.lly_module:
				clickUI = Integer.parseInt(v.getTag().toString());
				new AsyncDataLoader(callback).execute();

				break;

			default:
				break;
			}
		}
	};
	OnPageChangeListener pageChangeListener = new OnPageChangeListener() {

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			currentPage = arg0;
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}
	};

	DataCallback callback = new DataCallback() {
		private int times;
		private ProgressStatus preStatus;
		private int netStatus = 0;

		@Override
		public void onPrepare() {
			// TODO Auto-generated method stub
			times = 3;
			preStatus = new ProgressStatus();
			KyUtil.addLoadingWin(MainActivity.this, view_parent, preStatus);
		}

		@Override
		public void onStart() {
			// TODO Auto-generated method stub
			if (!KyUtil.connectivityIsAvailable(MainActivity.this)) {
				netStatus = -1;
				return;
			}
			if (!KyUtil.pingIP(ServiceApi.IP)) {
				netStatus = 0;
				return;
			}
			try {
				if (null == common_Res || common_Res.getBean_second_modules().size() == 0) {
					common_Res = KyApplication.getApplication().gson.fromJson(
							KyHttpClient.get(uis.get(clickUI).getModule_action()), Bean_common_Res.class);
				}
				netStatus = 1;

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				netStatus = 2;
				times--;
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				System.out.println("重试" + times + "次");
				if (times > 0) {
					onStart();

				}
			}
		}

		@Override
		public void onFinish() {
			// TODO Auto-generated method stub
			preStatus.cancel = true;

			switch (netStatus) {
			case -1:
				KyUtil.removeLoadingWin(view_parent,null,false);
				ToastUtils.showToast(MainActivity.this, R.string.network_status_toast);
				break;
			case 0:
				KyUtil.removeLoadingWin(view_parent,null,false);
				ToastUtils.showToast(MainActivity.this, R.string.service_status_toast);
				break;
			case 1:
				KyUtil.removeLoadingWin(view_parent, new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (null != common_Res && common_Res.getBean_second_modules().size() > 0) {
							KyApplication.getApplication().common_Res = common_Res;
							Intent intent = new Intent(MainActivity.this, FragmentTabsPager.class);
							startActivity(intent);
						} else {
							ToastUtils.showToast(MainActivity.this, R.string.request_null_toast);
						}
					}
				},true);

				break;
			case 2:
				KyUtil.removeLoadingWin(view_parent,null,false);
				ToastUtils.showToast(MainActivity.this, R.string.request_long_toast);
				break;
			}

		}
	};

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		// System.out.println("onStop");
	}
}
