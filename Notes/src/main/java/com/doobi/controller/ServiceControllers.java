package com.doobi.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.doobi.exception.NotesException;
import com.doobi.exception.RegistrationException;

import com.doobi.model.Notes;
import com.doobi.model.User;

import com.doobi.service.NotesService;
import com.doobi.service.UserRegistrationService;

@RestController
public class ServiceControllers {
	
	
	@Autowired
	private UserRegistrationService registrationService;
	
	@Autowired
	private NotesService notesService;
	
	@Autowired
	private PasswordEncoder passwordEndocer;
	
	
	@GetMapping("/hello")
	public String saysTest() {
		return "hello world";
	}
	
	@PostMapping("/notes/registration")
	public ResponseEntity<User> getRegistrationHandler(@RequestBody User user) throws RegistrationException{
		user.setPassword(passwordEndocer.encode(user.getPassword()));
		
		User s = registrationService.registerUser(user);
		return new ResponseEntity<User>(s,HttpStatus.CREATED);
		
	}
	
	
	@PostMapping("/notes/addNotes")
	public ResponseEntity<String> addNotesHandler(@RequestBody Notes notes)throws NotesException{
		String s=notesService.addNotes(notes);
		
		return new ResponseEntity<String>(s,HttpStatus.OK);
	}
	
	@GetMapping("/notes/getNote/{id}")
	public ResponseEntity<Notes> getNotesHandler(@PathVariable("id") Integer userId)throws NotesException{
		Notes notes=notesService.getTheNotes(userId);
		return new ResponseEntity<Notes>(notes,HttpStatus.OK);
	}
	
	

}
