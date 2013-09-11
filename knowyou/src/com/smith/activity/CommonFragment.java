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

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.GridView;

import com.smith.adapter.ComicAdapter;
import com.smith.entity.Bean_common;

public class CommonFragment extends Fragment {
	private int classify;
	
	private WebView wb_advert;
	private GridView gd_list;
	
	private List<Bean_common> datas;
	private ComicAdapter comicAdapter;
	

	/**
	 * When creating, retrieve this instance's number from its arguments.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		classify = getArguments().getInt("classify",0);
		System.out.println("CountingFragment->onCreate  name=" + classify);
		
		


	}

	/**
	 * The Fragment's UI is just a simple text view showing its instance number.
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		System.out.println("CountingFragment->onCreateView  classify=" + classify);

		View v = inflater.inflate(R.layout.channel, container, false);
		
		initView(v);
		initData();
		initOnClickListener();
		return v;
	}
	private void initView(View v) {
		// TODO Auto-generated method stub
		wb_advert=(WebView)v.findViewById(R.id.wb_advert);
		gd_list=(GridView)v.findViewById(R.id.gd_list);
	}

	private void initData() {
		// TODO Auto-generated method stub
		datas=new ArrayList<Bean_common>();
		setData();
		comicAdapter=new ComicAdapter(getActivity(), datas);
		gd_list.setAdapter(comicAdapter);
	}

	private void initOnClickListener() {
		// TODO Auto-generated method stub

	}
	
	private void setData(){
		Bean_common data1=new Bean_common("海贼王", 0, "", "", "", "", "");
		Bean_common data2=new Bean_common("海贼王", 0, "", "", "", "", "");
		Bean_common data3=new Bean_common("海贼王", 0, "", "", "", "", "");
		Bean_common data4=new Bean_common("海贼王", 0, "", "", "", "", "");
		Bean_common data5=new Bean_common("海贼王", 0, "", "", "", "", "");
		Bean_common data6=new Bean_common("海贼王", 0, "", "", "", "", "");
		Bean_common data7=new Bean_common("海贼王", 0, "", "", "", "", "");
		Bean_common data8=new Bean_common("海贼王", 0, "", "", "", "", "");
		Bean_common data9=new Bean_common("海贼王", 0, "", "", "", "", "");
		datas.add(data1);
		datas.add(data2);
		datas.add(data3);
		datas.add(data4);
		datas.add(data5);
		datas.add(data6);
		datas.add(data7);
		datas.add(data8);
		datas.add(data9);
	}
}
