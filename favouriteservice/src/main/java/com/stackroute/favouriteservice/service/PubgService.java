package com.stackroute.favouriteservice.service;

import java.util.List;

import com.stackroute.favouriteservice.domain.UserMatches;
import com.stackroute.favouriteservice.exception.MatchAlreadyExistsException;
import com.stackroute.favouriteservice.exception.MatchNotFoundException;


/**
 * @author Ashish
 *
 */
public interface PubgService {
	
	boolean saveMatch(UserMatches match) throws MatchAlreadyExistsException;

	boolean deleteMatchById(String id) throws MatchNotFoundException;
	
	
	List<UserMatches> getMyMatches(String userId);
}
