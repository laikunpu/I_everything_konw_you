package com.smith.db;

import java.util.ArrayList;
import java.util.List;

import com.smith.entity.Bean_common;
import com.smith.entity.Bean_module;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DaoImpl implements IDao {
	private DBHelper dbHelper = null;

	private Context mctx;

	private SQLiteDatabase mdb;

	private Cursor cursor = null;

	public DaoImpl(Context ctx) {
		this.mctx = ctx;
	}

	public void open() throws SQLException {
		dbHelper = new DBHelper(mctx);
		mdb = dbHelper.getWritableDatabase();
	}

	public void close() {
		if (dbHelper != null) {
			dbHelper.close();
		}
		if (cursor != null) {
			cursor.close();
		}
	}

	public boolean exist(String name, String table, String field) {
		// TODO Auto-generated method stub
		try {
			String sql = "Select 1 From " + table + " Where " + field + "=?";

			cursor = mdb.rawQuery(sql, new String[] { name });

			return cursor.getCount() > 0;

		} catch (SQLException e) {

		} finally {
		}
		return false;
	}

	@Override
	public boolean add_module(Bean_module module) {
		// TODO Auto-generated method stub
		open();
		if (exist(module.getModule_name(), DBHelper.TABLE_BEAN_MODULE, DBHelper.MODULE_NAME)) {
			return false;
		}

		try {
			StringBuffer sql = new StringBuffer("INSERT INTO " + DBHelper.TABLE_BEAN_MODULE + " ("
					+ DBHelper.MODULE_NAME + "," + DBHelper.MODULE_NUM + "," + DBHelper.ADURL + ","
					+ DBHelper.ISMOREDATA + "," + DBHelper.DATANUM + "," + DBHelper.DATANUMMAX + ","
					+ DBHelper.MOREDATA_ACTION + ")");

			sql.append(" values(?,?,?,?,?,?,?)");
			mdb.execSQL(
					sql.toString(),
					new String[] { module.getModule_name() + "", module.getModule_num() + "", module.getAdUrl(),
							module.isMoreData() + "", module.getDataNum() + "", module.getDataNumMax() + "",
							module.getMoreData_action() });
			for (int i = 0; i < module.getCommons().size(); i++) {
				add_common(module.getCommons().get(i), module.getModule_name());
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return false;
	}
	
	public boolean add_common(Bean_common common,String module_name) {
		// TODO Auto-generated method stub
		if (exist(common.getName(), DBHelper.TABLE_BEAN_COMMON, DBHelper.COMMON_NAME)) {
			return false;
		}
		System.out.println("add_common  module_name="+module_name);
		try {
			StringBuffer sql = new StringBuffer("INSERT INTO " + DBHelper.TABLE_BEAN_COMMON + " ("
					+ DBHelper.MODULE_NAME + "," + DBHelper.COMMON_NAME + "," + DBHelper.COMMON_TYPE + ","
					+ DBHelper.COMMON_DETAIL_ACTION + "," + DBHelper.COMMON_SUMMARY + "," + DBHelper.COMMON_COVER_URL + ","
					+ DBHelper.COMMON_DETAIL_URL+ "," +DBHelper.COMMON_DOWNLOAD_URL+ "," +DBHelper.COMMON_SIZE + ")");

			sql.append(" values(?,?,?,?,?,?,?,?,?)");
			mdb.execSQL(
					sql.toString(),
					new String[] { module_name , common.getName(), common.getType()+"",
						common.getDetail_action() , common.getSummary() , common.getCover_url() ,
						common.getDetail_url(),common.getDownload_url(),common.getSize() });

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return false;
	}

	@Override
	public boolean delete_table(String table) {
		// TODO Auto-generated method stub

		try {
			open();
			String sql = "delete from " + table;
			mdb.execSQL(sql);
			return true;
		} catch (Exception e) {
		} finally {
			close();
		}
		return false;
	}

	@Override
	public List<Bean_module> get_modules() {
		// TODO Auto-generated method stub
		List<Bean_module> modulesuis = null;
		try {
			open();
			String sql = "Select * From " + DBHelper.TABLE_BEAN_MODULE;

			cursor = mdb.rawQuery(sql, null);
			cursor.moveToFirst();

			modulesuis = new ArrayList<Bean_module>();

			for (; !cursor.isAfterLast(); cursor.moveToNext()) {
				Bean_module module = new Bean_module();

				module.setModule_name(cursor.getString(cursor.getColumnIndex(DBHelper.MODULE_NAME)));
				module.setModule_num(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBHelper.MODULE_NUM))));
				module.setAdUrl(cursor.getString(cursor.getColumnIndex(DBHelper.ADURL)));
				module.setMoreData(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(DBHelper.ISMOREDATA))));
				module.setDataNum(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBHelper.DATANUM))));
				module.setDataNumMax(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBHelper.DATANUMMAX))));
				module.setMoreData_action(cursor.getString(cursor.getColumnIndex(DBHelper.MOREDATA_ACTION)));

				
				module.setCommons(get_commons(module.getModule_name()));
				
				modulesuis.add(module);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return modulesuis;
	}
	
	public List<Bean_common> get_commons(String module_name) {
		// TODO Auto-generated method stub
		List<Bean_common> commons = null;
		Cursor mCursor = null;
		try {
			String sql = "Select * From " + DBHelper.TABLE_BEAN_COMMON+" where "+DBHelper.MODULE_NAME+" = ?";
		    mCursor  = mdb.rawQuery(sql, new String[] {module_name });
			mCursor.moveToFirst();

			commons = new ArrayList<Bean_common>();

			for (; !mCursor.isAfterLast(); mCursor.moveToNext()) {
				Bean_common common = new Bean_common();

				common.setName(mCursor.getString(mCursor.getColumnIndex(DBHelper.COMMON_NAME)));
				common.setType(Integer.parseInt(mCursor.getString(mCursor.getColumnIndex(DBHelper.COMMON_TYPE))));
				common.setDetail_action(mCursor.getString(mCursor.getColumnIndex(DBHelper.COMMON_DETAIL_ACTION)));
				common.setSummary(mCursor.getString(mCursor.getColumnIndex(DBHelper.COMMON_SUMMARY)));
				common.setCover_url(mCursor.getString(mCursor.getColumnIndex(DBHelper.COMMON_COVER_URL)));
				common.setDetail_url(mCursor.getString(mCursor.getColumnIndex(DBHelper.COMMON_DETAIL_URL)));
				common.setDownload_url(mCursor.getString(mCursor.getColumnIndex(DBHelper.COMMON_DOWNLOAD_URL)));
				common.setSize(mCursor.getString(mCursor.getColumnIndex(DBHelper.COMMON_SIZE)));
				commons.add(common);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mCursor.close();
		}
		return commons;
	}


	// @Override
	// public boolean saveBook(Book book) {
	// // TODO Auto-generated method stub
	// if (exist(book.getBookName())) {
	// return false;
	// }
	// open();
	// try {
	// StringBuffer sql = new StringBuffer("INSERT INTO "
	// + DBHelper.TABLE_BOOK + " (" + DBHelper.BOOK_NAME + ","
	// + DBHelper.BOOK_IMAGEURL + "," + DBHelper.BOOK_DETAILURL
	// + "," + DBHelper.BOOK_AUTHOR + ","
	// + DBHelper.BOOK_WORDCOUNT + ","
	// + DBHelper.BOOK_MODIFIEDTIME + "," + DBHelper.BOOK_SUMMARY
	// + "," + DBHelper.BOOK_READURL + "," + DBHelper.DOWNLOAD_URL
	// + "," + DBHelper.BOOK_PATH + "," + DBHelper.BOOK_COMEFROM+")");
	//
	// sql.append(" values(?,?,?,?,?,?,?,?,?,?,?)");
	// mdb.execSQL(
	// sql.toString(),
	// new String[] { book.getBookName(), book.getImageUrl(),
	// book.getBookDetailUrl(), book.getBookAuthor(),
	// book.getWordCount(), book.getModifiedTime(),
	// book.getSummary(), book.getReadUrl(),
	// book.getDowloadUrl(), book.getBookPath(),book.getBookcomefrom() });
	//
	// return true;
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// close();
	// }
	// return false;
	// }
	//
	// @Override
	// public boolean deleteBook(Book book) {
	// // TODO Auto-generated method stub
	// try {
	// open();
	// String sql = "delete from BookList where bookPath=?";
	// mdb.execSQL(sql, new String[] { book.getBookPath() });
	// return true;
	// } catch (Exception e) {
	// System.out.println("DaoImpl->deleteBook  error");
	// } finally {
	// close();
	// }
	// return false;
	// }
	//
	// @Override
	// public boolean updateBook(Book book) {
	// // TODO Auto-generated method stub
	// return false;
	// }
	//
	// @Override
	// public Book getBookById(int itemid) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public boolean exist(String name) {
	// // TODO Auto-generated method stub
	// try {
	// open();
	// String sql = "Select 1 From " + DBHelper.TABLE_BOOK + " Where "
	// + DBHelper.BOOK_NAME + "=?";
	//
	// cursor = mdb.rawQuery(sql, new String[] { name });
	//
	// return cursor.getCount() > 0;
	//
	// } catch (SQLException e) {
	//
	// } finally {
	// close();
	// }
	// return false;
	// }
	//
	// @Override
	// public List<Book> getBooks() {
	// // TODO Auto-generated method stub
	// List<Book> books = new ArrayList<Book>();
	// try {
	// open();
	// String sql = "Select * From " + DBHelper.TABLE_BOOK;
	//
	// cursor = mdb.rawQuery(sql, null);
	// cursor.moveToFirst();
	// for (; !cursor.isAfterLast(); cursor.moveToNext()) {
	// Book book = new Book();
	// book.setBookName(cursor.getString(cursor
	// .getColumnIndex(DBHelper.BOOK_NAME)));
	// book.setImageUrl(cursor.getString(cursor
	// .getColumnIndex(DBHelper.BOOK_IMAGEURL)));
	// book.setBookDetailUrl(cursor.getString(cursor
	// .getColumnIndex(DBHelper.BOOK_DETAILURL)));
	// book.setBookAuthor(cursor.getString(cursor
	// .getColumnIndex(DBHelper.BOOK_AUTHOR)));
	// book.setWordCount(cursor.getString(cursor
	// .getColumnIndex(DBHelper.BOOK_WORDCOUNT)));
	// book.setModifiedTime(cursor.getString(cursor
	// .getColumnIndex(DBHelper.BOOK_MODIFIEDTIME)));
	// book.setSummary(cursor.getString(cursor
	// .getColumnIndex(DBHelper.BOOK_SUMMARY)));
	// book.setReadUrl(cursor.getString(cursor
	// .getColumnIndex(DBHelper.BOOK_READURL)));
	// book.setDowloadUrl(cursor.getString(cursor
	// .getColumnIndex(DBHelper.DOWNLOAD_URL)));
	// book.setBookPath(cursor.getString(cursor
	// .getColumnIndex(DBHelper.BOOK_PATH)));
	// book.setBookcomefrom(cursor.getString(cursor
	// .getColumnIndex(DBHelper.BOOK_COMEFROM)));
	// books.add(book);
	// }
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// close();
	// }
	// return books;
	// }

}
