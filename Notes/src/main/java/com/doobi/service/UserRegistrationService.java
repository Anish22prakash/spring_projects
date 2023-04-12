package com.doobi.service;

import com.doobi.exception.RegistrationException;
import com.doobi.model.User;

public interface UserRegistrationService {
	
	public User registerUser(User user) throws RegistrationException;

}
