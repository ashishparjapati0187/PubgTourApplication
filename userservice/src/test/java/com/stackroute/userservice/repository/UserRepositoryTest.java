package com.stackroute.userservice.repository;

/*import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.userservice.UserserviceApplication;
import com.stackroute.userservice.model.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;



@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@ContextConfiguration(classes=UserserviceApplication.class)
public class UserRepositoryTest {

	
	public UserRepositoryTest() {
		
	}
	@Autowired
	UserRepository userRepository;
	
	private User user;
	
	@Before
	public void setUp()
	{
		user=new User("736953","ashish","kumar","12345",new Date());
	}
	@Test
	public void testRegisterUserSuccess(){
		userRepository.save(user);
		Optional<User> userTemp=userRepository.findById(user.getUserId());
		assertThat(user.equals(userTemp));
		}
	
	
	
	

}*/
