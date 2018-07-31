package com.client;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.config.HibernateUtil;
import com.model.Student;

public class StudentTest {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSession();

		Criteria criteria = session.createCriteria(Student.class);
		List<Student> list = criteria.list();
		list.forEach(s1 -> {
			System.out.println(s1.getName() + " " + s1.getAddr());
		});
	}

}