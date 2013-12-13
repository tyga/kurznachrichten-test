package de.htw_berlin.f4.ai.kbe.kurznachrichten;

import static org.junit.Assert.*;
import static de.htw_berlin.f4.ai.kbe.kurznachrichten.TestShortMessageServiceInit.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GetUsersTest {

	// /////////////////////////////////////////////////////
	// getUsersTests
	// /////////////////////////////////////////////////////

	private final Set<User> users = new HashSet<User>();
	private final User user1 = new User();
	private final User user2 = new User();
	private final User user3 = new User();
	
	private ShortMessageService sms;

	@Before
	public void setUp() {
		sms = new ShortMessageServiceImpl();
	}

	@Test
	public void getUsersTestReturnsCorrectListOfUsers() {
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
		// cleanup
		try {
			sms.deleteUser(USER_NAME);
			sms.deleteUser(USER_NAME2);
			sms.deleteUser(USER_NAME3);
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void getUsersTestThereAreNoUsers() {
		Set<User> getted = sms.getUsers();
		assertNotNull(getted);
		assertEquals(0, getted.size());
	}

	@After
	public void cleanUp() {
		users.clear();
	}
}
