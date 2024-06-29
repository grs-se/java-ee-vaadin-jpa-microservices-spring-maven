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
		
		Article article = new Article("Albert Einstein - Relativity");
		entityManager.persist(article);
		
		entityManager.getTransaction().commit();
		
		entityManager.close();
		factory.close();
	}

}