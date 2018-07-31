package com.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.Session;
import org.hibernate.procedure.ProcedureCall;

import com.config.HibernateUtil;

/**
 * @author AshwinGarad
 * @version Hibernate 5.2 onward
 */

public class Operations {

	public static void displayAll() {
		Session session = HibernateUtil.getSession();

		ProcedureCall call = session.createStoredProcedureCall("student_displayAll");
		List<Object[]> list = call.getResultList();
		list.forEach(s -> {
			System.out.println(s[0] + "\t" + s[1] + "\t" + s[2]);
		});
		session.close();
	}

	public static void displayById() {
		Session session = HibernateUtil.getSession();

		ProcedureCall call = session.createStoredProcedureCall("student_displayById");

		/**
		 * ------------------------------------------------------------------
		 * 
		 * By JPA
		 * 
		 */
		call.registerStoredProcedureParameter("sid", Long.class, ParameterMode.IN);
		call.setParameter("sid", 1l);

		List<Object[]> list = call.getResultList();
		list.forEach(s -> {
			System.out.println(s[0] + "\t" + s[1] + "\t" + s[2]);
		});

		/**
		 * ------------------------------------------------------------------
		 * 
		 * By Hibernate
		 * 
		 */

		call.registerParameter("sid", Long.class, ParameterMode.IN).bindValue(1l);

		List<Object[]> list1 = call.getResultList();
		list1.forEach(s -> {
			System.out.println(s[0] + "\t" + s[1] + "\t" + s[2]);
		});
		session.close();
	}

	public static void insertRecord() {
		Session session = HibernateUtil.getSession();

		ProcedureCall call = session.createStoredProcedureCall("student_insert");
		call.registerParameter("sname", String.class, ParameterMode.IN).bindValue("XYZ");
		call.registerParameter("smark", Integer.class, ParameterMode.IN).bindValue(456);

		/**
		 * execute() return false if record is inserted
		 */
		boolean flag = call.execute();
		session.beginTransaction().commit();

		/**
		 * executeUpdate() return 1 if record is inserted. This method required active
		 * transaction.
		 */
		session.beginTransaction();
		int flag1 = call.executeUpdate();
		session.getTransaction().commit();

		session.close();

		System.out.println(flag + " " + flag1);
	}

	public static void deleteById() {
		Session session = HibernateUtil.getSession();

		ProcedureCall call = session.createStoredProcedureCall("student_delete");
		call.registerParameter("sid", Long.class, ParameterMode.IN).bindValue(10l);
		session.beginTransaction();
		int flag = call.executeUpdate();
		session.getTransaction().commit();
		session.close();
		System.out.println(flag);
	}

	public static void updateRecord() {
		Session session = HibernateUtil.getSession();

		ProcedureCall call = session.createStoredProcedureCall("student_update");
		call.registerParameter("sid", Long.class, ParameterMode.IN).bindValue(8l);
		call.registerParameter("sname", String.class, ParameterMode.IN).bindValue("XYZ");
		call.registerParameter("smark", Integer.class, ParameterMode.IN).bindValue(456);
		session.beginTransaction();
		int flag = call.executeUpdate();
		session.getTransaction().commit();
		session.close();
		System.out.println(flag);
	}

	public static void main(String[] args) throws IOException {
		// All Stored procedures are in src/main/resource folder
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int ch = 0;
		do {
			System.out.println("\n1:Insert");
			System.out.println("2:Update");
			System.out.println("3:Delete");
			System.out.println("4:Display");
			System.out.println("5:Display by id");
			System.out.println("6:Exit");
			System.out.println("\nEnter choice : ");
			ch = Integer.parseInt(br.readLine());

			switch (ch) {
			case 1:
				Operations.insertRecord();
				break;
			case 2:
				Operations.updateRecord();
				break;
			case 3:
				Operations.deleteById();
				break;
			case 4:
				Operations.displayAll();
				break;
			case 5:
				Operations.displayById();
				break;
			case 6:
				System.exit(0);
				break;
			default:
				System.out.println("Enter right choice");
				break;
			}
		} while (ch != 6);
	}
}
