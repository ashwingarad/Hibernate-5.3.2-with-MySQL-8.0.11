package com.config;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class HibernateObjectByProps {

	public static void main(String[] args) {
		Configuration configuration = new Configuration();
		Properties properties = new Properties();
		try {
			//if file name is hibernate.properties then no need to set property. Automatically loaded.
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("database.properties"));
			configuration.setProperties(properties);
			configuration.addResource("com/model/Student.hbm.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}

		Session session = configuration.buildSessionFactory().openSession();
		System.out.println(session.isConnected());
		session.close();
	}
}
