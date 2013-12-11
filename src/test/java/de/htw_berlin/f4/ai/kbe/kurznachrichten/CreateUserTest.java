package de.htw_berlin.f4.ai.kbe.kurznachrichten;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CreateUserTest extends TestShortMessageServiceInit {

	// /////////////////////////////////////////////////////
	// createUserTests
	// /////////////////////////////////////////////////////
	
	private User user1;
	
	@Before
	@Override
	public void setUp(){
		super.setUp();
		user1 = new User();
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void createUserTestNameAlreadyExists(){
		sms.createUser(USER_NAME, CITY);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void createUserTestNameTooLong(){
		sms.createUser(STR_LENGTH_31, CITY);
	}
	
	@Test
	public void createUserTestNameNotTooLong(){
		sms.createUser(STR_LENGTH_30, CITY);
		user1.setName(STR_LENGTH_30);
		user1.setCity(CITY);
		Set<User> users = sms.getUsers();
		if(!users.contains(user1)){
			fail("User with a name length of 30 characters should have been created, but wasn't.");
		}
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void createUserTestNameTooShort(){
		sms.createUser(STR_LENGTH_3, CITY);
	}
	
	@Test
	public void createUserTestNameNotTooShort(){
		sms.createUser(STR_LENGTH_4, CITY);
		user1.setName(STR_LENGTH_4);
		user1.setCity(CITY);
		Set<User> users = sms.getUsers();
		if(!users.contains(user1)){
			fail("User with a name length of 4 characters should have been created, but wasn't.");
		}
	}
	
	@Test (expected = NullPointerException.class)
	public void createUserTestNameNull(){
		sms.createUser(null, CITY);
	}
	
	@Test (expected = NullPointerException.class)
	public void createUserTestCityNull(){
		sms.createUser("UserName", null);
	}
	
	@After
	public void cleanUp(){
		sms.deleteUser(USER_NAME);
		super.tearDown();
	}
}
