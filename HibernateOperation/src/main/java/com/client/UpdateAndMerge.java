package com.client;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.config.HibernateUtil;
import com.model.Student;

public class UpdateAndMerge {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSession();

		// Student s1 = new Student();
		// s1.setId(4l);
		// s1.setName("PQR");
		// s1.setAddr("Nasik");
		//
		// session.saveOrUpdate(s1);
		// session
 
		Student s1 = null;
		Object o = session.get(Student.class, 3L);
		s1 = (Student) o;
		s1.setAddr("sacds");
		session.saveOrUpdate(s1);
		session.close();

		s1.setAddr("Nasik");

		Session session2 = HibernateUtil.getSession();
		Student s2 = null;
		Object o2 = session.get(Student.class, 3l);
		s2 = (Student) o2;
		
		Transaction tx=session2.beginTransaction();
		session2.update(s1);

		//session2.merge(s1);
	}

}
