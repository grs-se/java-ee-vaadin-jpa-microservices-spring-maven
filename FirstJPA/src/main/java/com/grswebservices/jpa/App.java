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
		
		// JPQL Statement
		TypedQuery<Person> query = entityManager
				.createNamedQuery("person.getPersonByName", Person.class);
//		        .createNamedQuery("person.getPersonById", Person.class);
//			    .createNamedQuery("person.getAll", Person.class);
				// Native Query
//				.createNativeQuery("SELECT * FROM person_table WHERE age BETWEEN 20 AND 40", Person.class);
//				.createQuery("SELECT p FROM Person p ORDER BY p.name ASC");
//				.createQuery("SELECT p FROM Person p WHERE p.NAME like '%el'");
//		Query query = entityManager.createQuery("SELECT p FROM Person p WHERE p.age BETWEEN 30 AND 50");
		
		query.setParameter("name", "Adam");
//		query.setParameter("id", 1);
		
		List<Person> people = query.getResultList();
		
		for (Person p : people)
		   System.out.println(p);

		// CRUD
		// Create
		// Student student = new Student("Anna", 56, "60");
		// Person person = new Person("Joe", "joe@gmail.com");
		
		// Find
//		Person person = entityManager.find(Person.class, 1);
//		
//		Person p1 = new Person("Joe", "joe@gmail.com", 35);
//		Person p2 = new Person("Adam", "adam@gmail.com", 56);
//		Person p3 = new Person("Kevin", "kevin@gmail.com", 24);
//		Person p4 = new Person("Anna", "anna@gmail.com", 72);
		
		// entityManager.remove(person);
		
		// entityManager.persist(person);
		
//		entityManager.persist(p1);
//		entityManager.persist(p2);
//		entityManager.persist(p3);
//		entityManager.persist(p4);
//		
		entityManager.getTransaction().commit();
		
		entityManager.close();
		factory.close();
	}

}
