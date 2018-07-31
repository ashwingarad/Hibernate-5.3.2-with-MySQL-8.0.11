package com.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.type.StandardBasicTypes;

import com.config.HibernateUtil;
import com.model.Employee;

/**
 * @author AshwinGarad
 */
public class SQLQueries {

	public void insertClause() {
		Session session = HibernateUtil.getSession();

		NativeQuery<Object> query = session.createNativeQuery("Insert into employee(id, name, salary) values(?,?,?)");
		query.setParameter(1, null); // Auto increment
		query.setParameter(2, "ABC");
		query.setParameter(3, 25000);

		session.beginTransaction();
		query.executeUpdate();
		session.getTransaction().commit();
		System.out.println("Saved successfully");
		session.close();
	}

	public void displayAll() {
		Session session = HibernateUtil.getSession();

		NativeQuery<Employee> query = session.createNativeQuery("Select * from employee");
		query.addEntity(Employee.class);

		List<Employee> list = query.getResultList();

		list.forEach(e -> {
			System.out.println(e.getId() + "\t" + e.getName() + "\t" + e.getSalary());
		});
		session.close();
	}

	public void selectClause() {
		Session session = HibernateUtil.getSession();

		NativeQuery<Object[]> query = session.createNativeQuery("Select name, salary from employee");

		List<Object[]> list = query.getResultList();

		list.forEach(e -> {
			System.out.println(e[0] + "\t" + e[1]);
		});
		session.close();
	}

	public void whereClause() {
		Session session = HibernateUtil.getSession();

		NativeQuery<Object[]> query = session.createNativeQuery("Select name, salary from employee where id=?");
		query.setParameter(1, 1);

		List<Object[]> list = query.getResultList();

		list.forEach(e -> {
			System.out.println(e[0] + "\t" + e[1]);
		});
		session.close();

	}

	public void max() {
		Session session = HibernateUtil.getSession();

		NativeQuery<Object> query = session.createNativeQuery("Select max(salary) from employee");

		List<Object> list = query.getResultList();

		list.forEach(e -> {
			System.out.println(e.toString());
		});
	}

	public void avg() {
		Session session = HibernateUtil.getSession();

		NativeQuery<Object> query = session.createNativeQuery("Select avg(salary) from employee");

		List<Object> list = query.getResultList();

		list.forEach(e -> {
			System.out.println(e);
		});
		session.close();
	}

	public void distinctWithCount() {
		Session session = HibernateUtil.getSession();

		NativeQuery<Object> query = session.createNativeQuery("Select count(distinct salary) from employee");

		List<Object> list = query.getResultList();

		list.forEach(e -> {
			System.out.println(e);
		});
	}

	public void scalar() {
		Session session = HibernateUtil.getSession();

		NativeQuery<Object[]> query = session.createNativeQuery("Select name, salary from employee");
		query.addScalar("name", StandardBasicTypes.STRING);
		query.addScalar("salary", StandardBasicTypes.INTEGER);

		List<Object[]> list = query.getResultList();

		list.forEach(e -> {
			System.out.println(e[0] + "\t" + e[1]);
		});
		session.close();
	}

	public void namedNativeQuery() {
		Session session = HibernateUtil.getSession();

		NativeQuery<Employee> query = session.getNamedNativeQuery("q1");

		query.setParameter("id", 1l);

		List<Employee> list = query.getResultList();

		list.forEach(e -> {
			System.out.println(e.getName() + "\t" + e.getSalary());
		});
		session.close();
	}

	public void orderByClause() {
		Session session = HibernateUtil.getSession();

		NativeQuery<Object[]> query = session.createNativeQuery("Select name, salary from employee order by name asc");
		query.addScalar("name", StandardBasicTypes.STRING);
		query.addScalar("salary", StandardBasicTypes.INTEGER);

		List<Object[]> list = query.getResultList();

		list.forEach(e -> {
			System.out.println(e[0] + "\t" + e[1]);
		});
	}

	public void count() {
		Session session = HibernateUtil.getSession();

		NativeQuery<Object> query = session.createNativeQuery("Select count(salary) from employee");

		List<Object> list = query.getResultList();

		list.forEach(e -> {
			System.out.println(e);
		});
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
