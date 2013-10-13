package com.smith.activity;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.bitmapcache.CacheableBitmapDrawable;
import uk.co.senab.bitmapcache.samples.NetworkedCacheableImageView.OnImageLoadedListener;

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

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CommonDetailNextActivity extends Activity {

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

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
		rotateAnimation= AnimationUtils.loadAnimation(CommonDetailNextActivity.this, R.anim.rotate_anim);
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

}