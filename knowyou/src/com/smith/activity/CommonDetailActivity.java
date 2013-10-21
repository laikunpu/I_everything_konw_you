package com.smith.activity;

import uk.co.senab.bitmapcache.samples.NetworkedCacheableImageView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.smith.activity.R;
import com.smith.adapter.DetailContentsAdapter;
import com.smith.entity.Bean_common_Req;
import com.smith.entity.Bean_common_detail;
import com.smith.entity.Bean_common_page_Res;
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
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
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
	private CheckBox cbx_summary;

	private Button btn_online;
	private Button btn_download;

	private ScrollView sc_contents;
	private GridView gd_contents;

	private Bean_common_detail data;
	private DetailContentsAdapter contentsAdapter;

	private Bean_common_page_Res common_page_Res;

	private int currentData = 0;
	private int line;
	private String chapterName;

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
		TextPaint tp = txt_bookName.getPaint();
		tp.setFakeBoldText(true);
		txt_bookAuthor = (TextView) findViewById(R.id.txt_bookAuthor);
		txt_modifiedTime = (TextView) findViewById(R.id.txt_modifiedTime);
		txt_summary = (TextView) findViewById(R.id.txt_summary);
		cbx_summary = (CheckBox) findViewById(R.id.cbx_summary);
		btn_online = (Button) findViewById(R.id.btn_online);
		btn_download = (Button) findViewById(R.id.btn_download);

		gd_contents = (GridView) findViewById(R.id.gd_contents);
		sc_contents = (ScrollView) findViewById(R.id.sc_contents);
	}

	private void initData() {
		// TODO Auto-generated method stub

		data = KyApplication.getApplication().common_detail;

		img_cover.loadImage(data.getCover_url(), true, null, null);
		txt_bookName.setText(data.getName());
		txt_bookAuthor.setText(data.getAuthor());
		txt_modifiedTime.setText(data.getModify());
		txt_summary.setText(data.getSummary());
		txt_summary.post(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (txt_summary.getLineCount() > 3) {
					txt_summary.setLines(3);
				}

			}
		});

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

		txt_summary.setOnClickListener(clickListener);
		cbx_summary.setOnCheckedChangeListener(checkedChangeListener);
		btn_back.setOnClickListener(clickListener);
	}

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btn_content:
				currentData = Integer.parseInt(v.getTag().toString());
				KyApplication.getApplication().content = data.getContents().get(currentData);
				chapterName=data.getContents().get(currentData).getContet_name();
				new AsyncDataLoader(callback).execute();
				break;
			case R.id.btn_online:
				switch (data.getOnline().getOnlineType()) {
				case 1:
					KyApplication.getApplication().content = data.getContents().get(0);
					chapterName=data.getContents().get(0).getContet_name();
					new AsyncDataLoader(callback).execute();
					break;
				}
				break;
			case R.id.txt_summary:
				cbx_summary.setChecked(!cbx_summary.isChecked());
				break;
			case R.id.btn_back:
				finish();
				break;
			default:
				break;
			}
		}
	};

	OnCheckedChangeListener checkedChangeListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// TODO Auto-generated method stub
			switch (buttonView.getId()) {
			case R.id.cbx_summary:
				if (isChecked) {
					txt_summary.setLines(txt_summary.getLineCount());
				} else {
					if (txt_summary.getLineCount() > 3) {
						txt_summary.setLines(3);
					}

				}
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
			times = 1;
			preStatus = new ProgressStatus();
			KyUtil.addLoadingWin(CommonDetailActivity.this, view_parent, preStatus);
		}

		@Override
		public void onStart() {
			// TODO Auto-generated method stub

			if (!KyUtil.connectivityIsAvailable(CommonDetailActivity.this)) {
				netStatus = -1;
				return;
			}
			if (!KyUtil.pingIP(ServiceApi.IP)) {
				netStatus = 0;
				return;
			}
			try {

				Bean_common_Req request = new Bean_common_Req(new Bean_Request_Head(0), null);
				Bean_common_url common_request = new Bean_common_url(data.getContents().get(currentData)
						.getContet_url());
				request.setUrl(common_request);
				String requestJson = KyApplication.getApplication().gson.toJson(request);

				String json = KyHttpClient.post(data.getContents().get(currentData).getAction(), requestJson);
				common_page_Res = KyApplication.getApplication().gson.fromJson(json, Bean_common_page_Res.class);
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
				KyUtil.removeLoadingWin(view_parent, null, false);
				ToastUtils.showToast(CommonDetailActivity.this, R.string.network_status_toast);
				break;
			case 0:
				KyUtil.removeLoadingWin(view_parent, null, false);
				ToastUtils.showToast(CommonDetailActivity.this, R.string.service_status_toast);
				break;
			case 1:
				KyUtil.removeLoadingWin(view_parent, new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (null != common_page_Res && null != common_page_Res.getBean_common_pages()
								&& common_page_Res.getBean_common_pages().size() > 0) {
							KyApplication.getApplication().common_page_Res = common_page_Res;
							Intent intent = new Intent(CommonDetailActivity.this, CommonDetailNextActivity.class);
							intent.putExtra("chapterName", chapterName);
							startActivity(intent);
						} else {
							ToastUtils.showToast(CommonDetailActivity.this, "服务器已经在收集该内容,请稍后尝试!");
						}
					}
				}, true);

				break;
			case 2:
				KyUtil.removeLoadingWin(view_parent, null, false);
				ToastUtils.showToast(CommonDetailActivity.this, R.string.request_long_toast);
				break;
			}

		}
	};

}
