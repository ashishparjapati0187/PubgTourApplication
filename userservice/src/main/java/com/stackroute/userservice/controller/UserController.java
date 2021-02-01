package com.stackroute.userservice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.stackroute.userservice.model.User;
import com.stackroute.userservice.service.SecurityTokenGenerate;
import com.stackroute.userservice.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
@EnableWebMvc
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SecurityTokenGenerate tokenGenerator;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user){
		
		try{
	   userService.saveUser(user);
	    return new ResponseEntity<String>("User successfully registered",HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<String>("message=: "+e.getMessage(),HttpStatus.CONFLICT);
		}
		
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User user){
		
		try{
			String userId=user.getUserId();
			String password=user.getPassword();
			if(userId==null || password==null){
				throw new Exception("username and password cannot be null");
				
			}
			User userTemp=userService.findByUserIdAndPassword(userId, password);
			
			String passwordTemp=userTemp.getPassword();
			if(!password.equals(passwordTemp)){
				throw new Exception("invalid credential: password and username incoreect");
			}
			
			Map<String,String> map=tokenGenerator.generateToken(userTemp);
			return new ResponseEntity<Map<String,String>>(map,HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<String>("message=: "+e.getMessage(),HttpStatus.UNAUTHORIZED);
		}
		
		
	}

	

}
