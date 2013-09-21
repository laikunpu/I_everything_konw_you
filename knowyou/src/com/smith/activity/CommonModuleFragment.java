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

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.gson.JsonSyntaxException;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.smith.adapter.CommonAdapter;
import com.smith.entity.Bean_common_Req;
import com.smith.entity.Bean_common;
import com.smith.entity.Bean_common_Res;
import com.smith.entity.Bean_common_detail;
import com.smith.entity.Bean_common_moreData;
import com.smith.entity.Bean_common_moreData_Req;
import com.smith.entity.Bean_common_url;
import com.smith.entity.Bean_second_module;
import com.smith.entity.heard.Bean_Request_Head;
import com.smith.inter.DataCallback;
import com.smith.util.AsyncDataLoader;
import com.smith.util.KyHttpClient;
import com.smith.util.KyUtil;
import com.smith.util.ProgressStatus;
import com.smith.util.ServiceApi;
import com.smith.util.ToastUtils;

public class CommonModuleFragment extends Fragment {
	private int classify;
	private RelativeLayout view_parent;
	private WebView wb_advert;
	private String adUrl;
	private PullToRefreshGridView pull_refresh_grid;

	private int currentData;
	private CommonAdapter comicAdapter;

	private Bean_common_detail detail;
	private Bean_second_module second_module;

	private RelativeLayout rly_FootRefreshTip;
	private ImageView img_update;
	private boolean isFootRefreshing = false;
	private Animation rotateAnimation = null;
	private Bean_common_Res common_Res;

	/**
	 * When creating, retrieve this instance's number from its arguments.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		classify = getArguments().getInt("classify", 0);
		// System.out.println("CountingFragment->onCreate  name=" + classify);

	}

	/**
	 * The Fragment's UI is just a simple text view showing its instance number.
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		// System.out.println("CountingFragment->onCreateView  classify=" +
		// classify);

		View v = inflater.inflate(R.layout.common_module, container, false);

		initView(v);
		initData();
		initOnClickListener();
		return v;
	}

	private void initView(View v) {
		// TODO Auto-generated method stub
		wb_advert = (WebView) v.findViewById(R.id.wb_advert);
		pull_refresh_grid = (PullToRefreshGridView) v.findViewById(R.id.pull_refresh_grid);
		view_parent = (RelativeLayout) v.findViewById(R.id.view_parent);

		rly_FootRefreshTip = (RelativeLayout) v.findViewById(R.id.rly_FootRefreshTip);
		img_update = (ImageView) v.findViewById(R.id.img_update);
	}

	private void initData() {
		// TODO Auto-generated method stub

		rotateAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_anim);
		second_module = KyApplication.getApplication().common_Res.getBean_second_modules().get(classify);
		
		
		adUrl = KyApplication.getApplication().common_Res.getBean_second_modules().get(classify).getAdUrl();
		comicAdapter = new CommonAdapter(getActivity(), second_module.getCommons());
		pull_refresh_grid.setAdapter(comicAdapter);
		pull_refresh_grid.setMode(Mode.DISABLED);
		pull_refresh_grid.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {

			@Override
			public void onLastItemVisible() {
				// TODO Auto-generated method stub
				if (second_module.isMoreData()&&!isFootRefreshing) {
					isFootRefreshing = true;
					
					if (second_module.getDataNum() < second_module.getDataNumMax()) {
						System.out.println("num"+second_module.getDataNum());
						img_update.startAnimation(rotateAnimation);
						rly_FootRefreshTip.setVisibility(View.VISIBLE);
						new Thread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								try {
									Bean_common_moreData_Req<Bean_common_moreData> request = new Bean_common_moreData_Req<Bean_common_moreData>(
											new Bean_Request_Head(0), null);
									Bean_common_moreData common_moreData = new Bean_common_moreData(second_module
											.getDataNum());
									request.setMoreData(common_moreData);
									String requestJson = KyApplication.getApplication().gson.toJson(request);
									common_Res = KyApplication.getApplication().gson.fromJson(
											KyHttpClient.post(second_module.getMoreData_action(), requestJson),
											Bean_common_Res.class);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} finally {
									KyApplication.getApplication().handler.postDelayed(new Runnable() {

										@Override
										public void run() {
											// TODO Auto-generated method stub

											if (null != common_Res
													&& common_Res.getBean_second_modules().get(0).getCommons().size() > 0) {

												for (int i = 0; i < common_Res.getBean_second_modules().get(0)
														.getCommons().size(); i++) {
													second_module.getCommons().add(
															common_Res.getBean_second_modules().get(0).getCommons()
																	.get(i));
												}
												comicAdapter.notifyDataSetChanged();
												second_module.setDataNum(second_module.getDataNum() + 1);
												ToastUtils.showToast(getActivity(), "加载成功");
											}else{
												ToastUtils.showToast(getActivity(), "加载失败");
											}
											isFootRefreshing = false;
											rly_FootRefreshTip.setVisibility(View.GONE);
											img_update.clearAnimation();
											

										}
									},1000);
								}
							}
						}).start();
					} else {
						ToastUtils.showToast(getActivity(), "已无更多数据");
						isFootRefreshing = false;
					}
				}
			}
		});

	}

	private void initOnClickListener() {
		// TODO Auto-generated method stub
		comicAdapter.setOnClickListener(clickListener);
		if (null != adUrl && !"".equals(adUrl)) {
			wb_advert.setVisibility(View.VISIBLE);
			wb_advert.getSettings().setJavaScriptEnabled(true);
			wb_advert.loadUrl(adUrl);
		}
	}

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.img_cover:
				currentData = Integer.parseInt(v.getTag().toString());
				new AsyncDataLoader(callback).execute();

				break;

			default:
				break;
			}
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
			KyUtil.addLoadingWin(getActivity(), view_parent, preStatus);
		}

		@Override
		public void onStart() {
			// TODO Auto-generated method stub
			if (!KyUtil.connectivityIsAvailable(getActivity())) {
				netStatus = -1;
				return;
			}
			if (!KyUtil.pingIP(ServiceApi.IP)) {
				netStatus = 0;
				return;
			}
			try {
				Bean_common_Req request = new Bean_common_Req(
						new Bean_Request_Head(0), null);
				Bean_common_url common_request = new Bean_common_url(second_module.getCommons().get(currentData)
						.getDetail_url());
				request.setUrl(common_request);
				String requestJson = KyApplication.getApplication().gson.toJson(request);
				detail = KyApplication.getApplication().gson.fromJson(
						KyHttpClient.post(second_module.getCommons().get(currentData).getDetail_action(), requestJson),
						Bean_common_detail.class);
				netStatus = 1;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				netStatus = 2;
				times--;
				detail = null;
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
				KyUtil.removeLoadingWin(view_parent, null, false);
				ToastUtils.showToast(getActivity(), R.string.network_status_toast);
				break;
			case 0:
				KyUtil.removeLoadingWin(view_parent, null, false);
				ToastUtils.showToast(getActivity(), R.string.service_status_toast);
				break;
			case 1:
				KyUtil.removeLoadingWin(view_parent, new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (null != detail) {
							KyApplication.getApplication().common_detail = detail;
							Intent intent = new Intent(getActivity(), CommonDetailActivity.class);
							startActivity(intent);
						} else {
							ToastUtils.showToast(getActivity(), R.string.request_null_toast);
						}
					}
				}, true);

				break;
			case 2:
				KyUtil.removeLoadingWin(view_parent, null, false);
				ToastUtils.showToast(getActivity(), R.string.request_long_toast);
				break;
			}

		}
	};
}
