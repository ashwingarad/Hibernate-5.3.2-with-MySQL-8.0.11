package com.client;

import org.hibernate.Session;

import com.config.HibernateUtil;
import com.model.Student;

public class Evict {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSession();

		System.out.println("Before EVICT");

		Student s = session.get(Student.class, 2l);
		System.out.println(s.getName() + " " + s.getAddr());

		s.setAddr("Mumbai");
		session.saveOrUpdate(s);

		session.evict(s);

		session.beginTransaction().commit();

		System.out.println("\nAfter EVICT");
		s = session.get(Student.class, 2l);
		System.out.println(s.getName() + " " + s.getAddr());

		session.close();
	}
}
