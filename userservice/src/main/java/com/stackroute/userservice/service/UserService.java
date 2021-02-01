package com.stackroute.userservice.service;

import com.stackroute.userservice.exceptions.UserAlreadyExistException;
import com.stackroute.userservice.exceptions.UserNotFoundException;
import com.stackroute.userservice.model.User;

public interface UserService {
	
	boolean saveUser(User user) throws UserAlreadyExistException,UserNotFoundException;
	public User findByUserIdAndPassword(String userId,String password) throws UserNotFoundException;

}
