package com.client;

import org.hibernate.Session;

import com.config.HibernateUtil;
import com.model.Student;

/**
 * @author AshwinGarad
 */
public class Operations {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSession();

		Student s = new Student();
		s.setName("LMN");
		s.setMarks(452);

		session.persist(s);
		session.beginTransaction().commit();

		System.out.println("Saved");
		session.close();
		HibernateUtil.closeFactory();
	}
}
