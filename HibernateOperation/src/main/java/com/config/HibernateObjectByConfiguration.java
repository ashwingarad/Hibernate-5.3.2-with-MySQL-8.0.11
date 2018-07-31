package com.config;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class HibernateObjectByConfiguration {

	public static void main(String[] args) {
		Configuration cfg = new Configuration();

		cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		cfg.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
		cfg.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/hibernate?useSSL=false");
		cfg.setProperty("hibernate.connection.username", "root");
		cfg.setProperty("hibernate.connection.password", "root");

		cfg.addResource("com/model/Student.hbm.xml");

		Session session = cfg.buildSessionFactory().openSession();
		System.out.println(session.isConnected());
		session.close();
	}

}
