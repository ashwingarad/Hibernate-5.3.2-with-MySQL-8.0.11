package com.client;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.config.HibernateUtil;
import com.model.Student;

public class Pagination {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSession();

		Query query = session.createQuery("from Student");
		query.setFirstResult(2);
		query.setMaxResults(4);

		List<Student> list = query.getResultList();
		list.forEach(s -> {
			System.out.println(s.getId() + "\t" + s.getName());
		});
		session.close();
	}

}
