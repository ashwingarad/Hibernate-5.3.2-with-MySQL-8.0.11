package com.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import com.config.HibernateUtil;
import com.model.Student;

public class AllOperations {

	private void add(Student s) {
		Session session = HibernateUtil.getSession();
		Long id = (Long) session.save(s);
		session.beginTransaction().commit();
		if (id > 0) {
			System.out.println("\nSuccessfully saved...");
		} else {
			System.out.println("\nSomething went wrong..");
		}
		session.close();
	}

	private void update(Student s) {
		Session session = HibernateUtil.getSession();
		session.saveOrUpdate(s);
		session.beginTransaction().commit();
		System.out.println("\nSuccessfully updated");
		session.close();
	}

	private void delete(Long id) {
		Session session = HibernateUtil.getSession();
		Student s = (Student) session.get(Student.class, id);
		session.delete(s);
		session.beginTransaction().commit();
		System.out.println("\nSuccessfully deleted");
		session.close();
	}

	private void display() {
		Session session = HibernateUtil.getSession();

		CriteriaQuery<Student> query = session.getCriteriaBuilder().createQuery(Student.class);
		query.from(Student.class);

		List<Student> list = session.createQuery(query).getResultList();
		session.close();

		// Criteria criteria = session.createCriteria(Student.class);
		// Deprecated method

		list.forEach(s -> {
			System.out.println(s.getId() + "  " + s.getName() + "  " + s.getAddr());
		});
	}

	private Student getByID(Long id) {
		Session session = HibernateUtil.getSession();
		Student s = (Student) session.get(Student.class, id);
		return s;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		AllOperations operations = new AllOperations();
		int ch = 0;
		Student s = null;
		do {
			System.out.println("\n1: Add");
			System.out.println("2: Update");
			System.out.println("3: Delete");
			System.out.println("4: Display");
			System.out.println("5: Exit");
			System.out.print("\nEnter choice : ");
			ch = Integer.parseInt(br.readLine());
			switch (ch) {
			case 1:
				s = new Student();
				System.out.print("\nEnter Name : ");
				String name = br.readLine();

				System.out.print("\nEnter Address : ");
				String addr = br.readLine();

				s.setName(name);
				s.setAddr(addr);
				operations.add(s);
				break;

			case 2:
				System.out.print("\nEnter ID : ");
				s = operations.getByID(Long.parseLong(br.readLine()));

				System.out.println(s.getId() + "  " + s.getName() + "  " + s.getAddr());

				System.out.print("\nEnter Name : ");
				name = br.readLine();
				System.out.print("\nEnter Address : ");
				addr = br.readLine();

				s.setName(name);
				s.setAddr(addr);
				operations.update(s);
				break;

			case 3:
				System.out.print("\nEnter ID : ");
				Long id = Long.parseLong(br.readLine());
				operations.delete(id);
				break;

			case 4:
				operations.display();
				break;

			case 5:
				HibernateUtil.closeFactory();
				System.exit(0);
				break;

			default:
				System.out.println("\nEnter right choice");
				break;
			}
		} while (ch != 5);
	}

}
