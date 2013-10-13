package com.smith.activity;

import android.os.Bundle;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;


public class MainActivity extends BaseActivity  implements SlidingCallback{
	private MidFragment midFragment;

	public MainActivity() {
		super(R.string.left_and_right);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSlidingMenu().setMode(SlidingMenu.LEFT_RIGHT);
		getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		
		
		midFragment=new MidFragment();
		
		
		setContentView(R.layout.content_frame);
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.content_frame,midFragment)
		.commit();
		
		getSlidingMenu().setSecondaryMenu(R.layout.menu_frame_two);
		getSlidingMenu().setSecondaryShadowDrawable(R.drawable.shadowright);
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.menu_frame_two, new SampleListFragment("right"))
		.commit();
		
		mFrag.setSlidingCallback(this);
	}
	@Override
	public void SlidingToggle(int postion) {
		// TODO Auto-generated method stub
		midFragment.dataChanged(postion);
		toggle();
	}
}
interface SlidingCallback{
	public void SlidingToggle(int postion);
}