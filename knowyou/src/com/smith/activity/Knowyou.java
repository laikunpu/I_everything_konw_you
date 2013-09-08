package com.smith.activity;

import android.app.Application;

public class Knowyou extends Application {
	private static Knowyou instance;

	public static Knowyou getApplication() {
		return instance;
	}
}
