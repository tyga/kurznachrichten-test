package de.htw_berlin.f4.ai.kbe.kurznachrichten;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class GetUsersTest {

	// /////////////////////////////////////////////////////
	// getUsersTests
	// /////////////////////////////////////////////////////
		
	private ShortMessageService sms;
	private String city = "irgendwo";
	//TODO user mit new erzeugen!
	private User user1;
	private User user2;
	private User user3;
	
	@Test
	public void getUsersTestReturnsCorrectListOfUsers(){
		sms.createUser("user1", city);
		sms.createUser("user2", city);
		sms.createUser("user3", city);
		user1.setName("user1");
		user1.setCity(city);
		user2.setName("user2");
		user2.setCity(city);
		user3.setName("user3");
		user3.setCity(city);
		Set<User> users = new HashSet<User>();
		users.add(user1);
		users.add(user2);
		users.add(user3);
		assertNotNull(sms.getUsers());
		assertEquals(users, sms.getUsers());
	}
	
	@Test
	public void getUsersTestThereAreNoUsers(){
		Set<User> users = new HashSet<User>();
		assertNotNull(sms.getUsers());
		assertEquals(users, sms.getUsers());
	}
}
