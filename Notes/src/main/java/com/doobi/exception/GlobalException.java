package com.doobi.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(LogInException.class)
	public ResponseEntity<myErrorDetails> logInExceptionHandler(LogInException loginExp, WebRequest req){
		
		myErrorDetails err= new myErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(loginExp.getMessage());
		err.setDetails(req.getDescription(false));
		
		return new ResponseEntity<myErrorDetails>(err, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NotesException.class)
	public ResponseEntity<myErrorDetails> notesExceptionHandler(NotesException NotesExp, WebRequest req){
		
		myErrorDetails err= new myErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(NotesExp.getMessage());
		err.setDetails(req.getDescription(false));
		
		return new ResponseEntity<myErrorDetails>(err, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(RegistrationException.class)
	public ResponseEntity<myErrorDetails> registrationExceptionHandler(RegistrationException resExp, WebRequest req){
		
		myErrorDetails err= new myErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(resExp.getMessage());
		err.setDetails(req.getDescription(false));
		
		return new ResponseEntity<myErrorDetails>(err, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BadCredentialException.class)
	public ResponseEntity<myErrorDetails> credentialsExceptionHandler(BadCredentialException resExp, WebRequest req){
		
		myErrorDetails err= new myErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(resExp.getMessage());
		err.setDetails(req.getDescription(false));
		
		return new ResponseEntity<myErrorDetails>(err, HttpStatus.NOT_FOUND);
	}
	
	
	

}
