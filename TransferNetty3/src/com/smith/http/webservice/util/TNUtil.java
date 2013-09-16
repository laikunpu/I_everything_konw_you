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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.gson.Gson;
import com.smith.http.webservice.entity.Normal_Html;
import com.smith.http.webservice.entity.Selenium_info;
import com.smith.http.webservice.inter.IDao;

public class TNUtil {
	public static final Gson gson = new Gson();

	public static IDao dao;;

	public static String fileToContent(String path) {
		String encoded = "UTF-8";
		InputStreamReader reader = null;
		try {
			File file = new File(path);
			if (!file.exists())
				return null;
			reader = new InputStreamReader(new FileInputStream(path), encoded);
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

	public static String htmlToPath(String htmlname) {
		String path = htmlname.replaceAll("/", "-");
		path = path.replaceAll("[.]", "=");
		path = path.replace(":", ";");
		return path;

	}

	public static String pathToHtml(String pathname) {
		String html = pathname.replaceAll("[-]", "/");
		html = html.replaceAll("[=]", ".");
		html = html.replaceAll("[;]", ":");
		return html;

	}

	public static Document loadUrl(final String url, final IDao<Normal_Html> dao, final Selenium_info selenium_info)
			throws IOException {
		final Document doc;
		// String htnl_cache = fileToContent(TN_Constant.NORMAL_HTML_CACHE +
		// TNUtil.htmlToPath(url) + ".html");
		Normal_Html htnl_cache = dao.findT(TNUtil.htmlToPath(url) + ".html", Normal_Html.class);
		if (null != htnl_cache) {
			doc = Jsoup.parse(new String(htnl_cache.getContent(), "utf-8"));
			System.out.println(url + "  从本地读取!!!");
		} else {

			if (null != selenium_info && selenium_info.isUseSelenium()) {
				WebDriver driver = new FirefoxDriver();

				driver.get(url);
				WebDriverWait wait = new WebDriverWait(driver, 10);
				wait.until(new ExpectedCondition<WebElement>() {
					@Override
					public WebElement apply(WebDriver d) {
						return d.findElement(By.id(selenium_info.getTag()));
					}
				}).click();
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
						if (dao.findT(TNUtil.htmlToPath(url) + ".html", Normal_Html.class) == null) {
							dao.addT(new Normal_Html(TNUtil.htmlToPath(url) + ".html", 1, doc.toString().trim()
									.getBytes("utf-8")));
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
