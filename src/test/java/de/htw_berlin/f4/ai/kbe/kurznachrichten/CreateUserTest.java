package de.htw_berlin.f4.ai.kbe.kurznachrichten;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//TODO User (user1) erzeugen mit new
public class CreateUserTest extends TestShortMessageServiceInit {

	// /////////////////////////////////////////////////////
	// createUserTests
	// /////////////////////////////////////////////////////
	
	//private ShortMessageService sms;
	private String USER_NAME = "user1";	//TODO Keine Konstanten (kein final)
	private String USER_CITY = "irgendwo";	//TODO Konstanten in TestShortMessageServiceInit.class
	private User user1;
	
	@Before
	public void initialize(){
		sms.createUser(USER_NAME, USER_CITY);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void createUserTestNameAlreadyExists(){
		sms.createUser(USER_NAME, USER_CITY);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void createUserTestNameTooLong(){
		sms.createUser(STR_LENGTH_31, USER_CITY);
	}
	
	@Test
	public void createUserTestNameNotTooLong(){
		sms.createUser(STR_LENGTH_30, USER_CITY);
		user1.setName(STR_LENGTH_30);
		user1.setCity(USER_CITY);
		Set<User> users = sms.getUsers();
		if(!users.contains(user1)){
			fail("User with a name length of 30 characters should have been created, but wasn't.");
		}
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void createUserTestNameTooShort(){
		sms.createUser(STR_LENGTH_3, USER_CITY);
	}
	
	@Test
	public void createUserTestNameNotTooShort(){
		sms.createUser(STR_LENGTH_4, USER_CITY);
		user1.setName(STR_LENGTH_4);
		user1.setCity(USER_CITY);
		Set<User> users = sms.getUsers();
		if(!users.contains(user1)){
			fail("User with a name length of 4 characters should have been created, but wasn't.");
		}
	}
	
	@Test (expected = NullPointerException.class)
	public void createUserTestNameNull(){
		sms.createUser(null, USER_CITY);
	}
	
	@Test (expected = NullPointerException.class)
	public void createUserTestCityNull(){
		sms.createUser(USER_NAME, null);
	}
	
	@After
	public void cleanUp(){
		sms.deleteUser(USER_NAME);
	}
}
