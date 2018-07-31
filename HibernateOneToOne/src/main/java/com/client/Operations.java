package com.client;

import org.hibernate.Session;

import com.config.HibernateUtil;
import com.model.Employee;
import com.model.Pancard;

/**
 * @author AshwinGarad
 */
public class Operations {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSession();

		Employee employee = new Employee();
		employee.setName("LMN");
		employee.setSalary(555555);

		Pancard pancard = new Pancard();
		pancard.setNum("AS33345JFHD");
		pancard.setEmployee(employee);

		employee.setPancard(pancard);

		session.save(employee);

		session.beginTransaction().commit();
		System.out.println("Saved");
		session.close();
	}
}
