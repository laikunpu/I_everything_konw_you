package com.smith.activity.abandon;

import com.smith.activity.CommonDetailActivity;
import com.smith.activity.KyApplication;
import com.smith.activity.R;
import com.smith.activity.R.id;
import com.smith.activity.R.layout;
import com.smith.activity.R.string;
import com.smith.adapter.abandon.CommonSearchAdapter;
import com.smith.entity.Bean_common_Req;
import com.smith.entity.Bean_common_detail;
import com.smith.entity.Bean_common_search_Res;
import com.smith.entity.Bean_common_sou;
import com.smith.entity.Bean_common_sou_Req;
import com.smith.entity.Bean_common_url;
import com.smith.entity.heard.Bean_Request_Head;
import com.smith.inter.DataCallback;
import com.smith.util.AsyncDataLoader;
import com.smith.util.KyHttpClient;
import com.smith.util.KyUtil;
import com.smith.util.ProgressStatus;
import com.smith.util.ServiceApi;
import com.smith.util.ToastUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class SearchActivity extends BaseActivity {

	private LinearLayout view_parent;
	private Bean_common_search_Res search_Res = null;
	private ListView lsv_search;
	private EditText etx_search;
	private ImageView img_search;
	private CommonSearchAdapter searchAdapter;
	private int postion=0;
	private Bean_common_detail detail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);

		initView();
		initData();
		initOnClickListener();
	}

	private void initView() {
		// TODO Auto-generated method stub
		view_parent = (LinearLayout) findViewById(R.id.view_parent);
		lsv_search = (ListView) findViewById(R.id.lsv_search);
		etx_search = (EditText) findViewById(R.id.etx_search);
		img_search = (ImageView) findViewById(R.id.img_search);
	}

	private void initData() {
		// TODO Auto-generated method stub
		search_Res = KyApplication.getApplication().search_Res;
		searchAdapter = new CommonSearchAdapter(this, search_Res.getBean_common_searchs());
		lsv_search.setAdapter(searchAdapter);
	}

	private void initOnClickListener() {
		// TODO Auto-generated method stub
		img_search.setOnClickListener(clickListener);
		lsv_search.setOnItemClickListener(itemClickListener);
	}
	
	OnClickListener clickListener=	new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.img_search:
				if(etx_search.getText().toString().trim().equals("")){
					ToastUtils.showToast(SearchActivity.this, "搜索词不能为空");
				}else{
					new AsyncDataLoader(callback).execute();
				}
				break;
			case R.id.img_cover:


				break;
			}
			
		}
	};

	
	OnItemClickListener itemClickListener=new OnItemClickListener(){

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// TODO Auto-generated method stub
			postion = position;
			new AsyncDataLoader(detailCallback).execute();
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
			KyUtil.addLoadingWin(SearchActivity.this, view_parent, preStatus);
		}

		@Override
		public void onStart() {
			// TODO Auto-generated method stub
			if (!KyUtil.connectivityIsAvailable(SearchActivity.this)) {
				netStatus = -1;
				return;
			}
			if (!KyUtil.pingIP(ServiceApi.IP)) {
				netStatus = 0;
				return;
			}
			try {
				Bean_common_sou_Req req = new Bean_common_sou_Req(
						new Bean_Request_Head(0), null);
				Bean_common_sou sou = new Bean_common_sou(etx_search.getText().toString().trim());
				req.setCommon_sou(sou);
				String requestJson = KyApplication.getApplication().gson.toJson(req);
				search_Res = KyApplication.getApplication().gson.fromJson(
						KyHttpClient.post(KyApplication.getApplication().common_Res.getSearchAction(), requestJson),
						Bean_common_search_Res.class);
				netStatus = 1;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				netStatus = 2;
				times--;
				search_Res = null;
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
				ToastUtils.showToast(SearchActivity.this, R.string.network_status_toast);
				break;
			case 0:
				KyUtil.removeLoadingWin(view_parent, null, false);
				ToastUtils.showToast(SearchActivity.this, R.string.service_status_toast);
				break;
			case 1:
				KyUtil.removeLoadingWin(view_parent, new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (null != search_Res&&null!=search_Res.getBean_common_searchs()&&search_Res.getBean_common_searchs().size()>0) {
							KyApplication.getApplication().search_Res = search_Res;
							searchAdapter=new CommonSearchAdapter(SearchActivity.this, search_Res.getBean_common_searchs());
							lsv_search.setAdapter(searchAdapter);
							searchAdapter.notifyDataSetChanged();
						} else {
							ToastUtils.showToast(SearchActivity.this, R.string.request_null_toast);
						}
					}
				}, true);

				break;
			case 2:
				KyUtil.removeLoadingWin(view_parent, null, false);
				ToastUtils.showToast(SearchActivity.this, R.string.request_long_toast);
				break;
			}

		}
	};
	
	
	
	DataCallback detailCallback = new DataCallback() {
		private int times;
		private ProgressStatus preStatus;
		private int netStatus = 0;

		@Override
		public void onPrepare() {
			// TODO Auto-generated method stub
			times = 3;
			preStatus = new ProgressStatus();
			KyUtil.addLoadingWin(SearchActivity.this, view_parent, preStatus);
		}

		@Override
		public void onStart() {
			// TODO Auto-generated method stub
			if (!KyUtil.connectivityIsAvailable(SearchActivity.this)) {
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
				Bean_common_url common_request = new Bean_common_url(search_Res.getBean_common_searchs().get(postion).getDetail_url());
				request.setUrl(common_request);
				String requestJson = KyApplication.getApplication().gson.toJson(request);
				detail = KyApplication.getApplication().gson.fromJson(
						KyHttpClient.post(search_Res.getBean_common_searchs().get(postion).getAction(), requestJson),
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
				ToastUtils.showToast(SearchActivity.this, R.string.network_status_toast);
				break;
			case 0:
				KyUtil.removeLoadingWin(view_parent, null, false);
				ToastUtils.showToast(SearchActivity.this, R.string.service_status_toast);
				break;
			case 1:
				KyUtil.removeLoadingWin(view_parent, new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (null != detail) {
							KyApplication.getApplication().common_detail = detail;
							Intent intent = new Intent(SearchActivity.this, CommonDetailActivity.class);
							startActivity(intent);
						} else {
							ToastUtils.showToast(SearchActivity.this, R.string.request_null_toast);
						}
					}
				}, true);

				break;
			case 2:
				KyUtil.removeLoadingWin(view_parent, null, false);
				ToastUtils.showToast(SearchActivity.this, R.string.request_long_toast);
				break;
			}

		}
	};
	
	
	
}
