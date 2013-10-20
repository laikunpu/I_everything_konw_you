package com.smith.activity;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.smith.entity.Bean_common_Res;
import com.smith.entity.Bean_common_sou;
import com.smith.entity.Bean_common_sou_Req;
import com.smith.entity.heard.Bean_Request_Head;
import com.smith.inter.DataCallback;
import com.smith.util.AsyncDataLoader;
import com.smith.util.KyHttpClient;
import com.smith.util.KyUtil;
import com.smith.util.ProgressStatus;
import com.smith.util.ServiceApi;
import com.smith.util.ToastUtils;

public class MainActivity extends MainBaseActivity implements SlidingCallback {
	
	private long exitTime = 0;
	
	private MidFragment midFragment;
	private FrameLayout content_frame;

	private ImageView img_search;
	private EditText etx_search;
	private Button btn_home;
	private Bean_common_Res common_Res;

	
	public MainActivity() {
		super(R.string.left_and_right);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSlidingMenu().setMode(SlidingMenu.LEFT_RIGHT);
		getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

		midFragment = new MidFragment();

		setContentView(R.layout.content_frame);
		getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, midFragment).commit();

		getSlidingMenu().setSecondaryMenu(R.layout.menu_frame_two);
		getSlidingMenu().setSecondaryShadowDrawable(R.drawable.shadowright);
		getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame_two, new SampleListFragment("right"))
				.commit();

		mFrag.setSlidingCallback(this);

		getSupportActionBar().setCustomView(R.layout.search);
		getSupportActionBar().setDisplayShowCustomEnabled(true);

		getSupportActionBar().setDisplayShowHomeEnabled(false);
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0099FF")));

		initView();
		initData();
		initOnClickListener();

	}

	private void initView() {

		content_frame = (FrameLayout) findViewById(R.id.content_frame);

		View v = getSupportActionBar().getCustomView();
		img_search = (ImageView) v.findViewById(R.id.img_search);
		etx_search = (EditText) v.findViewById(R.id.etx_search);
		btn_home = (Button) v.findViewById(R.id.btn_home);
	}

	private void initData() {

	}

	private void initOnClickListener() {
		img_search.setOnClickListener(clickListener);
		btn_home.setOnClickListener(clickListener);
	}

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.img_search:
				if (etx_search.isShown()) {
	
					((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
							MainActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
					if (!etx_search.getText().toString().trim().equals("")) {
						new AsyncDataLoader(callback).execute();
					}
					etx_search.setVisibility(View.INVISIBLE);
					
				} else {
					etx_search.setVisibility(View.VISIBLE);
					etx_search.requestFocus();
					((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).showSoftInput(etx_search, 0);
				}
				break;
			case R.id.btn_home:
				toggle();
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
			KyUtil.addLoadingWin(MainActivity.this, content_frame, preStatus);
		}

		@Override
		public void onStart() {
			// TODO Auto-generated method stub
			if (!KyUtil.connectivityIsAvailable(MainActivity.this)) {
				netStatus = -1;
				return;
			}
			if (!KyUtil.pingIP(ServiceApi.IP)) {
				netStatus = 0;
				return;
			}
			try {
				Bean_common_sou_Req req = new Bean_common_sou_Req(new Bean_Request_Head(0), null);
				Bean_common_sou sou = new Bean_common_sou(etx_search.getText().toString().trim());
				req.setCommon_sou(sou);
				String requestJson = KyApplication.getApplication().gson.toJson(req);
				common_Res = KyApplication.getApplication().gson.fromJson(
						KyHttpClient.post(KyApplication.getApplication().module_Res.getSearchAction(), requestJson),
						Bean_common_Res.class);
				netStatus = 1;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				netStatus = 2;
				times--;
				common_Res = null;
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
			etx_search.setText("");
			switch (netStatus) {
			case -1:
				KyUtil.removeLoadingWin(content_frame, null, false);
				ToastUtils.showToast(MainActivity.this, R.string.network_status_toast);
				break;
			case 0:
				KyUtil.removeLoadingWin(content_frame, null, false);
				ToastUtils.showToast(MainActivity.this, R.string.service_status_toast);
				break;
			case 1:
				KyUtil.removeLoadingWin(content_frame, new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (null != common_Res && null != common_Res.getBean_commons()
								&& common_Res.getBean_commons().size() > 0) {
							midFragment.dataForSearch(common_Res.getBean_commons());
						} else {
							ToastUtils.showToast(MainActivity.this, "无结果返回!");
						}
					}
				}, true);

				break;
			case 2:
				KyUtil.removeLoadingWin(content_frame, null, false);
				ToastUtils.showToast(MainActivity.this, R.string.request_long_toast);
				break;
			}

		}
	};
	

	@Override
	public void SlidingToggle(int postion) {
		// TODO Auto-generated method stub
		midFragment.dataChanged(postion);
		toggle();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		 if (keyCode == KeyEvent.KEYCODE_BACK) {
	            exit();
	            return false;
	        }
	        return super.onKeyDown(keyCode, event);
	}
	
	 public void exit() {
	        if ((System.currentTimeMillis() - exitTime) > 2000) {
	            ToastUtils.showToast(this, "再按一次退出程序!");
	            exitTime = System.currentTimeMillis();
	        } else {
	            finish();
	            System.exit(0);
	        }
	    }
	
}



interface SlidingCallback {
	public void SlidingToggle(int postion);
	
}