package com.grswebservices.jpa;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class University {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int universityId;
	
	@Column(name = "university_name")	
	private String universityName;
	
	@OneToMany(mappedBy = "university")
	private List<Student> students;
	
	public University() {
		this.students = new ArrayList<>();
	}
	
	public University(String universityName) {
		// call default constructor to make sure students is instantiated as a new ArrayList
		this();
		this.universityName = universityName;
	}
	
	public void addStudent(Student s) {
		this.students.add(s);
	}

	public int getUniversityId() {
		return universityId;
	}

	public void setUniversityId(int universityId) {
		this.universityId = universityId;
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	
	
}
