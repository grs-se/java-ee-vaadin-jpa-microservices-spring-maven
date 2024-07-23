package com.udemy.inheritance;

import jakarta.persistence.Entity;

@Entity
public class Bus extends Vehicle {
	
	private int numOfPassengers;
	
	public Bus() {
		
	}
	
	public Bus(String name, int numOfPassengers) {
		super(name);
		this.numOfPassengers = numOfPassengers;
	}

	public int getNumOfPassengers() {
		return numOfPassengers;
	}

	public void setNumOfPassengers(int numOfPassengers) {
		this.numOfPassengers = numOfPassengers;
	}
	
}
