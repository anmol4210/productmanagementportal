package com.nagarro.productmanagement.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nagarro.productmanagement.constants.Constants;


public class HibernateUtils {
	private HibernateUtils(){}
	public static Session createSession(){
		SessionFactory sf=new Configuration().configure(Constants.HIBERNATE_FILE_PATH).buildSessionFactory();
		return sf.openSession();

	}
}