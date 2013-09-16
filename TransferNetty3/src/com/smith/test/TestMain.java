package com.smith.test;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// ApplicationContext context = new
		// ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		// IDao dao=context.getBean("dao", DaoImpl.class);
		// dao.addT(new Normal_Html("1", 2, "3"));

		// WebDriver driver = new FirefoxDriver();
		//
		// driver.get("http://www.imanhua.com/comic/1067/list_87825.html");
		// // WebElement element = driver.findElement(By.name("q"));
		// // element.sendKeys("hello Selenium!");
		// // element.submit();
		// WebDriverWait wait = new WebDriverWait(driver, 10);
		// wait.until(new ExpectedCondition<WebElement>() {
		// @Override
		// public WebElement apply(WebDriver d) {
		// return d.findElement(By.id("mangaFile"));
		// }
		// }).click();
		//
		// WebElement element = driver.findElement(By.id("mangaFile"));
		//
		// System.out.println(element.getAttribute("src"));
		// // Select selectAge = new
		// // Select(driver.findElement(By.id("mangaFile")));
		//
		// driver.close();
		// // driver.quit();

		String page_img_url = "http://t5.mangafiles.com:88/Files/Images/1067/87825/imanhua%20001.png";
		String pre = page_img_url.substring(0, page_img_url.lastIndexOf("."));
		String after = page_img_url.substring(page_img_url.lastIndexOf("."));
		// System.out.println(pre);
		// System.out.println(after);
		// System.out.println(getNumbers(mid));

		for (int i = 1; i < 15; i++) {

			pre = pre.substring(0,(pre.length() - (i + "").length()));
			pre = pre + i;
			System.out.println(pre+after);
		}
	}

	// 截取数字
	public static String getNumbers(String content) {
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			return matcher.group(0);
		}
		return "";
	}

}
