package com.stackroute.favouriteservice.repository;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.favouriteservice.domain.UserMatches;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import com.stackroute.favouriteservice.FavouriteserviceApplication;
import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(classes=FavouriteserviceApplication.class)
public class PubgRepoTest {

	@Autowired
	private transient PubgRepository pubgRepository;

	public void setRepo(final PubgRepository pubgRepository) {
		this.pubgRepository = pubgRepository;
	}

	public PubgRepoTest(){
		
	}
	@After
	public void tearDown() {
		pubgRepository.deleteAllInBatch();
	}


	@Test
	public void testSaveMatch() throws Exception {
		pubgRepository.save(new UserMatches("123",null,"erangal","736953","2019-12-12"));
		final UserMatches movie = pubgRepository.getOne("123");
		assertThat(movie.getId()).isEqualTo("123");
	}


	@Test
	public void testdeleteMatch() throws Exception {
		final UserMatches match2 = pubgRepository.save(new UserMatches("123",null,"erangal","736953","2019-12-12"));
				
		pubgRepository.delete(match2);
		assertEquals(Optional.empty(), pubgRepository.findById("123"));

	}


	@Test
	public void testGetAllMatches() throws Exception {
		pubgRepository.save(
				new UserMatches("123",null,"erangal","736953","2019-12-12"));
		pubgRepository.save(new UserMatches("124",null,"vikendi","736953","2019-12-12"));
				
		final List<UserMatches> list = pubgRepository.findAll();
		assertEquals(list.get(0).getTitle(), "erangal");
	}
}
