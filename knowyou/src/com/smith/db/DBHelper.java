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

	public static final String _ID = "_id";

	// 模块数据表
	public static final String TABLE_BEAN_MODULE = "bean_module";

	public static final String MODULE_NAME = "module_name";
	public static final String MODULE_NUM = "module_num";
	public static final String ADURL = "adUrl";
	public static final String ISMOREDATA = "isMoreData";
	public static final String DATANUM = "dataNum";
	public static final String DATANUMMAX = "dataNumMax";
	public static final String MOREDATA_ACTION = "moreData_action";

	// 模块子数据表
	public static final String TABLE_BEAN_COMMON = "bean_common";
	public static final String COMMON_NAME = "name"; // 动漫名字
	public static final String COMMON_TYPE = "type"; // 动漫类型 0：漫画 1：动画
	public static final String COMMON_DETAIL_ACTION = "detail_action";
	public static final String COMMON_SUMMARY = "summary"; // 简介
	public static final String COMMON_COVER_URL = "cover_url"; // 封面url
	public static final String COMMON_DETAIL_URL = "detail_url"; // 详情url
	public static final String COMMON_DOWNLOAD_URL = "download_url"; // 下载url
	public static final String COMMON_SIZE = "size"; // 大小

	/**
	 * 创建模块表
	 */
	private static String SQL_CREATE_MODULE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_BEAN_MODULE + " (" + _ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT," + MODULE_NAME + " VARCHAR(200)," + MODULE_NUM + " VARCHAR(200),"
			+ ADURL + " VARCHAR(200)," + ISMOREDATA + " VARCHAR(200)," + DATANUM + " VARCHAR(200)," + DATANUMMAX
			+ " VARCHAR(200)," + MOREDATA_ACTION + " VARCHAR(200))";

	/**
	 * 创建模块子数据表
	 */
	private static String SQL_CREATE_COMMON_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_BEAN_COMMON + " (" + _ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT," + MODULE_NAME + " VARCHAR(200)," +COMMON_NAME + " VARCHAR(200)," + COMMON_TYPE + " VARCHAR(200),"
			+ COMMON_DETAIL_ACTION + " VARCHAR(200)," + COMMON_SUMMARY + " VARCHAR(200)," + COMMON_COVER_URL
			+ " VARCHAR(200)," + COMMON_DETAIL_URL + " VARCHAR(200)," + COMMON_DOWNLOAD_URL + " VARCHAR(200),"
			+ COMMON_SIZE + " VARCHAR(200))";

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_MODULE_TABLE);
		db.execSQL(SQL_CREATE_COMMON_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS bean_module");
		db.execSQL("DROP TABLE IF EXISTS bean_common");
		onCreate(db);
	}

}
