/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.smith.activity;

import java.util.ArrayList;
import java.util.List;

import com.smith.entity.Bean_common_Req;
import com.smith.entity.Bean_common_Res;
import com.smith.entity.Bean_common_detail;
import com.smith.entity.Bean_common_search_Res;
import com.smith.entity.Bean_common_sou;
import com.smith.entity.Bean_common_sou_Req;
import com.smith.entity.Bean_common_url;
import com.smith.entity.heard.Bean_Request_Head;
import com.smith.inter.DataCallback;
import com.smith.util.AsyncDataLoader;
import com.smith.util.KyHttpClient;
import com.smith.util.KyUtil;
import com.smith.util.ProgressStatus;
import com.smith.util.ServiceApi;
import com.smith.util.ToastUtils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

/**
 * Demonstrates combining a TabHost with a ViewPager to implement a tab UI that
 * switches between tabs and also allows the user to perform horizontal flicks
 * to move between the tabs.
 */
public class FragmentTabsPager extends FragmentActivity {
	TabHost mTabHost;
	ViewPager mViewPager;
	TabsAdapter mTabsAdapter;

	KyApplication application;

	private LinearLayout lly_search;
	private EditText etx_search;
	private ImageView img_search;
	
	private Bean_common_search_Res search_Res;
	private Bean_common_Res common_Res;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

		setContentView(R.layout.fragment_tabs_pager);
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();

		mViewPager = (ViewPager) findViewById(R.id.pager);

		List<TextView> textViews = new ArrayList<TextView>();

		mTabsAdapter = new TabsAdapter(this, mTabHost, mViewPager, textViews);

		application = KyApplication.getApplication();

		 common_Res = application.common_Res;
		
