package com.smith.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class UserSettings {
	private static SharedPreferences settings;
	private static SharedPreferences.Editor editor;
	private static UserSettings userSettings;

	public static UserSettings getInstance(Context context) {
		if (null == settings || null == userSettings) {
			settings = PreferenceManager.getDefaultSharedPreferences(context);
			userSettings = new UserSettings();
		}

		return userSettings;
	}

	private UserSettings() {
		super();
	}

	public static void putValue(String key, Object value) {
		editor = settings.edit();
		if (value instanceof Boolean) {
			editor.putBoolean(key, (Boolean) value);
		} else if (value instanceof Float) {
			editor.putFloat(key, (Float) value);
		} else if (value instanceof Integer) {
			editor.putInt(key, (Integer) value);
		} else if (value instanceof Long) {
			editor.putLong(key, (Long) value);
		} else {
			editor.putString(key, (String) value);
		}
		editor.commit();
	}

	public static void firstcome() {
		putValue("come", "came");
	}

	public static boolean isfirstcome() {
		if (settings.getString("come", "no").equals("came"))
			return false;
		firstcome();
		return true;
	}
}
