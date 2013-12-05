package de.htw_berlin.f4.ai.kbe.kurznachrichten;

import org.junit.Test;

public class DeleteUserTest {

	// /////////////////////////////////////////////////////
	// deleteUserTests
	// /////////////////////////////////////////////////////
		
	private ShortMessageService sms;
	
	@Test (expected = IllegalArgumentException.class)
	public void deleteUserTestNameDoesNotExist(){
		sms.deleteUser("ThisUserDoesNotExist");
	}
}
