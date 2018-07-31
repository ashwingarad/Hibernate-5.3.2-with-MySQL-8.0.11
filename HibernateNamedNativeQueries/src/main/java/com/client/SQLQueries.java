package com.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.type.StandardBasicTypes;

import com.config.HibernateUtil;
import com.model.Student;

public class SQLQueries {

	public void insertClause() {
		Session session = HibernateUtil.getSession();

		NativeQuery<Student> query = session.getNamedNativeQuery("insert");
		query.setParameter(1, null); // Auto increment
		query.setParameter(2, "ABC");
		query.setParameter(3, 456);

		session.beginTransaction();
		query.executeUpdate();
		session.getTransaction().commit();
		System.out.println("Saved successfully");
		session.close();
	}

	public void displayAll() {
		Session session = HibernateUtil.getSession();

		NativeQuery<Student> query = session.getNamedNativeQuery("displayAll");
		List<Student> list = query.getResultList();
		list.forEach(e -> {
			System.out.println(e.getId() + "\t" + e.getName() + "\t" + e.getMarks());
		});
		session.close();
	}

	public void selectClause() {
		Session session = HibernateUtil.getSession();

		NativeQuery<Object[]> query = session.getNamedNativeQuery("selectClause");

		List<Object[]> list = query.getResultList();

		list.forEach(e -> {
			System.out.println(e[0] + "\t" + e[1]);
		});
		session.close();
	}

	public void whereClause() {
		Session session = HibernateUtil.getSession();

		NativeQuery<Object[]> query = session.getNamedNativeQuery("whereClause");
		query.setParameter(1, 1);

		List<Object[]> list = query.getResultList();

		list.forEach(e -> {
			System.out.println(e[0] + "\t" + e[1]);
		});
		session.close();
	}

	public void max() {
		Session session = HibernateUtil.getSession();

		NativeQuery<Object> query = session.getNamedNativeQuery("max");

		Object e = query.getSingleResult();

		System.out.println(e);

		session.close();
	}

	public void avg() {
		Session session = HibernateUtil.getSession();

		NativeQuery<Object> query = session.getNamedNativeQuery("avg");

		Object e = query.getSingleResult();

		System.out.println(e);
		session.close();
	}

	public void distinctWithCount() {
		Session session = HibernateUtil.getSession();

		NativeQuery<Object> query = session.getNamedNativeQuery("distinct");

		Object e = query.getSingleResult();

		System.out.println(e);
		session.close();
	}

	public void scalar() {
		Session session = HibernateUtil.getSession();

		NativeQuery<Object[]> query = session.getNamedNativeQuery("selectClause");
		query.addScalar("name", StandardBasicTypes.STRING);
		query.addScalar("marks", StandardBasicTypes.INTEGER);

		List<Object[]> list = query.getResultList();

		list.forEach(e -> {
			System.out.println(e[0] + "\t" + e[1]);
		});
		session.close();
	}

	public void orderByClause() {
		Session session = HibernateUtil.getSession();

		NativeQuery<Object[]> query = session.getNamedNativeQuery("orderClause");
		query.addScalar("name", StandardBasicTypes.STRING);
		query.addScalar("marks", StandardBasicTypes.INTEGER);

		List<Object[]> list = query.getResultList();

		list.forEach(e -> {
			System.out.println(e[0] + "\t" + e[1]);
		});
		session.close();
	}

	public void count() {
		Session session = HibernateUtil.getSession();

		NativeQuery<Object> query = session.getNamedNativeQuery("countStudent");

		Object e = query.getSingleResult();

		System.out.println(e);
		session.close();
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		SQLQueries q = new SQLQueries();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int ch = 0;
		do {
			System.out.println("\n1: Display all");
			System.out.println("2: Where clause");
			System.out.println("3: Select Clause");
			System.out.println("4: insert Clause");
			System.out.println("5: orderBy Clause");
			System.out.println("6: count Clause");
			System.out.println("7: max Clause");
			System.out.println("8: avg Clause");
			System.out.println("9: distinct Clause");
			System.out.println("10: Exit");

			System.out.print("\nEnter choice : ");
			ch = Integer.parseInt(br.readLine());

			switch (ch) {
			case 1:
				q.displayAll();
				break;
			case 2:
				q.whereClause();
				break;
			case 3:
				q.selectClause();
				break;
			case 4:
				q.insertClause();
				break;
			case 5:
				q.orderByClause();
				break;
			case 6:
				q.count();
				break;
			case 7:
				q.max();
				break;
			case 8:
				q.avg();
				break;
			case 9:
				q.distinctWithCount();
				break;
			case 10:
				System.exit(0);
				break;
			default:
				System.out.println("Enter right choice");
				break;
			}
		} while (ch != 10);
	}

}
