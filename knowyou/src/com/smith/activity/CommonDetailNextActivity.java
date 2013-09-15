package com.smith.activity;

import java.util.List;

import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.smith.adapter.CommonDetailNextAdapter;
import com.smith.entity.Bean_common_detail_content;
import com.smith.entity.Bean_common_page;
import com.smith.entity.Bean_common_page_Res;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

public class CommonDetailNextActivity extends BaseActivity {

	private PullToRefreshListView pullToRefreshListView;
	private CommonDetailNextAdapter commonDetailNextAdapter;

	private List<Bean_common_page> pages;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
//		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
		
		setContentView(R.layout.common_detail_next);

		initView();
		initData();
		initOnClickListener();
	}

	private void initView() {
		// TODO Auto-generated method stub
		pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_refresh_list);
	}

	private void initData() {
		// TODO Auto-generated method stub
		pages = KnowyouApplication.getApplication().common_page_Res.getBean_common_pages();
		commonDetailNextAdapter=new CommonDetailNextAdapter(CommonDetailNextActivity.this, pages);
		pullToRefreshListView.setAdapter(commonDetailNextAdapter);
	}

	private void initOnClickListener() {
		// TODO Auto-generated method stub

	}

}
