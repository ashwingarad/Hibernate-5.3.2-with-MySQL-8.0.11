package com.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import com.config.HibernateUtil;
import com.model.Documents;

public class DocumentFileOperation {
	private File f = null;
	private FileInputStream fileInputStream;
	private FileOutputStream fileOutputStream;
	private Session session;

	public void saveDocument(String fileName) {
		try {
			if (!f.exists()) {
				System.out.println("Image not found on given location");
			} else {
				/** Setup data with file object */
				f = new File(FindPath.getPath() + fileName);
				fileInputStream = new FileInputStream(f);
				byte[] docData = new byte[4096];
				fileInputStream.read(docData); // convert file into bytes

				/** Setting up data with object */
				Documents documents = new Documents();
				documents.setFileName(f.getName());
				documents.setDocFile(docData);

				/** Storing data in the mysql database */
				session = HibernateUtil.getSession();
				Transaction tx = session.beginTransaction();
				session.persist(documents);
				tx.commit();

				/** Checking data stored or not by using transaction */
				if (tx.getStatus() == TransactionStatus.COMMITTED) {
					System.out.println("Saved successfully");
				} else {
					System.out.println("Something went wrong..");
				}
				session.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeFactory();
		}
	}

	public void retriveDocument(String fileName) {
		f = new File(FindPath.getPath() + fileName);
		try {
			if (f.exists()) {
				System.out.println("Image already exists on given location");
			} else {
				session = HibernateUtil.getSession();

				/**
				 * Given query created by CriteriaQuery ---> Select * from documents where
				 * fileName=filename;
				 */
				CriteriaBuilder builder = session.getCriteriaBuilder();
				CriteriaQuery<Documents> query = builder.createQuery(Documents.class);
				Root<Documents> root = query.from(Documents.class);
				query.select(root);
				query.where(builder.equal(root.get("fileName"), fileName));

				/** Fetch data from mysql */
				Documents doc = session.createQuery(query).getSingleResult();
				session.close();

				/** Storing data in the given path */
				fileOutputStream = new FileOutputStream(f);
				fileOutputStream.write(doc.getDocFile());
				fileOutputStream.close();

				/** Checking file downloaded or not */
				if (f.exists()) {
					System.out.println("File successfully downloaded");
				} else {
					System.out.println("Something went wrong..");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeFactory();
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		DocumentFileOperation documentFileOperation = new DocumentFileOperation();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int ch = 0;
		do {
			System.out.println("\n1:Insert");
			System.out.println("2:Download");
			System.out.println("3:Exit");
			System.out.print("\nEnter choice: ");
			ch = Integer.parseInt(br.readLine());

			switch (ch) {
			case 1:
				documentFileOperation.saveDocument("Arduino_workshop_sensors.pdf");
				break;
			case 2:
				documentFileOperation.retriveDocument("Arduino_workshop_sensors.pdf");
				break;
			case 3:
				System.exit(0);
				break;
			default:
				System.out.println("Enter right choice");
				break;
			}
		} while (ch != 3);
	}
}
