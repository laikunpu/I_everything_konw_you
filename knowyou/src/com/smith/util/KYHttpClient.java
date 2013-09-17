package com.smith.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;

public class KYHttpClient {
	private static final String APPLICATION_JSON = "application/json";
	private static final int CONNECTION_TIMEOUT = 20000;
	private static final String CONTENT_TYPE_TEXT_JSON = "text/json";
	private static final String HTTP_ENCODING = "UTF-8";

	public static String post(String url, String json) throws Exception {
		// 将JSON进行UTF-8编码,以便传输中文
		String encoderJson = URLEncoder.encode(json, HTTP_ENCODING);

		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);

		httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, CONNECTION_TIMEOUT);
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, CONNECTION_TIMEOUT);

		StringEntity se = new StringEntity(encoderJson);
		se.setContentType(CONTENT_TYPE_TEXT_JSON);
		se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
		httpPost.setEntity(se);
		// httpClient.execute(httpPost);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		// 保存获取的json数据
		StringBuilder builder = new StringBuilder();
		BufferedReader bufferedReader2 = new BufferedReader(
				new InputStreamReader(httpResponse.getEntity().getContent()));

		for (String s = bufferedReader2.readLine(); s != null; s = bufferedReader2.readLine()) {
			builder.append(s);
		}
		// System.out.println("服务器返回的数据=" + builder.toString());
		return builder.toString();
	}

	public static String get(String url) throws Exception {
		HttpClient httpClient = new DefaultHttpClient();
		httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, CONNECTION_TIMEOUT);
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, CONNECTION_TIMEOUT);

		HttpGet httpGet = new HttpGet(url);

		HttpResponse httpResponse = httpClient.execute(httpGet);
		// 保存获取的json数据
		StringBuilder builder = new StringBuilder();
		BufferedReader bufferedReader2 = new BufferedReader(
				new InputStreamReader(httpResponse.getEntity().getContent()));

		for (String s = bufferedReader2.readLine(); s != null; s = bufferedReader2.readLine()) {
			builder.append(s);
		}
		// System.out.println("服务器返回的数据=" + responseBody);
		return builder.toString();

	}

}
