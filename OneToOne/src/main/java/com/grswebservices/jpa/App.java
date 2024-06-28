package com.grswebservices.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class App {

	public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("com.grswebservices.jpa");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		Address address = new Address();
		address.setAddressName("Wall Street");
		address.setZipCode(111);
		
		Employee employee = new Employee();
		employee.setEmployeeName("Joe Smith");
		employee.setAddress(address);
		
		address.setEmployee(employee);

		entityManager.persist(employee);
		entityManager.persist(entityManager);
		
		entityManager.getTransaction().commit();
		
		entityManager.close();
		factory.close();
	}

}