package de.htw_berlin.f4.ai.kbe.kurznachrichten;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DeleteUserTest extends TestShortMessageServiceInit {

	// /////////////////////////////////////////////////////
	// deleteUserTests
	// /////////////////////////////////////////////////////

	private final Set<User> users = new HashSet<User>();
	private final User user1 = new User();
	private final User user2 = new User();
	private final User user3 = new User();

	@Before
	@Override
	public void setUp() {
		super.setUp();
		// sms.createUser(USER_NAME, CITY); // already created
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
	}

	@Test(expected = IllegalArgumentException.class)
	public void deleteUserTestNameDoesNotExist() {
		sms.deleteUser(USER_NAME_NOT_EXISTING);
	}

	@Test
	public void deleteUserTestAUserIsDeleted() {

		Set<User> us = sms.getUsers();
		assertNotNull(us);
		int before_delete = us.size();
		assertTrue(us.contains(user1)); // contains User.class not String
		sms.deleteUser(USER_NAME);
		// verify delete operation
		us = sms.getUsers();
		assertNotNull(us);
		assertFalse(us.contains(user1)); // contains USer.class not String
		int after_delete = us.size();

		assertEquals(3, before_delete);
		assertEquals(2, after_delete);
	}

	@Test
	public void deleteUserTestCorrectUserIsDeleted() {
		Set<User> getted = sms.getUsers();
		assertNotNull(getted);
		// assertTrue(users.containsAll(getted));
		// assertTrue(getted.containsAll(users));
		assertEquals(users, getted);
		sms.deleteUser(USER_NAME3);
		// verify after delete operation
		users.remove(user3);
		getted = sms.getUsers();
		assertNotNull(getted);
		assertEquals(users, getted);
	}

	@After
	@Override
	public void tearDown() {
		users.clear();
		// sms.deleteUser(USER_NAME);
		sms.deleteUser(USER_NAME2);
		sms.deleteUser(USER_NAME3);

		super.tearDown();
	}
}
