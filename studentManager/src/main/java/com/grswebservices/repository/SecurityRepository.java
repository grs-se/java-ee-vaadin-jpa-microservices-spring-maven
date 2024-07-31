package com.grswebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.grswebservices.model.User;

@Repository
public interface SecurityRepository extends JpaRepository<User, Integer>{

	@Query("select u from User u where u.username=:username")
	User findByUsername(String username);
}
