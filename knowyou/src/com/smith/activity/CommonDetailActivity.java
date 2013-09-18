package com.smith.activity;

import uk.co.senab.bitmapcache.samples.NetworkedCacheableImageView;

import com.smith.adapter.DetailContentsAdapter;
import com.smith.entity.Bean_common_detail;
import com.smith.entity.Bean_common_page_Res;
import com.smith.inter.DataCallback;
import com.smith.util.AsyncDataLoader;
import com.smith.util.KYHttpClient;
import com.smith.util.KnowyouUtil;
import com.smith.util.ProgressStatus;
import com.smith.util.ServiceApi;
import com.smith.util.ToastUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class CommonDetailActivity extends BaseActivity {

	private LinearLayout view_parent;

	private NetworkedCacheableImageView img_cover;
	private TextView txt_bookName;
	private TextView txt_bookAuthor;
	private TextView txt_modifiedTime;
	private TextView txt_summary;
	private TextView txt_related;
	private TextView txt_comment;

	private Button btn_online;
	private Button btn_download;

	private ScrollView sc_contents;
	private GridView gd_contents;

	private Bean_common_detail data;
	private DetailContentsAdapter contentsAdapter;

	private Bean_common_page_Res common_page_Res;

	private int currentData = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.common_detail);

		initView();
		initData();
		initOnClickListener();
	}

	private void initView() {
		// TODO Auto-generated method stub

		view_parent = (LinearLayout) findViewById(R.id.view_parent);

		img_cover = (NetworkedCacheableImageView) findViewById(R.id.img_cover);
		txt_bookName = (TextView) findViewById(R.id.txt_bookName);
		txt_bookAuthor = (TextView) findViewById(R.id.txt_bookAuthor);
		txt_modifiedTime = (TextView) findViewById(R.id.txt_modifiedTime);
		txt_summary = (TextView) findViewById(R.id.txt_summary);
		txt_related = (TextView) findViewById(R.id.txt_related);
		txt_comment = (TextView) findViewById(R.id.txt_comment);
		btn_online = (Button) findViewById(R.id.btn_online);
		btn_download = (Button) findViewById(R.id.btn_download);

		gd_contents = (GridView) findViewById(R.id.gd_contents);
		sc_contents = (ScrollView) findViewById(R.id.sc_contents);
	}

	private void initData() {
		// TODO Auto-generated method stub
		data = KnowyouApplication.getApplication().common_detail;

		img_cover.loadImage(data.getCover_url(), true, null, null);
		txt_bookName.setText(data.getName());
		txt_bookAuthor.setText(data.getAuthor());
		txt_modifiedTime.setText(data.getModify());
		txt_summary.setText(data.getSummary());

		txt_bookName.setText(data.getName());

		if (data != null && null != data.getContents() && data.getContents().size() > 0) {
			contentsAdapter = new DetailContentsAdapter(this, data.getContents());
			gd_contents.setAdapter(contentsAdapter);
			sc_contents.post(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					sc_contents.scrollTo(0, 0);
				}
			});

		}

	}

	private void initOnClickListener() {
		// TODO Auto-generated method stub
		contentsAdapter.setOnClickListener(clickListener);
		if (null != data.getOnline()) {
			btn_online.setEnabled(true);
			btn_online.setText(data.getOnline().getOnlineText());
			btn_online.setOnClickListener(clickListener);
		}
		if (null != data.getDownload()) {

		}
	}

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btn_content:
				currentData = Integer.parseInt(v.getTag().toString());
				KnowyouApplication.getApplication().content = data.getContents().get(currentData);
				new AsyncDataLoader(callback).execute();
				break;
			case R.id.btn_online:
				switch (data.getOnline().getOnlineType()) {
				case 1:
					KnowyouApplication.getApplication().content = data.getContents().get(0);
					new AsyncDataLoader(callback).execute();
					break;
				}
				break;
			default:
				break;
			}
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
			KnowyouUtil.addLoadingWin(CommonDetailActivity.this, view_parent, preStatus);
		}

		@Override
		public void onStart() {
			// TODO Auto-generated method stub

			if (!KnowyouUtil.connectivityIsAvailable(CommonDetailActivity.this)) {
				netStatus = -1;
				return;
			}
			if (!KnowyouUtil.pingIP(ServiceApi.IP)) {
				netStatus = 0;
				return;
			}
			try {
				String json = KYHttpClient.post(data.getContents().get(currentData).getAction(), data.getContents()
						.get(currentData).getContet_url());
				common_page_Res = KnowyouApplication.getApplication().gson.fromJson(json, Bean_common_page_Res.class);
				netStatus = 1;
			} catch (Exception e) {
				// TODO Auto-generated catch block

				e.printStackTrace();
				netStatus = 2;
				times--;
				common_page_Res = null;
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
				KnowyouUtil.removeLoadingWin(view_parent);
				ToastUtils.showToast(CommonDetailActivity.this, "无法连接网络,请确认手机处于联网状态!!!");
				break;
			case 0:
				KnowyouUtil.removeLoadingWin(view_parent);
				ToastUtils.showToast(CommonDetailActivity.this, "服务器尚未开启!!!");
				break;
			case 1:
				KnowyouUtil.removeLoadingWin(view_parent, new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (null != common_page_Res) {
							KnowyouApplication.getApplication().common_page_Res = common_page_Res;
							Intent intent = new Intent(CommonDetailActivity.this, CommonDetailNextActivity.class);
							startActivity(intent);
						} else {
							ToastUtils.showToast(CommonDetailActivity.this, "网络异常!!!");
						}
					}
				});

				break;
			case 2:
				KnowyouUtil.removeLoadingWin(view_parent);
				ToastUtils.showToast(CommonDetailActivity.this, "服务器正在收集该内容，请稍后尝试!!!");
				break;
			}

		}
	};

}
