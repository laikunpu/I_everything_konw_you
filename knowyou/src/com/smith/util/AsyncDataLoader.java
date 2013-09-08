package com.smith.util;

import com.smith.inter.DataCallback;

import android.os.AsyncTask;

public class AsyncDataLoader extends AsyncTask<Void, Long, Object> {
	private DataCallback mCallback;

	public AsyncDataLoader(DataCallback callback) {
		setCallback(callback);
	}

	protected Object doInBackground(Void... voids) {
		if (mCallback != null) {
			mCallback.onStart();
		}
		return null;
	}

	protected void onPostExecute(Object result) {
		super.onPostExecute(result);
		if (mCallback != null) {
			mCallback.onFinish();
		}
	}

	protected void onPreExecute() {
		super.onPreExecute();
		if (mCallback != null) {
			mCallback.onPrepare();
		}
	}

	public void setCallback(DataCallback callback) {
		this.mCallback = callback;
	}

}
