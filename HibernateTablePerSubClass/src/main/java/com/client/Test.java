package com.client;

import java.util.Date;

import org.hibernate.Session;

import com.config.HibernateUtil;
import com.model.Card;
import com.model.Cheque;

public class Test {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSession();

		Card c = new Card();
		c.setAmt(60000l);
		c.setPaydate(new Date());
		c.setCard_type("Credit Card");

		Cheque cc = new Cheque();
		cc.setAmt(70000l);
		cc.setPaydate(new Date());
		cc.setCh_type("Crossed");

		session.persist(c);
		session.persist(cc);

		session.beginTransaction().commit();
		session.close();
		HibernateUtil.closeFactory();
	}

}
