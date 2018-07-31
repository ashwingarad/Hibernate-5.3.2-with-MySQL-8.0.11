package com.client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import com.config.HibernateUtil;
import com.model.TextDocument;

public class TextFileOperation {
	private File f = null;
	private Session session;
	private FileOutputStream outputStream;
	private FileInputStream inputStream;

	public void storeFileData(String fileName) {
		try {
			f = new File(FindPath.getPath() + fileName);
			if (!f.exists()) {
				System.out.println("File not found on given location");
			} else {
				inputStream = new FileInputStream(f);
				DataInputStream dataInputStream = new DataInputStream(inputStream);
				BufferedReader br = new BufferedReader(new InputStreamReader(dataInputStream));

				String text, tx = "";
				while ((text = br.readLine()) != null) {
					tx += text + "\n";
				}

				TextDocument document = new TextDocument();
				document.setFileName(f.getName());
				document.setFileText(tx);

				session = HibernateUtil.getSession();
				Transaction transaction = session.beginTransaction();
				session.persist(document);
				transaction.commit();
				if (transaction.getStatus() == TransactionStatus.COMMITTED) {
					System.out.println("Saved");
				}
				session.close();

				dataInputStream.close();
				inputStream.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeFactory();
		}
	}

	public void retriveFileData(String fileName) {
		f = new File(FindPath.getPath() + fileName);
		try {
			if (f.exists()) {
				System.out.println("File already available on given location");
			} else {
				session = HibernateUtil.getSession();
				NativeQuery<Object[]> query = session.createSQLQuery("select * from text_document where file_name = ?");
				query.setParameter(1, fileName);
				Object[] t = query.getSingleResult();
				session.close();
				String fileData = t[1].toString();

				outputStream = new FileOutputStream(f);

				/**
				 * 2 ways you can retrieve data and store into file
				 */

				// ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
				// objectOutputStream.writeObject(fileData);
				// objectOutputStream.close();

				DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
				dataOutputStream.writeBytes(fileData);
				dataOutputStream.close();
				outputStream.close();

				if (f.exists()) {
					System.out.println("file created successfully");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeFactory();
		}
	}

	public static void main(String[] args) throws IOException {
		TextFileOperation tf = new TextFileOperation();
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
				tf.storeFileData("tempFile.txt");
				break;
			case 2:
				tf.retriveFileData("tempFile.txt");
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
