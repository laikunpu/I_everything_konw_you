package com.smith.activity;

import java.util.List;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.smith.adapter.CommonDetailNextAdapter;
import com.smith.entity.Bean_common_detail_content;
import com.smith.entity.Bean_common_page;
import com.smith.entity.Bean_common_page_Res;
import com.smith.inter.DataCallback;
import com.smith.util.AsyncDataLoader;

import android.content.pm.ActivityInfo;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;
import android.widget.TextView;

public class CommonDetailNextActivity extends BaseActivity {

	private PullToRefreshListView pullToRefreshListView;
	private CommonDetailNextAdapter commonDetailNextAdapter;

	private List<Bean_common_page> pages;
	private TextView txt_FootRefreshTip;
	private int start = 0;
	private int end = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		setContentView(R.layout.common_detail_next);

		initView();
		initData();
		initOnClickListener();
	}

	private void initView() {
		// TODO Auto-generated method stub
		pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_refresh_list);
		txt_FootRefreshTip = (TextView) findViewById(R.id.txt_FootRefreshTip);
	}

	private void initData() {
		// TODO Auto-generated method stub
		pages = KnowyouApplication.getApplication().common_page_Res.getBean_common_pages();
		commonDetailNextAdapter = new CommonDetailNextAdapter(CommonDetailNextActivity.this, pages);
		pullToRefreshListView.setAdapter(commonDetailNextAdapter);
	}

	private void initOnClickListener() {
		// TODO Auto-generated method stub

		pullToRefreshListView.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {

			@Override
			public void onLastItemVisible() {
				// TODO Auto-generated method stub
				// if (end < pages.size()) {
				// start = end;
				// end = end + 3 < pages.size() ? end + 3 : pages.size();
				// txt_FootRefreshTip.setVisibility(View.VISIBLE);
				// loadData(start, end);
				// }

			}
		});
	}

}
