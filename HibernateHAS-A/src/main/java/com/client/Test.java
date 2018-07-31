package com.client;

import org.hibernate.Session;

import com.config.HibernateUtil;
import com.model.Employee;
import com.model.Pancard;

public class Test {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSession();

		Pancard pancard = new Pancard();
		pancard.setPan_num("AD87455FF96");

		Employee employee = new Employee();
		employee.setName("Ashwin");
		employee.setSalary(45000);
		employee.setPancard(pancard);

		// session.persist(pancard);
		session.persist(employee);

		session.beginTransaction().commit();
		System.out.println("Successfully saved");
		session.clear();
		HibernateUtil.closeFactory();
	}

}
