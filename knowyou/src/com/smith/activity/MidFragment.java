package com.smith.activity;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.smith.adapter.LeftAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MidFragment extends Fragment {

	private KyApplication application;
	private PullToRefreshListView pullToRefreshListView;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.midlist, null);

		initView(v);
		initData();
		initOnClickListener();
		return v;
	}

	private void initView(View v) {
		// TODO Auto-generated method stub
		pullToRefreshListView = (PullToRefreshListView) v.findViewById(R.id.pull_refresh_list);
	}

	private void initOnClickListener() {
		// TODO Auto-generated method stub
		
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	private void initData() {
		// TODO Auto-generated method stub
		application = KyApplication.getApplication();
		
	}

}
