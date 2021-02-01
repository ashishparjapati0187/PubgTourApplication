package com.stackroute.userservice.service;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.userservice.exceptions.UserAlreadyExistException;
import com.stackroute.userservice.exceptions.UserNotFoundException;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.repository.UserRepository;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


public class UserServiceTest {
	
	
	
	
	
	@Mock
	private transient UserRepository userRepository;
	
	@InjectMocks
	private transient UserServiceImpl userServiceImpl;
	
	private transient User user;
	
	transient Optional<User> userOptional;
	

	@Before
	public void setUpMock()
	{
		MockitoAnnotations.initMocks(this);
		user=new User("736953","ashish","kumar","12345",new Date());
		userOptional=Optional.of(user);
	}
	
	@Test
	public void testSaveUserSuccess() throws UserAlreadyExistException,UserNotFoundException{
		when(userRepository.save(user)).thenReturn(user);
		boolean flag=userServiceImpl.saveUser(user);
		assertEquals(true,flag);
		verify(userRepository,times(1)).save(user);
		
	}
	
	@Test(expected = UserAlreadyExistException.class)
	public void testSaveUserFailure() throws UserAlreadyExistException, UserNotFoundException {
		when(userRepository.findById(user.getUserId())).thenReturn(userOptional);
		boolean flag=userServiceImpl.saveUser(user);
		when(userRepository.save(user)).thenReturn(user);
		assertEquals("register failed",true, flag);
		verify(userRepository).findById(user.getUserId());
	}
	
	@Test
	public void testValidateSuccess() throws UserNotFoundException{
		when(userRepository.findByuserIdAndPassword(user.getUserId(),user.getPassword())).thenReturn(user);
		assertEquals(userServiceImpl.findByUserIdAndPassword(user.getUserId(), user.getPassword()), user);
		verify(userRepository,times(1)).findByuserIdAndPassword(user.getUserId(),user.getPassword());
	}
	
	@Test(expected = UserNotFoundException.class)
	public void testValidateFailure() throws UserNotFoundException{
		when(userRepository.findById(user.getUserId())).thenReturn(userOptional);
		assertEquals(userServiceImpl.findByUserIdAndPassword(user.getUserId(), user.getPassword()), user);
		verify(userRepository,times(1)).findById(user.getUserId());
		
	}
	

}
