package com.smith.activity;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.bitmapcache.CacheableBitmapDrawable;
import uk.co.senab.bitmapcache.samples.NetworkedCacheableImageView.OnImageLoadedListener;

import com.actionbarsherlock.ActionBarSherlock;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.smith.activity.R;
import com.smith.activity.R.anim;
import com.smith.activity.R.id;
import com.smith.activity.R.layout;
import com.smith.adapter.CommonDetailNextAdapter;
import com.smith.entity.Bean_common_detail_content;
import com.smith.entity.Bean_common_page;
import com.smith.entity.Bean_common_page_Res;
import com.smith.inter.DataCallback;
import com.smith.util.AsyncDataLoader;
import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CommonDetailNextActivity extends SherlockFragmentActivity {

	private PullToRefreshListView pullToRefreshListView;
	private CommonDetailNextAdapter commonDetailNextAdapter;

	private List<Bean_common_page> pages;
	private List<Bean_common_page> show_pages;
	private RelativeLayout rly_FootRefreshTip;
	private ImageView img_update;
	private int refreshStart = 0;
	private int refreshEnd = 5;
	private int loadImageResult = 0;
	private boolean isFootRefreshing = false;
	private Animation rotateAnimation = null;
	private TextView txt_page;
	private TextView txt_chapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		/* set it to be full screen */
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		getSupportActionBar().hide();
		setContentView(R.layout.common_detail_next);

		initView();
		initData();
		initOnClickListener();
	}

	private void initView() {
		// TODO Auto-generated method stub
		pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_refresh_list);
		rly_FootRefreshTip = (RelativeLayout) findViewById(R.id.rly_FootRefreshTip);
		img_update = (ImageView) findViewById(R.id.img_update);

		txt_page = (TextView) findViewById(R.id.txt_page);
		txt_chapter = (TextView) findViewById(R.id.txt_chapter);
	}

	private void initData() {
		// TODO Auto-generated method stub
		pages = KyApplication.getApplication().common_page_Res.getBean_common_pages();
		show_pages = new ArrayList<Bean_common_page>();
		for (int i = refreshStart; i < (refreshEnd < pages.size() ? refreshEnd : pages.size()); i++) {
			show_pages.add(pages.get(i));
		}

		commonDetailNextAdapter = new CommonDetailNextAdapter(CommonDetailNextActivity.this, show_pages);
		commonDetailNextAdapter.setOnImageLoadedListener(loadedListener);
		pullToRefreshListView.setAdapter(commonDetailNextAdapter);
		pullToRefreshListView.setMode(Mode.DISABLED);
		rotateAnimation = AnimationUtils.loadAnimation(CommonDetailNextActivity.this, R.anim.rotate_anim);

		
		txt_chapter.setText(getIntent().getStringExtra("chapterName"));
		pullToRefreshListView.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
				txt_page.setText((firstVisibleItem + 1) + "/" + (pages.size() + 1));
			}
		});

	}

	private void initOnClickListener() {
		// TODO Auto-generated method stub

		pullToRefreshListView.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {

			@Override
			public void onLastItemVisible() {
				// TODO Auto-generated method stub
				if (refreshStart < refreshEnd && refreshEnd < pages.size() && !isFootRefreshing) {
					isFootRefreshing = true;
					refreshStart = refreshEnd;
					refreshEnd = refreshEnd + 3 < pages.size() ? refreshEnd + 3 : pages.size();
					img_update.startAnimation(rotateAnimation);
					rly_FootRefreshTip.setVisibility(View.VISIBLE);
					KyApplication.getApplication().handler.postDelayed(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub

							for (int i = refreshStart; i < refreshEnd; i++) {
								show_pages.add(pages.get(i));
							}

							isFootRefreshing = false;
							commonDetailNextAdapter.notifyDataSetChanged();
							rly_FootRefreshTip.setVisibility(View.GONE);
							img_update.clearAnimation();

						}
					}, 3000);
				}

			}

		});
	}

	OnImageLoadedListener loadedListener = new OnImageLoadedListener() {

		@Override
		public void onImageLoaded(CacheableBitmapDrawable result) {
			// TODO Auto-generated method stub
			System.out.println("result");
		}
	};
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onResume(this);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPause(this);
	}

}
