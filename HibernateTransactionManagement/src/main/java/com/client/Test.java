package com.client;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import com.config.HibernateUtil;
import com.model.Student;

public class Test {

	public static void main(String[] args) {

		try (Session session = HibernateUtil.getSession()) {
			Student student = new Student();
			student.setName("ABC");
			student.setMarks(356);

			Transaction transaction = session.beginTransaction();
			session.persist(student);
			transaction.commit();

			if (transaction.getStatus() == TransactionStatus.COMMITTED) {
				System.out.println("Successfully saved");
			} else {
				transaction.rollback();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
