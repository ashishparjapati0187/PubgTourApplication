package com.stackroute.favouriteservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.favouriteservice.domain.UserMatches;
import com.stackroute.favouriteservice.exception.MatchAlreadyExistsException;
import com.stackroute.favouriteservice.exception.MatchNotFoundException;
import com.stackroute.favouriteservice.repository.PubgRepository;

@Service
public class PubgServiceImpl  implements PubgService{

	
	
	private final transient PubgRepository pubgRepository;
	
	@Autowired    // it is optional if not used still spring will autowired it
	public PubgServiceImpl(final PubgRepository pubgRepository) {
			super();
			this.pubgRepository = pubgRepository;
		}
	
	@Override
	public boolean saveMatch(final UserMatches match) throws MatchAlreadyExistsException {
		// TODO Auto-generated method stub
		
		System.out.println("in save function="+match);
		final Optional<UserMatches> object=pubgRepository.findById(match.getId());  //id will save matchId
		System.out.println("in service save="+match);
		if(object.isPresent())
		{
			throw new MatchAlreadyExistsException("cannot save movie it already exist");
			
		}
		
		pubgRepository.save(match);
		return true;
	}



	@Override
	public boolean deleteMatchById(String id) throws MatchNotFoundException {
		// TODO Auto-generated method stub
		final UserMatches movie=pubgRepository.findById(id).orElse(null);
		if(movie==null)
		{
		 throw new MatchNotFoundException("movie not found : cant delete");
		}
		pubgRepository.delete(movie);
		return true;
	}

	@Override
	public List<UserMatches> getMyMatches(String userId) {
		// TODO Auto-generated method stub
		return pubgRepository.findAll();
	}
	
}
