package com.client;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import com.model.Student;

public class Test {

	public static void main(String[] args) {
		Configuration configuration = new Configuration();
		configuration.configure();

		Session session = configuration.buildSessionFactory().openSession();

		Criteria criteria = session.createCriteria(Student.class);
		List<Student> list = criteria.list();

		list.forEach(s -> {
			System.out.println(s.getName() + " " + s.getAddr());
		});

		
	}
}
