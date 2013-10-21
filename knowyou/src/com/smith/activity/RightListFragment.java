package com.smith.activity;

import java.util.ArrayList;
import java.util.List;

import com.smith.adapter.RightAdapter;
import com.smith.util.ToastUtils;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class RightListFragment extends ListFragment{
	private RightAdapter adapter;
	private List<String> datas;


	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.right, null);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		initData();
	}

	private void initData() {
		// TODO Auto-generated method stub
		datas=new ArrayList<String>();
		datas.add("关于我们");
		datas.add("检查更新");
		adapter=new RightAdapter(getActivity(), datas);
		setListAdapter(adapter);
		
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		switch (position) {
		case 0:
			ToastUtils.showToast(getActivity(), "所有数据都是从网上收集的!");
			break;
		case 1:
			ToastUtils.showToast(getActivity(), "已经是最新版本!");
			break;
		default:
			break;
		}
	}
}
