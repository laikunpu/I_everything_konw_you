package com.smith.activity;

import java.util.ArrayList;
import java.util.List;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.smith.adapter.LeftAdapter;
import com.smith.adapter.MidAdapter;
import com.smith.entity.Bean_common;
import com.smith.entity.Bean_common_Req;
import com.smith.entity.Bean_common_detail;
import com.smith.entity.Bean_common_url;
import com.smith.entity.heard.Bean_Request_Head;
import com.smith.inter.DataCallback;
import com.smith.util.AsyncDataLoader;
import com.smith.util.KyHttpClient;
import com.smith.util.KyUtil;
import com.smith.util.ProgressStatus;
import com.smith.util.ServiceApi;
import com.smith.util.ToastUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MidFragment extends Fragment implements MidDataCallback {

	private KyApplication application;
	private PullToRefreshListView pullToRefreshListView;
	private MidAdapter midAdapter;
	private List<Bean_common> commons;
	private Bean_common_detail detail;
	private LinearLayout view_parent;
	private int pos;
	
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
		view_parent = (LinearLayout) v.findViewById(R.id.view_parent);
	}

	private void initOnClickListener() {
		// TODO Auto-generated method stub
		pullToRefreshListView.setOnItemClickListener(itemClickListener);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	private void initData() {
		// TODO Auto-generated method stub
		application = KyApplication.getApplication();
		if (null != application.modules && application.modules.size() > 0&&null!=application.modules.get(0).getCommons()) {
			commons = new ArrayList<Bean_common>();
			commons.addAll(application.modules.get(0).getCommons());
			midAdapter = new MidAdapter(getActivity(), commons);
			pullToRefreshListView.setAdapter(midAdapter);
		}

	}

	@Override
	public void dataChanged(int postion) {
		// TODO Auto-generated method stub
		if (null != application.modules && application.modules.size() > 0&&null!=application.modules.get(postion).getCommons()) {
		commons.clear();
		System.out.println(application.modules.get(postion).getCommons().size());
		commons.addAll(application.modules.get(postion).getCommons());
		midAdapter.notifyDataSetChanged();
		}
	}

	OnItemClickListener itemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
			// TODO Auto-generated method stub
			pos=position-1;
			new AsyncDataLoader(callback).execute();
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
				Bean_common_Req request = new Bean_common_Req(new Bean_Request_Head(0), null);
				Bean_common_url common_request = new Bean_common_url(commons.get(pos).getDetail_url());
				request.setUrl(common_request);
				String requestJson = KyApplication.getApplication().gson.toJson(request);
				detail = KyApplication.getApplication().gson.fromJson(
						KyHttpClient.post(commons.get(pos).getDetail_action(), requestJson),
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

interface MidDataCallback {
	public void dataChanged(int postion);
}