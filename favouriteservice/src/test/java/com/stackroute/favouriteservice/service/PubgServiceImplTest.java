package com.stackroute.favouriteservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.favouriteservice.domain.UserMatches;
import com.stackroute.favouriteservice.exception.MatchAlreadyExistsException;
import com.stackroute.favouriteservice.exception.MatchNotFoundException;
import com.stackroute.favouriteservice.repository.PubgRepository;


import static org.junit.Assert.*;



import static org.mockito.Mockito.*;
public class PubgServiceImplTest {

	@Mock
	private transient PubgRepository pubgRepo;
	
	private transient UserMatches match;
	
	@InjectMocks
	private transient PubgServiceImpl pubgServiceImpl;
	
	transient Optional<UserMatches> options;
	
	@Before
	public void setUpMock()
	{
		MockitoAnnotations.initMocks(this);
		match=new UserMatches("123",null,"erangal","736953","2019-12-12");
		options=Optional.of(match);
	}
	
	@Test
	public void testMockCreation(){
		assertNotNull("jpa repository creation fails:in jectmock error ",match);
	}
	
	@Test
	public void testSaveSuccess() throws MatchAlreadyExistsException{
		when(pubgRepo.save(match)).thenReturn(match);
		final boolean flag=pubgServiceImpl.saveMatch(match);
		assertTrue("saving failed call to movieServiceImpl failded",flag);
		verify(pubgRepo,times(1)).save(match);
		verify(pubgRepo,times(1)).findById(match.getId());
		
	}
	
	@Test(expected = MatchAlreadyExistsException.class)
	public void testSaveMatchFailure() throws MatchAlreadyExistsException
	{
		when(pubgRepo.findById("123")).thenReturn(options);
		when(pubgRepo.save(match)).thenReturn(match);
		final boolean flag=pubgServiceImpl.saveMatch(match);
		assertFalse("saving movie failed",flag);
		verify(pubgRepo,times(1)).findById(match.getId());
		
		
	}
	
	
	@Test
	public void testDeleteMatchById() throws MatchNotFoundException{
		when(pubgRepo.findById("123")).thenReturn(options);
		doNothing().when(pubgRepo).delete(match);
		final boolean flag=pubgServiceImpl.deleteMatchById("123");
		assertTrue("cannot be deleted already exist",flag);
		verify(pubgRepo,times(1)).findById(match.getId());
		verify(pubgRepo,times(1)).delete(match);
	}
	

	



	@Test
	public void testGetAllMatches(){
		final List<UserMatches> pubgList=new ArrayList<UserMatches>(1);
		when(pubgRepo.findByUserId(match.getUserId())).thenReturn(pubgList);
		final List<UserMatches> movieTemp=pubgServiceImpl.getMyMatches(match.getUserId());
		assertEquals(pubgList, movieTemp);
		verify(pubgRepo,times(1)).findAll();
	}

}
