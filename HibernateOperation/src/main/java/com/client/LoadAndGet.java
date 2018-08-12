package com.client;

import org.hibernate.Session;

import com.config.HibernateUtil;
import com.model.Student;

public class LoadAndGet {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSession();

		try {
			Student s2 = (Student) session.load(Student.class, 1l);
			System.out.println("Student load() called");
			System.out.println("ID : " + s2.getId());
			System.out.println("Student Load : " + s2 + "\n");

			Student s1 = (Student) session.get(Student.class, 2l);
			System.out.println("Student get() called");
			System.out.println("ID : " + s1.getId());
			System.out.println("Student Load : " + s1 + "\n");

			session.close();
			HibernateUtil.closeFactory();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
