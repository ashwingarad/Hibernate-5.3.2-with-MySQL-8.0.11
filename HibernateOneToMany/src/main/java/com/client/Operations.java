package com.client;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import com.config.HibernateUtil;
import com.model.Department;
import com.model.Teacher;
/**
 * @author AshwinGarad
 * */
public class Operations {
	public void store() {
		Session session = HibernateUtil.getSession();

		Department department = new Department();
		department.setName("Science");

		Teacher t1 = new Teacher();
		t1.setName("ABC");
		t1.setSalary(25000);
		t1.setDepartment(department);

		Teacher t2 = new Teacher();
		t2.setName("PQR");
		t2.setSalary(45000);
		t2.setDepartment(department);

		Set<Teacher> list = new HashSet<Teacher>();
		list.add(t1);
		list.add(t2);

		department.setTeacherSet(list);

		Department department2 = new Department();
		department2.setName("Commerce");

		Teacher t3 = new Teacher();
		t3.setName("XYZ");
		t3.setSalary(30000);
		t3.setDepartment(department2);

		Teacher t4 = new Teacher();
		t4.setName("PQR");
		t4.setSalary(50000);
		t4.setDepartment(department2);

		Set<Teacher> list2 = new HashSet<Teacher>();
		list2.add(t3);
		list2.add(t4);

		department2.setTeacherSet(list2);

		session.save(department);
		session.save(department2);

		session.beginTransaction().commit();
		session.close();
		System.out.println("Saved");
	}

	public void display() {
		Session session = HibernateUtil.getSession();

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Department> query = builder.createQuery(Department.class);

		query.from(Department.class);

		List<Department> list = session.createQuery(query).getResultList();

		list.forEach(d -> {
			System.out.println("\n\nDepartment Name");
			System.out.println(d.getId() + "\t" + d.getName());

			Set<Teacher> teacher = d.getTeacherSet();
			System.out.println("Name of teachers whose working under this department");
			teacher.forEach(t -> {
				System.out.println(t.getId() + "\t" + t.getName() + "\t" + t.getSalary());
			});
		});

		session.close();
	}

	public static void main(String[] args) {
		Operations operations = new Operations();
		operations.display();
	}
}
