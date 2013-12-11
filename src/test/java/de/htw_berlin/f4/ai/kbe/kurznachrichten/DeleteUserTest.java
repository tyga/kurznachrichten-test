package de.htw_berlin.f4.ai.kbe.kurznachrichten;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class DeleteUserTest extends TestShortMessageServiceInit {

	// /////////////////////////////////////////////////////
	// deleteUserTests
	// /////////////////////////////////////////////////////
	
	private Set<User> users;
	private User user1;
	private User user2;
	private User user3;
	
	@Before
	@Override
	public void setUp(){
		super.setUp();
		users = new HashSet<User>();
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
	
	@Test (expected = IllegalArgumentException.class)
	public void deleteUserTestNameDoesNotExist(){
		sms.deleteUser(USER_NAME_NOT_EXISTING);
	}
	
	@Test
	public void deleteUserTestAUserIsDeleted(){
		int before_delete;
		int after_delete;
		before_delete = sms.getUsers().size();
		assertTrue(sms.getUsers().contains(USER_NAME));
		sms.deleteUser(USER_NAME);
		assertFalse(sms.getUsers().contains(USER_NAME));
		after_delete = sms.getUsers().size();
		assertEquals(3, before_delete);
		assertEquals(2, after_delete);
		
	}
	
	@Test
	public void deleteUserTestCorrectUserIsDeleted(){
		assertTrue(users.containsAll(sms.getUsers()));
		assertTrue(sms.getUsers().containsAll(users));
		assertEquals(users, sms.getUsers());
		sms.deleteUser(USER_NAME3);
		users.remove(user3);
		assertEquals(users, sms.getUsers());
	}
	
	@Test
	public void cleanUp(){
		sms.deleteUser(USER_NAME);
		sms.deleteUser(USER_NAME2);
		sms.deleteUser(USER_NAME3);
		users.remove(user1);
		users.remove(user2);
		users.remove(user3);
		super.tearDown();
		}
}
