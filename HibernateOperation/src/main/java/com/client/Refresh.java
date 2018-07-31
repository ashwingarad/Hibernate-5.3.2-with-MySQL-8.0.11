package com.client;

import org.hibernate.Session;

import com.config.HibernateUtil;
import com.model.Student;

public class Refresh {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSession();

		Student s1 = (Student) session.get(Student.class, 1l);
		System.out.println(s1.getId() + "\t" + s1.getName());
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		session.refresh(s1);
		System.out.println(s1.getId() + "\t" + s1.getName());
	}

}
