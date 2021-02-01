package com.stackroute.favouriteservice.controller;

import java.util.ArrayList;
import static org.mockito.Mockito.*;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesAnnotationIntrospector;
import com.stackroute.favouriteservice.domain.UserMatches;
import com.stackroute.favouriteservice.service.PubgServiceImpl;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(PubgController.class)
public class PubgControllerTest {

	@Autowired
	private transient MockMvc mvc;
	@MockBean
	private transient PubgServiceImpl service;
	@InjectMocks
	private PubgController movieController;
	private transient UserMatches match;
	static List<UserMatches> matches;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(movieController).build();
		matches = new ArrayList<>();
		match = new UserMatches("123",null,"erangal","736953","2019-12-12");
		matches.add(match);
		match = new UserMatches("1234",null,"vikendi","736953","2019-12-12");
		matches.add(match);
	}

	@Test
	public void testSaveNewMatchSuccess() throws Exception {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI3MzY5NTMiLCJpYXQiOjE1NTM0NTI1ODh9.QfZ74N5xUS-JZvVOCgzdIL6tb2iqx6mgxjkW6SZnS50";
		when(service.saveMatch(match)).thenReturn(true);
	
		mvc.perform(post("/favourite/match").header("authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON).content(jsonToString(match))).andExpect(status().isCreated());
		verify(service, times(1)).saveMatch(Mockito.any(UserMatches.class));
		verifyNoMoreInteractions(service);
	}



	@Test
	public void testDeleteMatchById() throws Exception {
		when(service.deleteMatchById("1234")).thenReturn(true);
		mvc.perform(delete("/favourite/{id}", "1234")).andExpect(status().isOk());
		verify(service, times(1)).deleteMatchById("1234");
		verifyNoMoreInteractions(service);
	}


	@Test
	public void testGetAllMyMatches() throws Exception {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI3MzY5NTMiLCJpYXQiOjE1NTM0NTI1ODh9.QfZ74N5xUS-JZvVOCgzdIL6tb2iqx6mgxjkW6SZnS50";
		when(service.getMyMatches("736953")).thenReturn(matches);
		mvc.perform(get("/favourite/allMatches").header("authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON).content(jsonToString(match))).andExpect(status().isCreated());
		verify(service, times(1)).getMyMatches("736953");
		verifyNoMoreInteractions(service);
	}

	private static String jsonToString(final Object obj) throws JsonProcessingException {
		String result;
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			result = jsonContent;
		} catch (JsonProcessingException e) {
			result = "Json processing error";
		}
		return result;
	}
}
