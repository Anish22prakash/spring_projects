package com.doobi.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.doobi.Dao.UserDao;
import com.doobi.model.User;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> opt=userDao.findBymobileNumber(username);
		if(opt.isPresent()) {
			User us= opt.get();
			List<GrantedAuthority>authorities= new ArrayList<>();
			
			return new org.springframework.security.core.userdetails.User(us.getMobileNumber(),us.getPassword(),authorities);
		}else {
			throw new BadCredentialsException("user Details not found with this mobile number "+ username );
		}
	
	}

	

}
