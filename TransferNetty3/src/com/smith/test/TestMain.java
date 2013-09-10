package com.smith.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smith.http.webservice.dao.DaoImpl;
import com.smith.http.webservice.entity.Normal_Html;
import com.smith.http.webservice.global.TN_Constant;
import com.smith.http.webservice.inter.IDao;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml"); 
		IDao dao=context.getBean("dao", DaoImpl.class); 
		dao.addT(new Normal_Html("1", 2, "3"));
	}

}
