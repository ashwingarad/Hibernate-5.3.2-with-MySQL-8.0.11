package com.generator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class MyGenerator implements IdentifierGenerator {
	private String newId = null;

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = session.connection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select licence_no from licence");

			String id = null;
			if (resultSet != null) {
				while (resultSet.next()) {
					if (resultSet.isLast()) {
						id = resultSet.getString("licence_no");
						System.out.println("Old id : " + id);
					}
				}
			} else {
				int year = Calendar.getInstance().get(Calendar.YEAR);
				newId = "1-" + year;
			}

			if (id != null) {
				int pos = id.indexOf('-');
				String oldNum = "";
				for (int i = 0; i < pos; i++) {
					oldNum += id.charAt(i);
				}
				oldNum = Integer.toString((Integer.valueOf(oldNum) + 1));
				newId = oldNum + "-" + Calendar.getInstance().get(Calendar.YEAR);
				System.out.println("New Id : " + newId);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return newId;
	}
}
