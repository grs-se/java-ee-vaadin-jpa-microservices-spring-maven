package com.grswebservices;

public class Student {

	private String studentName;
	// composition
	private Address address;
	
	

	public Student(String studentName, Address address) {
		super();
		this.studentName = studentName;
		this.address = address;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	public void showInfo() {
		System.out.println("Address is: " + address);
	}
	
	// define init() and destroy() methods
	public void initStudent() {
		System.out.println("This is the init() method...");
	}
	
	// this is not called when the scope="prototype"
	// WHY? memory leak and memory management
	public void destroyStudent() {
		System.out.println("This is the destroy() method...");
	}
	
	
}
