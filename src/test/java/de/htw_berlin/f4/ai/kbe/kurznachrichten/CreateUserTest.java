package de.htw_berlin.f4.ai.kbe.kurznachrichten;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CreateUserTest extends TestShortMessageServiceInit {

	// /////////////////////////////////////////////////////
	// createUserTests
	// /////////////////////////////////////////////////////
	
	//private ShortMessageService sms;
	private String user = "user1";
	
	@Before
	public void initialize(){
		sms.createUser(user, "berlin");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void createUserTestNameAlreadyExists(){
		sms.createUser(user, "irgendwo");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void createUserTestNameTooLong(){
		sms.createUser(STR_LENGTH_31, "irgendwo");
	}
	
	@Test
	public void createUserTestNameNotTooLong(){
		sms.createUser(STR_LENGTH_30, "irgendwo");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void createUserTestNameTooShort(){
		sms.createUser(STR_LENGTH_3, "irgendwo");
	}
	
	@Test
	public void createUserTestNameNotTooShort(){
		sms.createUser(STR_LENGTH_4, "irgendwo");
	}
	
	@Test (expected = NullPointerException.class)
	public void createUserTestNameNull(){
		sms.createUser(null, "irgendwo");
	}
	
	@Test (expected = NullPointerException.class)
	public void createUserTestCityNull(){
		sms.createUser(user, null);
	}
	
	@After
	public void cleanUp(){
		sms.deleteUser(user);
	}
}
