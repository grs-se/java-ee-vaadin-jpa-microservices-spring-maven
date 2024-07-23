package com.grswebservices.spring;

public class Student {
	
	private Address address;
	
	public String showInfo() {
		return "Student address is: " + address.getAddress();
	}
}
