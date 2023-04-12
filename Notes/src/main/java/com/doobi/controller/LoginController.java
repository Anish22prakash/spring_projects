package com.doobi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doobi.Dao.UserDao;
import com.doobi.exception.BadCredentialException;
import com.doobi.model.User;

@RestController
public class LoginController {
	
	@Autowired
	private UserDao userDao;
	
	@GetMapping("/logIn")
	public ResponseEntity<User>getLoggedInUserHandler(Authentication auth) throws BadCredentialException{
		User us= userDao.findBymobileNumber(auth.getName()).orElseThrow(()-> new BadCredentialException("Invalid mobile number and password"));
		
		 //to get the token in body, pass HttpServletResponse inside this method parameter 
		// System.out.println(response.getHeaders(SecurityConstants.JWT_HEADER));
		
		return new ResponseEntity<User>(us, HttpStatus.ACCEPTED);
		
	}

}
