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
		c.setCard_type("Dabit Card");
		c.setAmt(12000L);
		c.setPaydate(new Date());

		Cheque cc = new Cheque();
		cc.setAmt(55000l);
		cc.setCh_type("Crossed");
		cc.setPaydate(new Date());

		session.persist(c);
		session.persist(cc);

		session.beginTransaction().commit();
		session.close();
	}
}
