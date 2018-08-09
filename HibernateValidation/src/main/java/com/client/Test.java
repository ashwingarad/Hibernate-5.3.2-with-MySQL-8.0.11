package com.client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Set;

import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import com.config.HibernateUtil;
import com.model.Employee;

public class Test {

	public static void main(String[] args) throws FileNotFoundException, NoSuchMethodException, SecurityException {
		Session session = HibernateUtil.getSession();

		Employee employee = new Employee();
		employee.setName("Abraham Lincoln");
		employee.setSalary(4562132);

		Configuration<?> config = Validation.byDefaultProvider().configure();
		config.addMapping(new FileInputStream("employeeXMLValidation.xml"));
		ValidatorFactory validatorFactory1 = config.buildValidatorFactory();
		Validator validator1 = validatorFactory1.getValidator();

		Set<ConstraintViolation<Employee>> validationErrors1 = validator1.validate(employee);

		if (!validationErrors1.isEmpty()) {
			for (ConstraintViolation<Employee> error : validationErrors1) {
				System.out.println(error.getMessageTemplate() + "::" + error.getInvalidValue() + "::"
						+ error.getPropertyPath() + "::" + error.getMessage());
			}
		}

		session.save(employee);
		session.beginTransaction().commit();

		if (session.getTransaction().getStatus() == TransactionStatus.COMMITTED) {
			System.out.println("Saved");
		}

		session.close();
		HibernateUtil.closeFactory();
	}

}
