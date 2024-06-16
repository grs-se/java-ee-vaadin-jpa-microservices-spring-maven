package com.grswebservices.jpa;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractPerson {
	
	private String drivingLicense;
	
	public AbstractPerson( ) {
	
	}

	public String getDrivingLicense() {
		return drivingLicense;
	}

	public void setDrivingLicense(String drivingLicense) {
		this.drivingLicense = drivingLicense;
	}
	
	
}
