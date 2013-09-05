package tres.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import tres.entity.Admin;
import tres.entity.Book;
import tres.entity.BookDetailType;
import tres.entity.Chapter;
import tres.entity.BookType;
import tres.inter.IDao;
import tres.inter.IService;
import tres.util.SSH2Constant;
import tres.util.SSH2Uitil;

@SuppressWarnings("unchecked")
public class ServiceImpl<T> implements IService<T> {

	@Resource
	private IDao dao;

	@Override
	public void addT(T t) {
		dao.addT(t);

	}

	@Override
	public boolean delT(int id, Class<T> c) {
		return dao.delT(id, c);
	}

	@Override
	public void updateT(T t) {
		// TODO Auto-generated method stub
		this.dao.updateT(t);
	}

	@Override
	public List<T> findAllT(Class<T> c) {

		return dao.findByCondition(c, null, 0, 0);
	}

	@Override
	public List<T> findT(int id, Class<T> c) {
		// TODO Auto-generated method stub

		return dao.findT(id, c);
	}

	// 扫描目录
	public boolean scanDirRecursion(final File file) {

		try {

			if (file.canRead()) {

				if (file.isDirectory()) {
					String[] files = file.list();
					if (files != null) {
						for (int i = 0; i < files.length; i++) {

							scanDirRecursion(new File(file, files[i]));

						}
					}
				} else {
					if (file.getName().endsWith("txt")) {

						// 扫描类型
						String booktype1 = file.getParentFile().getParent();
						final String booktype = booktype1.substring(booktype1
								.length() - 2);
						String bookdetailtype1 = file.getParent();
						final String bookdetailtype = bookdetailtype1
								.substring(bookdetailtype1.length() - 4);

						Map<String, String> mapDetailType = new HashMap<String, String>();
						mapDetailType.put("bookdetailtype", bookdetailtype);
						List<BookDetailType> bookDetailTypes = dao
								.findByCondition(BookDetailType.class,
										mapDetailType, 0, 0);
						mapDetailType = null;
						if (bookDetailTypes.size() < 1) {

							Map<String, String> mapType = new HashMap<String, String>();
							mapType.put("booktype", booktype);
							List<BookType> bookTypes = dao.findByCondition(
									BookType.class, mapType, 0, 0);
							mapType = null;
							BookType entityBookType;
							if (bookTypes.size() < 1) {

								entityBookType = new BookType(booktype);
								BookDetailType entityBookDetailType = new BookDetailType(
										bookdetailtype, entityBookType);
								entityBookType.getBookDetailTypes().add(
										entityBookDetailType);

								dao.addT(entityBookType);
							} else {

								entityBookType = bookTypes.get(0);
								BookDetailType entityBookDetailType = new BookDetailType(
										bookdetailtype, entityBookType);
								entityBookType.getBookDetailTypes().add(
										entityBookDetailType);
								dao.updateT(entityBookType);
							}

						}

						// 起线程扫描
						if (null != SSH2Uitil.writeExecutor
								&& !SSH2Uitil.writeExecutor.isTerminated()) {
							SSH2Uitil.writeExecutor.execute(new Runnable() {

								@Override
								public void run() {
									// TODO Auto-generated method stub
									System.err.println("线程:"
											+ Thread.currentThread().getName()
											+ " 处理文件: " + file.getName());

									try {
										String bookName = file
												.getName()
												.substring(
														0,
														file.getName().length() - 4);

										Map<String, String> map = new HashMap<String, String>();
										map.put("bookname", bookName);
										List<Book> books = dao.findByCondition(
												Book.class, map, 0, 0);
										map = null;

										if (null != books && books.size() > 0) {
											System.err.println("书名:"
													+ books.get(0)
															.getBookname()
													+ "   此书已存在！！！");
											return;

										}

										InputStreamReader reader = new InputStreamReader(
												new FileInputStream(file));
										BufferedReader fileReader = new BufferedReader(
												reader);
										String s;
										String tempTitle = "";
										StringBuffer buffer = new StringBuffer();
										int chapterNum = 0;
										boolean isStart = false;
										String bookAuthor = "";
										boolean isGetAuthorName = false;
										while ((s = fileReader.readLine()) != null) {

											if (SSH2Constant.STOPSACN) {
												System.err.println("退出扫描:"
														+ bookName);
												return;
											}

											if (!isGetAuthorName) {
												if (s.contains("作者")) {
													bookAuthor = s.trim();
													isGetAuthorName = true;
												}
											}
											if (s.contains("第")
													&& s.contains("章")
													&& s.indexOf("第") == 0
													&& (s.indexOf("章") > s
															.indexOf("第"))
													&& (s.indexOf("章") - s
															.indexOf("第")) < 12) {

												isStart = true;
												Map<String, String> mapChapters = new HashMap<String, String>();
												mapChapters.put("chaptertitle",
														tempTitle);
												List<Chapter> chapters = dao
														.findByCondition(
																Chapter.class,
																mapChapters, 0,
																0);
												mapChapters = null;

												if (null != chapters
														&& chapters.size() > 0) {
													System.out
															.println("此章节已存在！！！");
												} else if (buffer.length() > 0) {
													Chapter chapter = new Chapter(
															file.getName()
																	.substring(
																			0,
																			file.getName()
																					.length() - 4),
															0,
															chapterNum,
															tempTitle,
															buffer.toString()
																	.getBytes(
																			"UTF-8"));
													dao.addT(chapter);
												}
												tempTitle = s;
												chapterNum++;
												buffer.delete(0,
														buffer.length());
											} else if (isStart) {
												buffer.append(s);
											}
										}

										// 添加书本
										Book book = new Book(bookAuthor,
												bookName, booktype,
												bookdetailtype, 0, 0, 0, 0,
												file.getPath());
										System.err.println("已扫描进数据库:"
												+ bookName);
										dao.addT(book);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
										System.err.println("线程扫描出错！！！");
									}
								}
							});
						}
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("扫描出错！！！");
			return false;
		}
		return true;
	}

	@Override
	public List<T> getListByCondition(Class<T> c, String field,
			boolean ascOrdesc, int first, int max) {
		// TODO Auto-generated method stub
		return dao.getListByCondition(c, field, ascOrdesc, first, max);
	}

	@Override
	public int getCount(Class<T> c, String field, String name) {
		// TODO Auto-generated method stub
		return dao.getCount(c, field, name);
	}

	@Override
	public List<T> findByCondition(Class<T> c, String[] fields,
			String[] values, int first, int max) {
		// TODO Auto-generated method stub
		Map<String, String> map = null;
		if (null != fields && null != values && fields.length == values.length) {
			map = new HashMap<String, String>();
			for (int i = 0; i < fields.length; i++) {
				map.put(fields[i], values[i]);
			}
		}

		return dao.findByCondition(c, map, first, max);
	}

	@Override
	public T findByConditionForOne(Class<T> c, String[] fields, String[] values) {
		// TODO Auto-generated method stub
		Map<String, String> map = null;
		if (null != fields && null != values && fields.length == values.length) {
			map = new HashMap<String, String>();
			for (int i = 0; i < fields.length; i++) {
				map.put(fields[i], values[i]);
			}
			List<T> list = dao.findByCondition(c, map, 0, 0);
			if (null != list && list.size() > 0) {
				return list.get(0);
			}
		}
		return null;
	}

}
