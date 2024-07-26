package com.grswebservices.spring;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class Person {
	// There is no need to check the validity of a given field both when dealing with a user input component and when dealing with the database related operations, we just have to do it once.  
	@NotEmpty
	private String name;
	@NotEmpty
	@Email
	private String email;
	// age is not compulsory
	private int age;
	
	public Person() {
	}
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public Person(String name, String email, int age) {
		this.name = name;
		this.email = email;
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return name + " - " + age;
	}
}
