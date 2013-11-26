package de.htw_berlin.f4.ai.kbe.kurznachrichten;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ShortMessageServiceTest {

	private ShortMessageService sms;

	@Before
	public void setUp() {
		sms = new ShortMessageServiceImpl();
	}

	@After
	public void tearDown() {

	}

	// /////////////////////////////////////////////////////
	// createMessageTests
	// /////////////////////////////////////////////////////
	
	@Test 
	public void createMessageTestValidArguments() {
		//sms.createUser("User", "City");
		sms.createMessage("User", randomString(255), "Topic");
		sms.createMessage("User", "Message", "Topic");
	}

	@Test(expected=IllegalArgumentException.class)
	public void createMessageTestTooLong() {
		sms.createMessage("User", randomString(256), "Topic");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void createMessageTestTooShort() {
		sms.createMessage("User", randomString(9), "Topic");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void createMessageTestUserNotExists() {
		fail();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void createMessageTestTopicNotExists() {
		fail();
	}
	
	@Test(expected=NullPointerException.class)
	public void createMessageTestUsernameNull() {
		fail();
	}
	
	@Test(expected=NullPointerException.class)
	public void createMessageTestMessageNull() {
		fail();
	}
	
	@Test(expected=NullPointerException.class)
	public void createMessageTestTopicNull() {
		fail();
	}

	// /////////////////////////////////////////////////////
	// respondToMessageTests
	// /////////////////////////////////////////////////////

	// /////////////////////////////////////////////////////
	// deleteMessageTests
	// /////////////////////////////////////////////////////

	// /////////////////////////////////////////////////////
	// createTopicTests
	// /////////////////////////////////////////////////////

	// /////////////////////////////////////////////////////
	// getTopicsTests
	// /////////////////////////////////////////////////////

	// /////////////////////////////////////////////////////
	// createMessageByTopicTests
	// /////////////////////////////////////////////////////

	// /////////////////////////////////////////////////////
	// createUserTests
	// /////////////////////////////////////////////////////

	public static String randomString(int length, String alphabet) {
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(alphabet.charAt(rand.nextInt(alphabet.length())));
		}
		return sb.toString();
	}
	
	public static String randomString(int length) {
		String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_ ";
		return randomString(alphabet.length(), alphabet);
	}
	
	@BeforeClass
	public static void setUpClass() {

	}

	@AfterClass
	public static void tearDownClass() {

	}
}
