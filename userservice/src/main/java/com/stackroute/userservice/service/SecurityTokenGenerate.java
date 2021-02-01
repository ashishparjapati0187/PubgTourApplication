package com.stackroute.userservice.service;

import java.util.Map;

import com.stackroute.userservice.model.User;



public interface SecurityTokenGenerate {

	
	Map<String,String> generateToken(User user);  // return generate tokens
}
