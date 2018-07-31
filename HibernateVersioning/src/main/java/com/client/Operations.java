package com.client;

import org.hibernate.Session;

import com.config.HibernateUtil;
import com.model.Student;

public class Operations {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSession();

		Student s = new Student();
		s.setName("LMN");
		s.setMarks(452);

		session.persist(s);
		session.beginTransaction().commit();

		System.out.println("Saved");

		s = session.get(Student.class, 1l);
		s.setName("Ashwin");
		s.setMarks(650);
		session.update(s);
		session.beginTransaction().commit();

		System.out.println("Updated");

		session.close();
		HibernateUtil.closeFactory();
	}
}
