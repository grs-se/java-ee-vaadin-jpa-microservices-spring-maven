package com.grswebservices.jpa;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class App {

	public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("com.grswebservices.jpa");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		Person person = new Person();
		person.setName("Kevin");
		person.setAge(23);
		person.setDrivingLicense("XHB175");
		
		entityManager.persist(person);
		
		entityManager.getTransaction().commit();
		
		entityManager.close();
		factory.close();
	}

}