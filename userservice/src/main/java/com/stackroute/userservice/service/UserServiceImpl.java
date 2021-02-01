package com.stackroute.userservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.userservice.exceptions.UserAlreadyExistException;
import com.stackroute.userservice.exceptions.UserNotFoundException;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.repository.UserRepository;



@Service
public class UserServiceImpl  implements UserService{

	
	
	
	 private final UserRepository userRepo;
	 
	 @Autowired 
	public UserServiceImpl(UserRepository userRepo) {
		// TODO Auto-generated constructor stub
		this.userRepo=userRepo;
	}

	@Override
	public boolean saveUser(User user) throws UserAlreadyExistException, UserNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> userOpt=userRepo.findById(user.getUserId());
		if(userOpt.isPresent()){
			throw new UserAlreadyExistException("user already exist");
			
		}
		userRepo.save(user);
		return true;
	}

	@Override
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {
		// TODO Auto-generated method stub
		User user=userRepo.findByuserIdAndPassword(userId, password);
		if(user==null){
			throw new UserNotFoundException("userId and pass word not found");
		}
		return user;
	}

}
