package de.htw_berlin.f4.ai.kbe.kurznachrichten;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GetUsersTest extends TestShortMessageServiceInit {

	// /////////////////////////////////////////////////////
	// getUsersTests
	// /////////////////////////////////////////////////////
	
	
	private Set<User> users;
	private User user1;
	private User user2;
	private User user3;
	
	@Before
	@Override
	public void setUp(){
		sms = new ShortMessageServiceImpl();
		users = new HashSet<User>();
		user1 = new User();
		user2 = new User();
		user3 = new User();
	}
	
	@Test
	public void getUsersTestReturnsCorrectListOfUsers(){
		sms.createUser(USER_NAME, CITY);
		sms.createUser(USER_NAME2, CITY);
		sms.createUser(USER_NAME3, CITY);
		user1.setName(USER_NAME);
		user1.setCity(CITY);
		user2.setName(USER_NAME2);
		user2.setCity(CITY);
		user3.setName(USER_NAME3);
		user3.setCity(CITY);
		users.add(user1);
		users.add(user2);
		users.add(user3);
		assertNotNull(sms.getUsers());
		assertEquals(users, sms.getUsers());
	}
	
	@Test
	public void getUsersTestThereAreNoUsers(){
		assertNotNull(sms.getUsers());
		assertEquals(users, sms.getUsers());
	}
	
	@After
	public void cleanUp(){
		super.tearDown();
	}
}
