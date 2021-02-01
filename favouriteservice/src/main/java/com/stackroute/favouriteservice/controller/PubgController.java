package com.stackroute.favouriteservice.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.favouriteservice.domain.UserMatches;
import com.stackroute.favouriteservice.exception.MatchAlreadyExistsException;
import com.stackroute.favouriteservice.exception.MatchNotFoundException;
import com.stackroute.favouriteservice.service.PubgService;


import io.jsonwebtoken.Jwts;

@RestController
@CrossOrigin  
@RequestMapping(path="/favourite")
public class PubgController {

	
	 //eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI3MzY5NTMiLCJpYXQiOjE1NTM2NzIwMDh9.p6MqFbmeZAVeo2I9EGol8Nb-PeP1laSRd5-DU_SLLNI
	private  PubgService pubgService;
	
	//@Autowired       //it is optional 
	public PubgController(final PubgService pubgService)
	{
		this.pubgService=pubgService;
	}
	
	

	
	@PostMapping("/match")
	public ResponseEntity<?> saveNewMatch(@RequestBody final UserMatches match,HttpServletRequest request,HttpServletResponse response)
	{
		
		System.out.println("movie save object="+match);
		ResponseEntity<?> responseEntity;
		final String authHeader=request.getHeader("authorization");
		System.out.println("authheader="+authHeader);
		final String token=authHeader.substring(7);
		String userId=Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
		System.out.println("userId from token="+userId);
		try{
			match.setUserId(userId);
			pubgService.saveMatch(match);
		 responseEntity=new ResponseEntity<UserMatches>(match,HttpStatus.CREATED);
		}
		catch(MatchAlreadyExistsException e){
			responseEntity=new ResponseEntity<String>("message="+e.getMessage()+"//",HttpStatus.CONFLICT);
		}
		return  responseEntity;
	}

	
	@DeleteMapping("/{id}") // added
	public ResponseEntity<?> deleteMovieById(@PathVariable final String id)
	{

		ResponseEntity<?> responseEntity;
		try
		{
			pubgService.deleteMatchById(id);
			responseEntity=new ResponseEntity<String>("movie succesfully deleted",HttpStatus.OK);
			
		}
		catch(MatchNotFoundException e)
		{
			responseEntity=new ResponseEntity<String>("message="+e.getMessage(),HttpStatus.CONFLICT);
		}
		return responseEntity;

	}

	@GetMapping("/allMatches")
	public @ResponseBody ResponseEntity<List<UserMatches>> fetchMyMatches(final ServletRequest req,final ServletResponse res)
	{
		final HttpServletRequest request=(HttpServletRequest) req;
		final String authHeader=request.getHeader("authorization");
		final String token =  authHeader.split(" ")[1];
		String userId=Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
		return new ResponseEntity<List<UserMatches>>(pubgService.getMyMatches(userId),HttpStatus.CREATED);
	}


//	@GetMapping("/movies")
//	public @ResponseBody ResponseEntity<List<>> fetchMyMovies(final ServletRequest req,final ServletResponse res)
//	{
//		return null;
//	}
//	

	
	
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
}
