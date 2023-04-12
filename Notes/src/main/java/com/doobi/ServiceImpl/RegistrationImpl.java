package com.doobi.ServiceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doobi.Dao.UserDao;
import com.doobi.exception.RegistrationException;
import com.doobi.model.User;
import com.doobi.service.UserRegistrationService;

@Service
public class RegistrationImpl implements  UserRegistrationService{
	
	
	@Autowired
	private UserDao userDao;

	@Override
	public User registerUser(User user) throws RegistrationException {
		
		  Optional<User> us=userDao.findBymobileNumber(user.getMobileNumber());
		  if(us.isPresent()) {
			  throw new RegistrationException("This mobile number already in use");
		  }else {
			  User saveUser = userDao.save(user);
			  return saveUser;
		  }
		
		  
	}
	

}
