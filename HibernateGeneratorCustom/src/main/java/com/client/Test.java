package com.client;

import java.util.Calendar;

import org.hibernate.Session;

import com.config.HibernateUtil;
import com.model.Licence;

public class Test {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSession();

		Licence licence = new Licence();
		licence.setName("ABC");
		licence.setValidity(Calendar.getInstance());

		session.beginTransaction();
		session.save(licence);
		session.getTransaction().commit();
		System.out.println(session.getTransaction().getStatus());
		session.close();
	}

}
