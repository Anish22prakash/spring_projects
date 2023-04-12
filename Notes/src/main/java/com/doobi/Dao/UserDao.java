package com.doobi.Dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doobi.model.User;

public interface UserDao extends JpaRepository<User, Integer>{

	public Optional<User> findBymobileNumber(String mobileNumber);
	

}
