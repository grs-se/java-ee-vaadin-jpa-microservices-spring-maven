package com.grswebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grswebservices.model.Status;

// this annotation is optional: because the JpaRepository
// is extended - Spring knows that it is a repository
@Repository
public interface StatusRepository extends JpaRepository<Status, Integer>{
	// all CRUD operations are implemented by default
}
