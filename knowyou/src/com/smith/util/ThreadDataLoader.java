package com.smith.util;

import com.smith.inter.DataCallback;

import android.content.Context;
import android.os.Handler;

public class ThreadDataLoader extends Thread {
	private Handler mHandler;
	private DataCallback mCallback;

	public ThreadDataLoader(Handler mHandle, DataCallback callback) {
		this.mHandler = mHandler;
		this.mCallback = mCallback;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		mCallback.onStart();
		mHandler.post(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				mCallback.onFinish();
			}
		});
	}

	public void excute() {
		mHandler.post(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				mCallback.onPrepare();
				start();
			}
		});
	}

}
