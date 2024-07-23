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
		
		University university = new University("MIT");
		
		Student s1 = new Student();
		s1.setStudentName("Kevin");
		s1.setUniversity(university);
		
		Student s2 = new Student();
		s2.setStudentName("Joe");
		s2.setUniversity(university);

		entityManager.persist(university);
		entityManager.persist(s1);
		entityManager.persist(s2);

		entityManager.getTransaction().commit();
		
		entityManager.close();
		factory.close();
	}

}