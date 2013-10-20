package com.smith.http.webservice.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.gson.Gson;
import com.smith.http.webservice.entity.HtmlLevelOne;
import com.smith.http.webservice.entity.HtmlLevelThree;
import com.smith.http.webservice.entity.HtmlLevelTwo;
import com.smith.http.webservice.entity.Selenium_info;
import com.smith.http.webservice.inter.IDao;

public class TNUtil {
//	public static final Gson gson = new Gson();

	
	
	public static IDao dao;;
	static String charset = "utf-8";
	public static Hashtable<String, String> urltable=new Hashtable<String, String>();

	public static String fileToContent(String path) {
		InputStreamReader reader = null;
		try {
			File file = new File(path);
			if (!file.exists())
				return null;
			reader = new InputStreamReader(new FileInputStream(path), Charset.forName(charset));
			BufferedReader fileReader = new BufferedReader(reader);
			String s;
			StringBuffer buffer = new StringBuffer();
			while ((s = fileReader.readLine()) != null) {
				buffer.append(s);
				buffer.append("\n");
			}

			return buffer.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("TNUtil->fileToContent 出错!!!");
		} finally {
			try {
				if (null != reader)
					reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public static boolean writeToFile(String path, String filename, String str) {
		File dir = new File(path);
		File f = null;

		OutputStreamWriter writer = null;
		BufferedWriter bw = null;

		try {
			if (!dir.exists()) {
				dir.mkdir();
			}
			f = new File(dir, filename);
			// if(f.exists()){
			// f.delete();
			// }
			OutputStream os = new FileOutputStream(f);
			writer = new OutputStreamWriter(os);
			bw = new BufferedWriter(writer);
			bw.write(str);
			bw.flush();
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (null != bw)
					bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	// public static String htmlToPath(String htmlname) {
	// String path = htmlname.replaceAll("/", "-");
	// path = path.replaceAll("[.]", "=");
	// path = path.replace(":", ";");
	// return path;
	//
	// }
	//
	// public static String pathToHtml(String pathname) {
	// String html = pathname.replaceAll("[-]", "/");
	// html = html.replaceAll("[=]", ".");
	// html = html.replaceAll("[;]", ":");
	// return html;
	//
	// }

	public static Document loadUrl(final String url, final Selenium_info selenium_info, final int level)
			throws IOException {
		final Document doc;
		// String htnl_cache = fileToContent(TN_Constant.NORMAL_HTML_CACHE +
		// TNUtil.htmlToPath(url) + ".html");
		String htnl_cache = null;
		switch (level) {
		case 1:
			HtmlLevelOne htmlLevelOne = (HtmlLevelOne) dao.findT(url, HtmlLevelOne.class);
			if (null != htmlLevelOne) {
				htnl_cache = new String(htmlLevelOne.getContent(), charset);
			}
			break;
		case 2:
			HtmlLevelTwo htmlLevelTwo = (HtmlLevelTwo) dao.findT(url, HtmlLevelTwo.class);
			if (null != htmlLevelTwo) {
				htnl_cache = new String(htmlLevelTwo.getContent(), charset);
			}
			break;
		case 3:
			HtmlLevelThree htmlLevelThree = (HtmlLevelThree) dao.findT(url, HtmlLevelThree.class);
			if (null != htmlLevelThree) {
				htnl_cache = new String(htmlLevelThree.getContent(), charset);
			}
			break;
		}

		if (null != htnl_cache) {
			doc = Jsoup.parse(htnl_cache);
			System.out.println(url + "  从本地读取!!!");
		} else {

			if (null != selenium_info && selenium_info.isUseSelenium()) {
				WebDriver driver = new FirefoxDriver();
				// 设置10秒
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.get(url);
				driver.findElement(By.id(selenium_info.getTag())).click();
				String normal_html = driver.getPageSource();
				doc = Jsoup.parse(normal_html);
				driver.quit();

			} else {
				doc = Jsoup.connect(url).get();
			}
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					// writeToFile(TN_Constant.NORMAL_HTML_CACHE,
					// TNUtil.htmlToPath(url) + ".html", doc.toString());
					try {

						switch (level) {
						case 1:
							if (dao.findT(url, HtmlLevelOne.class) == null) {
								dao.addT(new HtmlLevelOne(url, 1, doc.toString().trim().getBytes(charset)));
							}
							break;
						case 2:
							if (dao.findT(url, HtmlLevelTwo.class) == null) {
								dao.addT(new HtmlLevelTwo(url, 1, doc.toString().trim().getBytes(charset)));
							}
							break;
						case 3:
							if (dao.findT(url, HtmlLevelThree.class) == null) {
								dao.addT(new HtmlLevelThree(url, 1, doc.toString().trim().getBytes(charset)));
							}
							break;
						}

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();
			System.out.println(url + "  从网上抓取!!!");
		}
		return doc;
	}

	/**
	 * 根据Class获取类的名字
	 * 
	 * @param c
	 * @return
	 */
	public static <T> String getTableName(Class<T> c) {
		String tb_name = c.toString();
		return tb_name = tb_name.substring(tb_name.lastIndexOf(".") + 1);
	}
}
