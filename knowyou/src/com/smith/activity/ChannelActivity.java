package com.smith.activity;

import java.util.ArrayList;
import java.util.List;

import com.smith.adapter.ComicAdapter;
import com.smith.entity.Bean_common;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.GridView;

public class ChannelActivity extends BaseActivity {

	private WebView wb_advert;
	private GridView gd_list;
	
	private List<Bean_common> datas;
	private ComicAdapter comicAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.channel);
		
		initView();
		initData();
		initOnClickListener();
	}

	private void initView() {
		// TODO Auto-generated method stub
		wb_advert=(WebView)findViewById(R.id.wb_advert);
		gd_list=(GridView)findViewById(R.id.gd_list);
	}

	private void initData() {
		// TODO Auto-generated method stub
		datas=new ArrayList<Bean_common>();
		setData();
		comicAdapter=new ComicAdapter(this, datas);
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
