package com.smith.activity;

import java.util.List;

import com.smith.adapter.LeftAdapter;
import com.smith.entity.Bean_common;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class LeftListFragment extends ListFragment implements LeftDataCallback {

	private LeftAdapter adapter;
	private KyApplication application;
	private SlidingCallback slidingCallback;

	public void setSlidingCallback(SlidingCallback slidingCallback) {
		this.slidingCallback = slidingCallback;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.left, null);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		initData();
	}

	private void initData() {
		// TODO Auto-generated method stub
		application = KyApplication.getApplication();
		adapter = new LeftAdapter(getActivity(), application.modules);
		setListAdapter(adapter);

	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		slidingCallback.SlidingToggle(position);

	}

	@Override
	public void dataChanged() {
		// TODO Auto-generated method stub
		adapter = new LeftAdapter(getActivity(), application.modules);
		setListAdapter(adapter);
	}

}

interface LeftDataCallback {
	public void dataChanged();
}
