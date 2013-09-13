package com.smith.activity;

import uk.co.senab.bitmapcache.samples.NetworkedCacheableImageView;

import com.smith.adapter.DetailContentsAdapter;
import com.smith.entity.Bean_common_detail;

import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class CommonDetailActivity extends BaseActivity {
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

		img_cover.loadImage(data.getCover_url(), true, null);
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

	}

}
