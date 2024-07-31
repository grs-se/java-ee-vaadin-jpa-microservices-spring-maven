package com.grswebservices.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grswebservices.model.Status;
import com.grswebservices.repository.StatusRepository;

@Service
@Transactional(readOnly = true) // all methods of this class will inherit transactional behaviour
// readonly = A boolean flag that can be set to true if the transaction iseffectively read-only, allowing for corresponding optimizations at runtime. 
public class StatusServiceImpl implements StatusService {
	
	@Autowired
	private StatusRepository statusRepository;

	@Override
	// default value is readOnly = false
	// save method is not readonly because it changes values in the db
	@Transactional
	public void save(Status status) {
		statusRepository.save(status);
	}

	@Override
	public List<Status> findAll() {
		return statusRepository.findAll();
	}

}
