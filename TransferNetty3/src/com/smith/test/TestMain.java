package com.smith.test;

import java.awt.Desktop;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.utils.URLEncodedUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.smith.http.webservice.util.TNUrl;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			String url="http://ps3.seemh.com:82/comic/m/%E7%BE%8E%E5%9E%8B%E5%A6%96%E7%B2%BE%E5%A4%A7%E6%B7%B7%E6%88%98[%E5%A4%A7%E6%AD%AA]/%E5%B0%8F%E5%89%A7%E5%9C%001002.jpg";
//			System.out.println(URLDecoder.decode(url, "utf-8"));
			URL u=new URL(url);
			System.out.println(u.getPath());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
