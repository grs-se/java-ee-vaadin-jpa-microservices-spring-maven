package com.grswebservices.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Student {

	// use primary key to find item very fast with logo-rhythmic running time complexity
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column
	@NotEmpty(message = "Name cannot be null or empty")
	private String name;
	
	@Column
	@Min(value = 0, message = "Age cannot be smaller than 0")
	@Max(value = 120, message = "Age cannot be bigger than 120")
	@NotNull(message = "Age must be specified")
	private int age;

	@Column
	@Min(value = 0, message = "Zip code cannot be smaller than 0")
	@Max(value = 9999, message = "Zip code cannot be bigger than 9999")
	@NotNull(message = "Zip code cannot be empty")
	@Digits(integer = 4, fraction = 0, message = "Zip code is 4 digit integer")
	private int zipCode;

	@Column
	@NotEmpty(message = "Country must be specified")
	private String country;
	
	@ManyToOne
	// many = refers to the owning entity (student)
	// one = refers to the reference entity (status)
	@JoinColumn
	// JoineColumn = jpa will assign will assign an additional column in the db table, using the primary key of the Status class, which is an int id, to reference the status in the Student class, 
	private Status status;
	
	public Student() {
		
	}

	public Student(String name, int age, int zipCode, String country, Status status) {
		this.name = name;
		this.age = age;
		this.zipCode = zipCode;
		this.country = country;
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
