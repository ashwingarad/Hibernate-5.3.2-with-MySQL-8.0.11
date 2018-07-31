package com.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Types;


import org.hibernate.Session;

import com.config.HibernateUtil;
import com.mysql.cj.jdbc.CallableStatement;

/**
 * @author AshwinGarad
 * @version Hibernate 5.2 onward
 */

public class Operations {

	public static void display() {
		Session session = HibernateUtil.getSession();

		session.doWork(connection -> {
			try (CallableStatement statement = (CallableStatement) connection
					.prepareCall("{? = call student_display_function(?)}")) {
				statement.registerOutParameter(1, Types.VARCHAR);
				statement.setLong(2, 1l);
				statement.execute();
				String sname = statement.getString(1);
				System.out.println(sname);
			} catch (Exception e) {
			}
		});
	}

	public static void insertRecord() {
		Session session = HibernateUtil.getSession();

		session.doWork(connection -> {
			try (CallableStatement statement = (CallableStatement) connection
					.prepareCall("{? = call student_insert_function(?,?)}")) {
				statement.registerOutParameter(1, Types.BOOLEAN);
				statement.setString(2, "ABC");
				statement.setInt(3, 564);
				session.beginTransaction();
				statement.execute();
				session.getTransaction().commit();
				System.out.println(statement.getString(1));
			} catch (Exception e) {
			}
		});
		session.close();
	}

	public static void deleteById() {
		Session session = HibernateUtil.getSession();

		session.doWork(connection -> {
			try (CallableStatement statement = (CallableStatement) connection
					.prepareCall("{? = call student_delete_function(?)}")) {
				statement.registerOutParameter(1, Types.BOOLEAN);
				statement.setLong(2, 8l);
				session.beginTransaction();
				statement.execute();
				session.getTransaction().commit();
				System.out.println(statement.getBoolean(1));
			} catch (Exception e1) {
				System.out.println(e1);
			}
		});
		session.close();
	}

	public static void updateRecord() {
		Session session = HibernateUtil.getSession();

		session.doWork(connection -> {
			try (CallableStatement statement = (CallableStatement) connection
					.prepareCall("{? = call student_update_function(?,?,?)}")) {
				statement.registerOutParameter(1, Types.BOOLEAN);
				statement.setLong(2, 7l);
				statement.setString(3, "PQR");
				statement.setInt(4, 471);
				session.beginTransaction();
				statement.execute();
				session.getTransaction().commit();
				System.out.println(statement.getBoolean(1));
			} catch (Exception e1) {
				System.out.println(e1);
			}
		});
		session.close();
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
			System.out.println("5:Exit");
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
				Operations.display();
				break;
			case 5:
				System.exit(0);
				break;
			default:
				System.out.println("Enter right choice");
				break;
			}
		} while (ch != 5);
	}
}
