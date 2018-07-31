package com.client;

import javax.persistence.StoredProcedureQuery;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import com.config.HibernateUtil;

/**
 * @author AshwinGarad
 * @version Hibernate 5.2 onward
 */

public class Operations {

	public static void procedureCall() {
		Session session = HibernateUtil.getSession();
		StoredProcedureQuery query = session.createNamedStoredProcedureQuery("getStudentById");
		query.setParameter("sid", 1l);

		Object[] s = (Object[]) query.getSingleResult();
		System.out.println(s[0] + "\t" + s[1] + "\t" + s[2]);
		session.close();
	}

	public static void functionCall() {
		Session session = HibernateUtil.getSession();
		NativeQuery<String> query1 = session.getNamedNativeQuery("studentDisplayFun");
		query1.setParameter(1, 1L);

		String s1 = query1.getSingleResult();
		System.out.println(s1);
		session.close();
	}

	public static void main(String[] args) {
		Operations.procedureCall();
		Operations.functionCall();
	}
}
