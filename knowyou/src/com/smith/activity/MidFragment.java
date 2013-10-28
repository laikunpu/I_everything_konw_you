package com.smith.activity;

import java.util.ArrayList;
import java.util.List;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.smith.adapter.LeftAdapter;
import com.smith.adapter.MidAdapter;
import com.smith.db.DBHelper;
import com.smith.db.DaoImpl;
import com.smith.db.IDao;
import com.smith.entity.Bean_common;
import com.smith.entity.Bean_common_Req;
import com.smith.entity.Bean_common_Res;
import com.smith.entity.Bean_common_detail;
import com.smith.entity.Bean_common_moreData;
import com.smith.entity.Bean_common_moreData_Req;
import com.smith.entity.Bean_common_url;
import com.smith.entity.Bean_module;
import com.smith.entity.Bean_module_Res;
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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MidFragment extends Fragment implements MidDataCallback {

	private KyApplication application;
	private PullToRefreshListView pullToRefreshListView;
	private LinearLayout lly_tryAgain;
	private Button btn_tryAgain;
	private MidAdapter midAdapter;
	private List<Bean_common> commons;

	private Bean_common_detail detail;
	private RelativeLayout view_parent;
	private int pos;

	private Bean_module module;
	private Bean_common_Res common_Res;
	private RelativeLayout rly_FootRefreshTip;
	private ImageView img_update;
	private boolean isRefreshing = false;
	private Animation rotateAnimation = null;

	private IDao dao;
	
	public LeftDataCallback leftDataCallback;

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
		view_parent = (RelativeLayout) v.findViewById(R.id.view_parent);
		lly_tryAgain = (LinearLayout) v.findViewById(R.id.lly_tryAgain);
		btn_tryAgain = (Button) v.findViewById(R.id.btn_tryAgain);

		rly_FootRefreshTip = (RelativeLayout) v.findViewById(R.id.rly_FootRefreshTip);
		img_update = (ImageView) v.findViewById(R.id.img_update);
	}

	private void initData() {
		// TODO Auto-generated method stub
		dao = new DaoImpl(getActivity());
		application = KyApplication.getApplication();
		if (null != application.modules && application.modules.size() > 0
				&& null != application.modules.get(0).getCommons()) {
			module = application.modules.get(0);
			commons = new ArrayList<Bean_common>();
			commons.addAll(application.modules.get(0).getCommons());
			midAdapter = new MidAdapter(getActivity(), commons);
			pullToRefreshListView.setAdapter(midAdapter);
		} else {
			pullToRefreshListView.setVisibility(View.GONE);
			lly_tryAgain.setVisibility(View.VISIBLE);
		}
		rotateAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_anim);
	}

	private void initOnClickListener() {
		// TODO Auto-generated method stub
		pullToRefreshListView.setOnItemClickListener(itemClickListener);

		pullToRefreshListView.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
				pullToRefreshListView.postDelayed(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						refreshData(true);
					}
				}, 1000);

			};

		});

		// Add an end-of-list listener
		pullToRefreshListView.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {

			@Override
			public void onLastItemVisible() {

				refreshData(false);
			}
		});

		btn_tryAgain.setOnClickListener(clickListener);
	}

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btn_tryAgain:
				new AsyncDataLoader(mainDataCallback).execute();
				break;

			default:
				break;
			}
		}
	};

	private void refreshData(final boolean isTopRefresh) {
		if (!KyUtil.connectivityIsAvailable(getActivity())) {
			if (isTopRefresh) {
				pullToRefreshListView.onRefreshComplete();
				ToastUtils.showToast(getActivity(), "无法连接网络!");
			}
			return;
		}

		if (null != module && module.isMoreData() && !isRefreshing) {
			isRefreshing = true;

			if (module.getDataNum() < module.getDataNumMax()) {
				if (!isTopRefresh) {
					img_update.startAnimation(rotateAnimation);
					rly_FootRefreshTip.setVisibility(View.VISIBLE);
				}

				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Bean_common_moreData_Req request = new Bean_common_moreData_Req(new Bean_Request_Head(0),
									null);
							Bean_common_moreData common_moreData = new Bean_common_moreData(module.getUrl(), module
									.getDataNum());
							request.setMoreData(common_moreData);
							String requestJson = KyApplication.getApplication().gson.toJson(request);
							common_Res = null;
							common_Res = KyApplication.getApplication().gson.fromJson(
									KyHttpClient.post(module.getMoreData_action(), requestJson), Bean_common_Res.class);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} finally {
							KyApplication.getApplication().handler.postDelayed(new Runnable() {

								@Override
								public void run() {
									// TODO Auto-generated method stub

									if (null != common_Res && common_Res.getBean_commons().size() > 0) {
										if (isTopRefresh) {
											commons.addAll(0, common_Res.getBean_commons());
										} else {
											commons.addAll(common_Res.getBean_commons());
										}

										midAdapter.notifyDataSetChanged();
										module.setDataNum(module.getDataNum() + 1);
										ToastUtils.showToast(getActivity(), "加载成功!");
									} else {
										ToastUtils.showToast(getActivity(), "加载失败!");
									}

									if (isTopRefresh) {
										pullToRefreshListView.onRefreshComplete();
									} else {
										rly_FootRefreshTip.setVisibility(View.GONE);
										img_update.clearAnimation();
									}

									isRefreshing = false;

								}
							}, 1000);
						}
					}
				}).start();
			} else {
				ToastUtils.showToast(getActivity(), "已无更多数据!");
				isRefreshing = false;
				if (isTopRefresh) {
					pullToRefreshListView.onRefreshComplete();
				}

			}
		} else {
			if (isTopRefresh) {
				pullToRefreshListView.onRefreshComplete();
			}
		}

	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void dataChanged(int postion) {
		// TODO Auto-generated method stub
		if (null != application.modules && application.modules.size() > 0
				&& null != application.modules.get(postion).getCommons()) {
			ListView mlist = pullToRefreshListView.getRefreshableView();
			if (!(mlist).isStackFromBottom()) {
				mlist.setStackFromBottom(true);
			}
			mlist.setStackFromBottom(false);
			module = application.modules.get(postion);
			commons.clear();
			commons.addAll(application.modules.get(postion).getCommons());
			midAdapter.notifyDataSetChanged();

		}
	}

	@Override
	public void dataForSearch(List<Bean_common> commons) {
		// TODO Auto-generated method stub
		module = null;
		this.commons.clear();
		this.commons.addAll(commons);
		midAdapter.notifyDataSetChanged();
	}

	OnItemClickListener itemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
			// TODO Auto-generated method stub
			pos = position - 1;
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
						KyHttpClient.post(commons.get(pos).getDetail_action(), requestJson), Bean_common_detail.class);
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

	DataCallback mainDataCallback = new DataCallback() {
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
			List<Bean_module> modules = null;
			try {

				Bean_module_Res module_Res = KyApplication.getApplication().gson.fromJson(
						KyHttpClient.get(ServiceApi.MODULE), Bean_module_Res.class);
				KyApplication.getApplication().module_Res = module_Res;
				modules = module_Res.getModules();
				if (null != modules) {
					application.modules = modules;
					dao.delete_table(DBHelper.TABLE_BEAN_MODULE);
					dao.delete_table(DBHelper.TABLE_BEAN_COMMON);
					for (int i = 0; i < modules.size(); i++) {
						dao.add_module(modules.get(i));
					}
				}
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
						if (null != application.modules && application.modules.size() > 0
								&& null != application.modules.get(0).getCommons()) {
							pullToRefreshListView.setVisibility(View.VISIBLE);
							lly_tryAgain.setVisibility(View.GONE);
							module = application.modules.get(0);
							commons = new ArrayList<Bean_common>();
							commons.addAll(application.modules.get(0).getCommons());
							midAdapter = new MidAdapter(getActivity(), commons);
							pullToRefreshListView.setAdapter(midAdapter);
							if(null!=leftDataCallback){
								leftDataCallback.dataChanged();
							}
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

	public void dataForSearch(List<Bean_common> commons);
}