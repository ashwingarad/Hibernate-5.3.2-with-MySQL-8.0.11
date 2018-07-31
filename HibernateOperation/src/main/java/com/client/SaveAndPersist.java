package com.client;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.config.HibernateUtil;
import com.model.Student;

public class SaveAndPersist {

	public void method1(Session session) {
		Transaction tx = session.beginTransaction();

		Student s1 = new Student();
		s1.setName("ABC");
		s1.setAddr("Jammu");

		session.save(s1); // Save immediately
		session.persist(s1); // Not save immediately
		tx.commit(); // persist() save object here.
	}

	public void method2(Session session) {
		session.beginTransaction();
		Student s2 = (Student) session.get(Student.class, 4l);
		session.close();

		Session session3 = HibernateUtil.getSession();
		session3.beginTransaction();
		s2.setAddr("Kashmir");
		session3.save(s2);
		session3.getTransaction().commit();
		session3.close();
	}

	public void method3(Session session) {
		session.beginTransaction();
		Student s2 = (Student) session.get(Student.class, 4l);
		session.close();

		Session session5 = HibernateUtil.getSession();
		session5.beginTransaction();
		s2.setAddr("Kashmir");
		session5.persist(s2);
		session5.getTransaction().commit();
		session5.close();
	}

	public static void main(String[] args) {
		SaveAndPersist obj = new SaveAndPersist();

		Session session = HibernateUtil.getSession();
		obj.method1(session);
		session.close();

		// Detach object saving by save()
		Session session2 = HibernateUtil.getSession();
		obj.method2(session2);
		session2.close();

		// Detach object saving by persist()
		Session session4 = HibernateUtil.getSession();
		obj.method3(session4);
		session4.close();
	}

}
