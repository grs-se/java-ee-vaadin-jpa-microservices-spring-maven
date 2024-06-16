package com.grswebservices.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="STUDENT")
public class Student {

	@Id
	@Column(name="id")
	// use auto-increment feature
	// MySQL
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="grade")
	private int studentGrade;
	
	@Column(name="name")
	private String studentName;
	
	@Column(name="age")
	private int studentAge;
	
	@Transient
	private String address;

	public Student(String name, int age, String address) {
		this.studentName = name;
		this.studentAge = age;
		this.address = address;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return studentName;
	}

	public void setName(String name) {
		this.studentName = name;
	}

	public int getAge() {
		return studentAge;
	}

	public void setAge(int age) {
		this.studentAge = age;
	}

	public int getStudentGrade() {
		return studentGrade;
	}

	public void setStudentGrade(int studentGrade) {
		this.studentGrade = studentGrade;
	}
	
	
	
	
}
