package com.grswebservices;

public class Student {
	
	private int id;
	private String name;
	private int age;
	
	public Student(int id, String name, int age) {
		this.id = 1;
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
	
	

}
