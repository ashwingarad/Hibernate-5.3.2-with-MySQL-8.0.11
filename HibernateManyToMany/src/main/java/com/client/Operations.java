package com.client;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;

import com.config.HibernateUtil;
import com.model.Author;
import com.model.Book;

public class Operations {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSession();

		Author a1 = new Author();
		a1.setAname("Ashwin");

		Author a2 = new Author();
		a2.setAname("Mahesh");

		Author a3 = new Author();
		a3.setAname("Rahul");

		Author a4 = new Author();
		a4.setAname("Ashnil");

		Set<Author> list1 = new HashSet<Author>();
		list1.add(a1);
		list1.add(a2);

		Set<Author> list2 = new HashSet<Author>();
		list1.add(a3);
		list1.add(a4);

		Book b1 = new Book();
		b1.setBname("Java");
		b1.setPrice(600);

		Book b2 = new Book();
		b2.setBname("Dotnet");
		b2.setPrice(450);

		Book b3 = new Book();
		b3.setBname("Accountancy");
		b3.setPrice(300);

		Book b4 = new Book();
		b4.setBname("Python");
		b4.setPrice(250);

		Set<Book> blist1 = new HashSet<Book>();
		blist1.add(b1);
		blist1.add(b3);

		Set<Book> blist2 = new HashSet<Book>();
		blist2.add(b2);
		blist2.add(b4);

		a1.setBookSet(blist1);
		a2.setBookSet(blist2);
		a3.setBookSet(blist1);
		a4.setBookSet(blist2);

		b1.setAuthorSet(list1);
		b2.setAuthorSet(list2);
		b3.setAuthorSet(list1);
		b4.setAuthorSet(list2);

		session.save(b1);
		session.save(b2);
		session.save(b3);
		session.save(b4);

		session.beginTransaction().commit();
		session.close();
		HibernateUtil.closeFactory();
		System.out.println("Saved");
	}

}
