package com.client;

import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import com.config.HibernateUtil;
import com.model.Employee;

public class Test {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSession();

		Employee employee = new Employee();
		employee.setName("Abraham Lincoln");
		employee.setSalary(4562132);
		session.save(employee);
		session.beginTransaction().commit();

		if (session.getTransaction().getStatus() == TransactionStatus.COMMITTED) {
			System.out.println("Saved");
		}

		session.close();
		HibernateUtil.closeFactory();
	}

}
