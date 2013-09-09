package com.smith.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	public DBHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	public DBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	// 数据库名称
	private static final String DB_NAME = "knowyou.db";
	// 数据库版本
	private static final int DB_VERSION = 1;

	// 模块数据表
	public static final String TABLE_BEAN_UI = "bean_ui";
	public static final String _ID = "_id";
	public static final String MODULE_NAME = "name";
	public static final String MODULE_BACKGROUND_URL = "background_url";
	public static final String MODULE_BACKGROUND_COLOR = "background_color";
	public static final String MODULE_URL = "module_url";

	/**
	 * 创建模块表
	 */
	private static String SQL_CREATE_MODULE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_BEAN_UI + " (" + _ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT," + MODULE_NAME + " VARCHAR(200)," + MODULE_BACKGROUND_URL
			+ " VARCHAR(200)," + MODULE_BACKGROUND_COLOR + " VARCHAR(200)," + MODULE_URL + " VARCHAR(200))";

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_MODULE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS bean_ui");
		onCreate(db);
	}

}
