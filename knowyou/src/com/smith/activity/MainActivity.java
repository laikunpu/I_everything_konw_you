package com.smith.activity;

import java.util.ArrayList;
import java.util.List;

import com.smith.adapter.ModuleAdapter;
import com.smith.adapter.MyPagerAdapter;
import com.smith.entity.Bean_UI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends BaseActivity {
	private ViewPager mViewPager;
	private List<View> mViews;
	private MyHandler mMyHandler;

	private List<Bean_UI> uis;

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
	}

	private void initData() {
		setResult(-1);
		mViews = new ArrayList<View>();
		uis = KnowyouApplication.getApplication().uis;
		setGridView();
		setViewPager();
	}

	private void initOnClickListener() {
	}


	private void setGridView() {
		GridView mGridView = new GridView(this);
		LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT);
		mGridView.setLayoutParams(lp);
		mGridView.setGravity(Gravity.CENTER);
		mGridView.setNumColumns(2);
		mGridView.setScrollBarStyle(-1);
		mGridView.setVerticalSpacing(20);
		mGridView.setHorizontalSpacing(20);
		ModuleAdapter moduleAdapter = new ModuleAdapter(this, uis);
		moduleAdapter.setOnClickListener(clickListener);
		mGridView.setAdapter(moduleAdapter);
		mViews.add(mGridView);
	}

	private void setViewPager() {
		mViewPager.setAdapter(new MyPagerAdapter(this, mViews));
	}
	OnClickListener clickListener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.lly_module:
				Intent intent = new Intent(MainActivity.this,
						FragmentTabsPager.class);
				startActivity(intent);
				break;

			default:
				break;
			}
		}
	};
}
