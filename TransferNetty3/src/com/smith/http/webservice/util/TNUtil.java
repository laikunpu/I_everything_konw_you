package com.smith.http.webservice.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.Gson;

public class TNUtil {
	public static final Gson gson = new Gson();

	public static String fileToContent(String path) {
		String encoded = "UTF-8";
		InputStreamReader reader = null;
		try {
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
