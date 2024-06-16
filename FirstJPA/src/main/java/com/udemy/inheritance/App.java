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
		Bus bus = new Bus("Name of Bus", 100);
		Car car = new Car("Name of Car", 150);
		
		entityManager.persist(bus);
		entityManager.persist(car);
		
		entityManager.getTransaction().commit();
		
		entityManager.close();
		factory.close();
	}
}


