
package com.udemy.inheritance;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "C")
public class Car extends Vehicle {

	private int speed;
	
	public Car() {
		
	}
	
	public Car(String name, int speed) {
		super(name);
		this.speed = speed;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	
}
