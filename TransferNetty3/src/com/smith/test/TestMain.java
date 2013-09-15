package com.smith.test;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


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

		try {
			Desktop desktop = Desktop.getDesktop();
			if (desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE)) {
				URI uri = new URI("");
				desktop.browse(uri);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (URISyntaxException ex) {
			ex.printStackTrace();
		}

	}

}
