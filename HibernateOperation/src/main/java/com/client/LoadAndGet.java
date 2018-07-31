package com.client;

import org.hibernate.Session;

import com.config.HibernateUtil;
import com.model.Student;

public class LoadAndGet {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSession();

		Student s1 = (Student) session.get(Student.class, 1l);
		System.out.println(s1.getId() + "\t" + s1.getName() + "\t" + s1.getAddr());

		Student s2 = (Student) session.load(Student.class, 1l);
		System.out.println(s2.getId() + "\t" + s2.getName() + "\t" + s2.getAddr());

		session.close();
		HibernateUtil.closeFactory();
	}
}
