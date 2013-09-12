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

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.smith.adapter.CommonAdapter;
import com.smith.entity.Bean_common;
import com.smith.entity.Bean_common_Res;
import com.smith.inter.DataCallback;
import com.smith.util.AsyncDataLoader;
import com.smith.util.KYHttpClient;
import com.smith.util.KnowyouUtil;
import com.smith.util.ServiceApi;
import com.smith.util.ToastUtils;

public class CommonModuleFragment extends Fragment {
	private int classify;
	private LinearLayout view_parent;
	private WebView wb_advert;
	private GridView gd_list;

	private List<Bean_common> datas;
	private CommonAdapter comicAdapter;

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
		gd_list = (GridView) v.findViewById(R.id.gd_list);
		view_parent = (LinearLayout) v.findViewById(R.id.view_parent);
	}

	private void initData() {
		// TODO Auto-generated method stub
		datas = KnowyouApplication.getApplication().common_Res.getBean_second_modules().get(classify).getCommons();
		comicAdapter = new CommonAdapter(getActivity(), datas);
		gd_list.setAdapter(comicAdapter);
	}

	private void initOnClickListener() {
		// TODO Auto-generated method stub

	}

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.img_cover:
				new AsyncDataLoader(callback).execute();

				break;

			default:
				break;
			}
		}
	};
	DataCallback callback = new DataCallback() {

		@Override
		public void onPrepare() {
			// TODO Auto-generated method stub

			KnowyouUtil.addLoadingWin(getActivity(), view_parent);
		}

		@Override
		public void onStart() {
			// TODO Auto-generated method stub
			try {
//				if (null == common_Res || common_Res.getBean_second_modules().size() == 0) {
//					common_Res = KnowyouApplication.getApplication().gson.fromJson(
//							KYHttpClient.get(ServiceApi.RECOMMEND_MANHUA), Bean_common_Res.class);
//				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void onFinish() {
			// TODO Auto-generated method stub

//			if (null != common_Res && common_Res.getBean_second_modules().size() > 0) {
//				KnowyouApplication.getApplication().common_Res = common_Res;
//				Intent intent = new Intent(MainActivity.this, FragmentTabsPager.class);
//				startActivity(intent);
//			} else {
//				ToastUtils.showToast(getActivity(), "网络异常!!!");
//			}

			KnowyouUtil.removeLoadingWin(view_parent);

		}
	};
}