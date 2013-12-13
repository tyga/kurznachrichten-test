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
	
	
	private final Set<User> users = new HashSet<User>();
	private final User user1 = new User();
	private final User user2 = new User();
	private final User user3 = new User();
	
	@Before
	@Override
	public void setUp(){
		sms = new ShortMessageServiceImpl();
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
		Set<User> getted = sms.getUsers();
		assertNotNull(getted);
		assertEquals(users, getted);
//		assertTrue(users.containsAll(getted));
//		assertTrue(getted.containsAll(users));
	}
	
	@Test
	public void getUsersTestThereAreNoUsers(){
		Set<User> getted = sms.getUsers();
		assertNotNull(getted);
		assertEquals(0, getted.size());
//		assertEquals(users, sms.getUsers());
	}
	
	@After
	public void cleanUp(){
		users.clear();
		super.tearDown();
	}
}
