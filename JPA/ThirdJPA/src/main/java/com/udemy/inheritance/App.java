package com.udemy.inheritance;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class App {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("com.grswebservices.jpa");
		EntityManager entityManager = factory.createEntityManager();
		
		entityManager.getTransaction().begin();
		
		// make changes to the DB

		// doesn't matter if using the Vehicle super class or if we are using the bus as the concrete implementation
		// JPA will insert the values into the car and into the bus db tables.
		Vehicle bus = new Bus("Name of Bus", 100);
//		Bus bus = new Bus("Name of Bus", 100);
		Vehicle car = new Car("Name of Car", 150);
		
		entityManager.persist(bus);
		entityManager.persist(car);
		
		entityManager.getTransaction().commit();
		
		entityManager.close();
		factory.close();
	}
}