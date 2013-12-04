package de.htw_berlin.f4.ai.kbe.kurznachrichten;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CreateUserTest {

	// /////////////////////////////////////////////////////
		// createUserTests
		// /////////////////////////////////////////////////////
	
	private ShortMessageService sms;
	
	@Before
	public void initialize(){
		sms.createUser("user1", "berlin");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void createUserTestNameAlreadyExists(){
		sms.createUser("user1", "irgendwo");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void createUserTestNameTooLong(){
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void createUserTestNameTooShort(){
		
	}
}