		lly_search = (LinearLayout) findViewById(R.id.lly_search);
		etx_search = (EditText) findViewById(R.id.etx_search);
		img_search = (ImageView) findViewById(R.id.img_search);
		if(common_Res.isSearch()){
			lly_search.setVisibility(View.VISIBLE);
			img_search.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					if(etx_search.getText().toString().trim().equals("")){
						ToastUtils.showToast(FragmentTabsPager.this, "搜索词不能为空");
					}else{
						new AsyncDataLoader(callback).execute();
					}
				}
			});
		}

		for (int i = 0; i < common_Res.getBean_second_modules().size(); i++) {
			Bundle bundle = new Bundle();
			bundle.putInt("classify", i);
			View view = LayoutInflater.from(FragmentTabsPager.this).inflate(R.layout.tab_item, null);
			TextView textView = (TextView) view.findViewById(R.id.txt_tab);
			textView.setText(common_Res.getBean_second_modules().get(i).getSecond_module_name());
			textView.setPressed(true);
			textViews.add(textView);
			mTabsAdapter.addTab(mTabHost.newTabSpec(common_Res.getBean_second_modules().get(i).getSecond_module_name())
					.setIndicator(view), CommonModuleFragment.class, bundle);
		}
		if (savedInstanceState != null) {
			mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString("tab", mTabHost.getCurrentTabTag());
	}

	/**
	 * This is a helper class that implements the management of tabs and all
	 * details of connecting a ViewPager with associated TabHost. It relies on a
	 * trick. Normally a tab host has a simple API for supplying a View or
	 * Intent that each tab will show. This is not sufficient for switching
	 * between pages. So instead we make the content part of the tab host 0dp
	 * high (it is not shown) and the TabsAdapter supplies its own dummy view to
	 * show as the tab content. It listens to changes in tabs, and takes care of
	 * switch to the correct paged in the ViewPager whenever the selected tab
	 * changes.
	 */
	public static class TabsAdapter extends FragmentPagerAdapter implements TabHost.OnTabChangeListener,
			ViewPager.OnPageChangeListener {
		private final Context mContext;
		private final TabHost mTabHost;
		private final ViewPager mViewPager;
		private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();
		private List<TextView> textViews;

		static final class TabInfo {
			private final String tag;
			private final Class<?> clss;
			private final Bundle args;

			TabInfo(String _tag, Class<?> _class, Bundle _args) {
				tag = _tag;
				clss = _class;
				args = _args;
			}
		}

		static class DummyTabFactory implements TabHost.TabContentFactory {
			private final Context mContext;

			public DummyTabFactory(Context context) {
				mContext = context;
			}

			@Override
			public View createTabContent(String tag) {
				View v = new View(mContext);
				v.setMinimumWidth(0);
				v.setMinimumHeight(0);
				return v;
			}
		}

		public TabsAdapter(FragmentActivity activity, TabHost tabHost, ViewPager pager, List<TextView> textViews) {
			super(activity.getSupportFragmentManager());
			mContext = activity;
			mTabHost = tabHost;
			mViewPager = pager;
			mTabHost.setOnTabChangedListener(this);
			mViewPager.setAdapter(this);
			mViewPager.setOnPageChangeListener(this);
			this.textViews = textViews;
		}

		public void addTab(TabHost.TabSpec tabSpec, Class<?> clss, Bundle args) {
			tabSpec.setContent(new DummyTabFactory(mContext));
			String tag = tabSpec.getTag();

			TabInfo info = new TabInfo(tag, clss, args);
			mTabs.add(info);
			mTabHost.addTab(tabSpec);
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return mTabs.size();
		}

		@Override
		public Fragment getItem(int position) {
			TabInfo info = mTabs.get(position);
			return Fragment.instantiate(mContext, info.clss.getName(), info.args);
		}

		@Override
		public void onTabChanged(String tabId) {
			int position = mTabHost.getCurrentTab();
			mViewPager.setCurrentItem(position);
			System.out.println("position=" + position);
			System.out.println("textViews.size()=" + textViews.size());
			for (int i = 0; i < textViews.size(); i++) {
				if (i == position) {

					textViews.get(i).setPressed(false);
				} else {
					textViews.get(i).setPressed(true);
				}
			}

		}

		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		}

		@Override
		public void onPageSelected(int position) {
			// Unfortunately when TabHost changes the current tab, it kindly
			// also takes care of putting focus on it when not in touch mode.
			// The jerk.
			// This hack tries to prevent this from pulling focus out of our
			// ViewPager.
			TabWidget widget = mTabHost.getTabWidget();
			int oldFocusability = widget.getDescendantFocusability();
			widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
			mTabHost.setCurrentTab(position);
			widget.setDescendantFocusability(oldFocusability);
		}

		@Override
		public void onPageScrollStateChanged(int state) {
		}
	}
	
	
	
	DataCallback callback = new DataCallback() {
		private int times;
		private ProgressStatus preStatus;
		private int netStatus = 0;

		@Override
		public void onPrepare() {
			// TODO Auto-generated method stub
			times = 3;
			preStatus = new ProgressStatus();
			KyUtil.addLoadingWin(FragmentTabsPager.this, mTabHost, preStatus);
		}

		@Override
		public void onStart() {
			// TODO Auto-generated method stub
			if (!KyUtil.connectivityIsAvailable(FragmentTabsPager.this)) {
				netStatus = -1;
				return;
			}
			if (!KyUtil.pingIP(ServiceApi.IP)) {
				netStatus = 0;
				return;
			}
			try {
				Bean_common_sou_Req req = new Bean_common_sou_Req(
						new Bean_Request_Head(0), null);
				Bean_common_sou sou = new Bean_common_sou(etx_search.getText().toString().trim());
				req.setCommon_sou(sou);
				String requestJson = KyApplication.getApplication().gson.toJson(req);
				search_Res = KyApplication.getApplication().gson.fromJson(
						KyHttpClient.post(common_Res.getSearchAction(), requestJson),
						Bean_common_search_Res.class);
				netStatus = 1;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				netStatus = 2;
				times--;
				search_Res = null;
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
				KyUtil.removeLoadingWin(mTabHost, null, false);
				ToastUtils.showToast(FragmentTabsPager.this, R.string.network_status_toast);
				break;
			case 0:
				KyUtil.removeLoadingWin(mTabHost, null, false);
				ToastUtils.showToast(FragmentTabsPager.this, R.string.service_status_toast);
				break;
			case 1:
				KyUtil.removeLoadingWin(mTabHost, new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (null != search_Res&&null!=search_Res.getBean_common_searchs()&&search_Res.getBean_common_searchs().size()>0) {
							KyApplication.getApplication().search_Res = search_Res;
							Intent intent = new Intent(FragmentTabsPager.this, SearchActivity.class);
							startActivity(intent);
						} else {
							ToastUtils.showToast(FragmentTabsPager.this, R.string.request_null_toast);
						}
					}
				}, true);

				break;
			case 2:
				KyUtil.removeLoadingWin(mTabHost, null, false);
				ToastUtils.showToast(FragmentTabsPager.this, R.string.request_long_toast);
				break;
			}

		}
	};
	
	
	
	
	
	
}
