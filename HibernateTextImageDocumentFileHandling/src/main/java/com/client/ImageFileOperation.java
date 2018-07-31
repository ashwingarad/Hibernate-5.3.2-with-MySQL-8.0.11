package com.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import com.config.HibernateUtil;
import com.model.ImageDocument;

public class ImageFileOperation {
	private File f = null;
	private FileInputStream inputStream;
	private FileOutputStream outputStream;
	private Session session = null;

	public void saveImage(String imageName) {
		f = new File(FindPath.getPath() + imageName);
		try {
			if (!f.exists()) {
				System.out.println("Image not found on given location");
			} else {
				inputStream = new FileInputStream(f);
				byte[] imgData = new byte[(int) f.length()];
				inputStream.read(imgData);

				ImageDocument imageDocument = new ImageDocument();
				imageDocument.setFileName(f.getName());
				imageDocument.setImageFile(imgData);

				session = HibernateUtil.getSession();
				Transaction tx = session.beginTransaction();
				session.persist(imageDocument);
				tx.commit();
				if (tx.getStatus() == TransactionStatus.COMMITTED) {
					System.out.println("Saved successfully");
				} else {
					System.out.println("Something went wrong...");
				}
				session.close();
				inputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeFactory();
		}
	}

	public void retriveImage(String imageName) {
		f = new File(FindPath.getPath() + imageName);
		try {
			if (f.exists()) {
				System.out.println("Image already exists on given location");
			} else {
				session = HibernateUtil.getSession();

				CriteriaBuilder builder = session.getCriteriaBuilder();
				CriteriaQuery<ImageDocument> query = builder.createQuery(ImageDocument.class);
				Root<ImageDocument> root = query.from(ImageDocument.class);
				query.select(root);
				query.where(builder.equal(root.get("fileName"), imageName));

				ImageDocument idoc = session.createQuery(query).getSingleResult();
				session.close();

				outputStream = new FileOutputStream(f);
				outputStream.write(idoc.getImageFile());
				outputStream.close();

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

	public static void main(String[] args) throws Exception {
		ImageFileOperation ifo = new ImageFileOperation();
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
				ifo.saveImage("md_flag.jpg");
				break;
			case 2:
				ifo.retriveImage("md_flag.jpg");
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
