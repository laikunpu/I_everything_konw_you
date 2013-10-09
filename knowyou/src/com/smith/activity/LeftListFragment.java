package com.smith.activity;

import com.smith.adapter.LeftAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LeftListFragment extends ListFragment {

	private LeftAdapter adapter;
	private KyApplication application;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list, null);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		initData();
	}

	private void initData() {
		// TODO Auto-generated method stub
		application=KyApplication.getApplication();
		adapter=new LeftAdapter(getActivity(), application.modules);
		setListAdapter(adapter);
	}

}
