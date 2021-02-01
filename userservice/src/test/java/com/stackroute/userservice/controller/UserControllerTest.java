package com.stackroute.userservice.controller;


import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.service.SecurityTokenGenerate;
import com.stackroute.userservice.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;



@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {


	@Autowired
	private transient MockMvc mvc;
	
	@MockBean
	private transient UserService userService;
	
	@MockBean
	private transient SecurityTokenGenerate securityTokenGenerate;
	private transient User user;
	
	
	@Before
	public void setUp()
	{
	
		user =new User("736953","ashish","kumar","12345",new Date());
	}
	
	@Test
	public void testRegisterUser() throws Exception{
		when(userService.saveUser(user)).thenReturn(true);
		mvc.perform(post("/user/register").contentType(MediaType.APPLICATION_JSON).content(jsonToString(user))).andExpect(status().isOk());
		verify(userService,times(1)).saveUser(Mockito.any(User.class));
		verifyNoMoreInteractions(userService);
	}
private static String jsonToString(final Object obj) throws JsonProcessingException{
		
		String result;
		try{
			final ObjectMapper mapper=new ObjectMapper();
			final String jsonContent=mapper.writeValueAsString(obj);
			result=jsonContent;
		}
		catch(JsonProcessingException e){
			result="Json processing error";
		}
		return result;
	}

@Test
public void testLoginUser() throws Exception
{
	when(userService.findByUserIdAndPassword(user.getUserId(),user.getPassword())).thenReturn(user);
	mvc.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON).content(jsonToString(user))).andExpect(status().isOk());
	verify(userService,times(1)).findByUserIdAndPassword(user.getUserId(),user.getPassword());
	verifyNoMoreInteractions(userService);
	
	}

		

}
