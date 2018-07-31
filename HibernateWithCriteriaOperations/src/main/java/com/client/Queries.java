package com.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.config.HibernateUtil;
import com.model.Employee;

/**
 * @author Ashwin Garad
 * @version 1.0
 */

public class Queries {

	public void displayAll() {
		Session session = HibernateUtil.getSession();

		CriteriaQuery<Employee> query = session.getCriteriaBuilder().createQuery(Employee.class);
		query.from(Employee.class);
		List<Employee> list = session.createQuery(query).getResultList();
		session.close();

		System.out.println("\nID \t Name \t Salary");
		list.forEach(s -> {
			System.out.println(s.getId() + " " + s.getName() + " " + s.getSalary());
		});
	}

	public void selectClause() {
		Session session = HibernateUtil.getSession();

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<String> query = builder.createQuery(String.class); // Return single string

		Root<Employee> root = query.from(Employee.class);
		query.select(root.get("name")); // By root object we can select particular column
		query.where(builder.equal(root.get("id"), 1));

		String s = session.createQuery(query).getSingleResult();
		session.close();

		System.out.println(s);
	}

	public void whereClause() {
		Session session = HibernateUtil.getSession();

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Employee> query = builder.createQuery(Employee.class);

		Root<Employee> root = query.from(Employee.class); // From Clause return all column name into root obj
		query.select(root); // Select * clause
		query.where(builder.equal(root.get("id"), 1)); // Where clause

		Employee s = session.createQuery(query).getSingleResult();
		session.close();

		System.out.println(s.getId() + " " + s.getName() + " " + s.getSalary());
	}

	public void groupByWithHavingClause() {
		Session session = HibernateUtil.getSession();

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);

		Root<Employee> root = query.from(Employee.class);
		query.multiselect(builder.count(root.get("name")).alias("Count"), root.get("salary"));
		query.groupBy(root.get("salary")); // groupBy clause
		query.having(builder.ge(root.get("salary"), 40000)); // Having clause

		List<Object[]> list = session.createQuery(query).getResultList();
		session.close();
		list.forEach(obj -> {
			long count = (long) obj[0];
			Integer sal = (Integer) obj[1];
			System.out.println(count + " " + sal);
		});
	}

	public void orderByClause() {
		Session session = HibernateUtil.getSession();

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Employee> query = builder.createQuery(Employee.class);

		Root<Employee> root = query.from(Employee.class);
		query.select(root);
		query.orderBy(builder.asc(root.get("salary")));

		List<Employee> list = session.createQuery(query).getResultList();
		session.close();

		list.forEach(s -> {
			System.out.println(s.getId() + " " + s.getName() + " " + s.getSalary());
		});
	}

	public void countFun() {
		Session session = HibernateUtil.getSession();

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);

		Root<Employee> root = criteriaQuery.from(Employee.class);
		criteriaQuery.select(builder.count(root)); // count(*)

		long count = session.createQuery(criteriaQuery).getSingleResult();
		System.out.println(count);
		session.close();
	}

	public void maxFun() {
		Session session = HibernateUtil.getSession();

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);

		Root<Employee> root = criteriaQuery.from(Employee.class);
		criteriaQuery.multiselect(builder.max(root.get("salary")), root.get("name"));

		List<Object[]> list = session.createQuery(criteriaQuery).getResultList();
		session.close();

		list.forEach(obj -> {
			System.out.println(obj[0] + " " + obj[1]);
		});
	}

	public void averageFun() {
		Session session = HibernateUtil.getSession();

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Double> criteriaQuery = builder.createQuery(Double.class);

		Root<Employee> root = criteriaQuery.from(Employee.class);
		criteriaQuery.multiselect(builder.avg(root.get("salary")));

		Double l = session.createQuery(criteriaQuery).getSingleResult();
		session.close();
		System.out.println(l);
	}

	public void distinctFun() {
		Session session = HibernateUtil.getSession();

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);

		Root<Employee> root = criteriaQuery.from(Employee.class);
		criteriaQuery.multiselect(builder.countDistinct(root.get("salary")));

		Long l = session.createQuery(criteriaQuery).getSingleResult();
		session.close();
		System.out.println(l);
	}

	public void likeClause() {
		Session session = HibernateUtil.getSession();

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Employee> query = builder.createQuery(Employee.class);

		Root<Employee> root = query.from(Employee.class);
		query.select(root);
		query.where(builder.like(root.get("name"), "As%"));

		List<Employee> list = session.createQuery(query).getResultList();

		list.forEach(s -> {
			System.out.println(s.getId() + " " + s.getName() + " " + s.getSalary());
		});
		session.close();
	}

	public static void main(String[] args) throws IOException {
		Queries q = new Queries();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int ch = 0;
		do {
			System.out.println("\n1: Display all");
			System.out.println("2: Where clause");
			System.out.println("3: Select Clause");
			System.out.println("4: GroupBy And Having Clause");
			System.out.println("5: orderBy Clause");
			System.out.println("6: count Clause");
			System.out.println("7: max Clause");
			System.out.println("8: avg Clause");
			System.out.println("9: distinct Clause");
			System.out.println("10: like Clause");
			System.out.println("11: Exit");

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
				q.groupByWithHavingClause();
				break;
			case 5:
				q.orderByClause();
				break;
			case 6:
				q.countFun();
				break;
			case 7:
				q.maxFun();
				break;
			case 8:
				q.averageFun();
				break;
			case 9:
				q.distinctFun();
				break;
			case 10:
				q.likeClause();
				break;
			case 11:
				HibernateUtil.closeFactory();
				System.exit(0);
				break;
			default:
				System.out.println("Enter right choice");
				break;
			}
		} while (ch != 11);
	}
}
