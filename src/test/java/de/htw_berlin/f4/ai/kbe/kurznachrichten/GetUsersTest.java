package de.htw_berlin.f4.ai.kbe.kurznachrichten;

import static org.junit.Assert.*;

import org.junit.Test;

public class GetUsersTest {

	// /////////////////////////////////////////////////////
	// getUserTests
	// /////////////////////////////////////////////////////
		
	private ShortMessageService sms;
	private String city = "irgendwo";
	
	@Test
	public void getUsersTestReturnsCorrectListOfUsers(){
		sms.createUser("user1", city);
		sms.createUser("user2", city);
		sms.createUser("user3", city);
		assertNotNull(sms.getUsers());
		//to do:	Set<user> mit den usern, die created wurden erzeugen
		//			assertEquals(Set<>, sms.getUsers());
		//problem:	kp, wie man für die User name und city setted
	}
}
