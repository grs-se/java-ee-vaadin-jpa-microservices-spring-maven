package com.grswebservices.services;

import java.util.List;

import com.grswebservices.model.Status;

public interface StatusService {
	public void save(Status status);
	public List<Status> findAll();
}
