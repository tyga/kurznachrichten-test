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
	
	private User user1 = new User();
	
//	@Before
//	@Override
//	public void setUp(){
//		super.setUp();
//		//user1 = new User();
//	}
	
	@Test (expected = IllegalArgumentException.class)
	public void createUserTestNameAlreadyExists(){
		sms.createUser(USER_NAME, CITY);
		//cleanup if no exception is thrown
		sms.deleteUser(USER_NAME);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void createUserTestNameTooLong(){
		sms.createUser(STR_LENGTH_31, CITY);
		//cleanup if no exception is thrown
		sms.deleteUser(STR_LENGTH_31);
	}
	
	@Test
	public void createUserTestNameNotTooLong(){
		sms.createUser(STR_LENGTH_30, CITY);
		user1.setName(STR_LENGTH_30);
		user1.setCity(CITY);
		Set<User> users = sms.getUsers();
		assertNotNull(users);
		assertTrue(users.contains(user1));
		//cleanup
		sms.deleteUser(STR_LENGTH_30);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void createUserTestNameTooShort(){
		sms.createUser(STR_LENGTH_3, CITY);
		//cleanup if no exception is thrown
		sms.deleteUser(STR_LENGTH_3);
	}
	
	@Test
	public void createUserTestNameNotTooShort(){
		sms.createUser(STR_LENGTH_4, CITY);
		user1.setName(STR_LENGTH_4);
		user1.setCity(CITY);
		Set<User> users = sms.getUsers();
		assertNotNull(users);
		assertTrue(users.contains(user1));
		//cleanup
		sms.deleteUser(STR_LENGTH_4);
	}
	
	@Test (expected = NullPointerException.class)
	public void createUserTestNameNull(){
		sms.createUser(null, CITY);
	}
	
	@Test (expected = NullPointerException.class)
	public void createUserTestCityNull(){
		sms.createUser("UserName", null);
		//cleanup if no exception is thrown
		sms.deleteUser("UserName");
	}
	
//	@After
//	public void cleanUp(){
//		sms.deleteUser(USER_NAME);
//		super.tearDown();
//	}
}
