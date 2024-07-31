package com.grswebservices.services;

import com.grswebservices.model.User;

public interface SecurityService {
	public void save(String username, String password);
	public void logout();
}
