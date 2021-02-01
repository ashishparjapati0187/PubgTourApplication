package com.stackroute.userservice.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.stackroute.userservice.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@Service     // generated token by algorrithm with returning map of token and message
public class JwtSecurityTokenGeneratorImpl implements SecurityTokenGenerate {

	@Override
	public Map<String, String> generateToken(User user) {
		// TODO Auto-generated method stub
		String jwtToken="";
		  // later add expiration Date
		jwtToken=Jwts.builder().setSubject(user.getUserId()).setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256,"secretkey").compact();
		
		Map<String,String> map=new HashMap<>();
		map.put("token", jwtToken);
		map.put("message", "User successfully logged in");
		return map;
	}

	
}
