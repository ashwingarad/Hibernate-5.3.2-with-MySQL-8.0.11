package com.client;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Filter;
import org.hibernate.Session;

import com.config.HibernateUtil;
import com.model.Student;

/**
 * @author AshwinGarad
 */
public class Operations {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSession();

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Student> criteriaQuery = builder.createQuery(Student.class);
		criteriaQuery.from(Student.class);

		Filter f1 = session.enableFilter("studFilter");
		f1.setParameter("mark", 500);

		List<Student> list = session.createQuery(criteriaQuery).getResultList();

		list.forEach(s -> {
			System.out.println(s.getId() + "\t" + s.getName() + "\t" + s.getMarks());
		});
		session.close();

		/**
		 * Without CriteriaBuilder and CriteriaQuery
		 */
		Session session2 = HibernateUtil.getSession();

		Filter f2 = session2.enableFilter("studFilter2");
		f2.setParameter("na", "Ashwin");

		List<Student> list2 = session2.createQuery("from Student").getResultList();
		System.out.println("\n\n");
		list2.forEach(s -> {
			System.out.println(s.getId() + "\t" + s.getName() + "\t" + s.getMarks());
		});

		session2.close();

		HibernateUtil.closeFactory();
	}
}
