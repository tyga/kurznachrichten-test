package de.htw_berlin.f4.ai.kbe.kurznachrichten;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CreateMessageTest extends TestShortMessageServiceInit {

//	public static String USER_NAME = "kinmin";
//	public static String CITY = "honeynut";
//	public static String TOPIC = "Kinmins new Smart Home";
//	public static String USER_NAME_NOT_EXISTING = "notexists";
//	public static String TOPIC_NOT_EXISTING = "This topic is not existing";
	
//	private ShortMessageService sms;

	// /////////////////////////////////////////////////////
	// setup and tear down
	// /////////////////////////////////////////////////////

	@Before
	@Override
	public void setUp() {
		super.setUp();
//		sms = new ShortMessageServiceImpl();
//		sms.createUser(USER_NAME, CITY);
//		sms.createTopic(USER_NAME, TOPIC);
	}

	@After
	@Override
	public void tearDown() {
		super.tearDown();
	}

	// /////////////////////////////////////////////////////
	// createMessageTests
	// /////////////////////////////////////////////////////

	@Test
	public void createMessageTestValidArguments() {
		// creating user before
		Long id1 = sms.createMessage(USER_NAME, STR_LENGTH_255, TOPIC);
		Long id2 = sms.createMessage(USER_NAME, MESSAGE_VALID2, TOPIC);
		assertNotNull(id1);
		assertNotNull(id2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void createMessageTestTooLong() {
		sms.createMessage(USER_NAME, STR_LENGTH_256, TOPIC);
	}

	@Test(expected = IllegalArgumentException.class)
	public void createMessageTestTooShort() {
		sms.createMessage(USER_NAME, STR_LENGTH_9, TOPIC);
	}

	@Test(expected = IllegalArgumentException.class)
	public void createMessageTestUserNotExists() {
		sms.createMessage(USER_NAME_NOT_EXISTING, STR_LENGTH_254, TOPIC);
	}

	@Test(expected = IllegalArgumentException.class)
	public void createMessageTestTopicNotExists() {
		sms.createMessage(USER_NAME, STR_LENGTH_254, TOPIC_NOT_EXISTING);
	}

	@Test(expected = NullPointerException.class)
	public void createMessageTestUsernameNull() {
		sms.createMessage(null, MESSAGE_VALID1, TOPIC);
	}

	@Test(expected = NullPointerException.class)
	public void createMessageTestMessageNull() {
		sms.createMessage(USER_NAME, null, TOPIC);
	}

	@Test(expected = NullPointerException.class)
	public void createMessageTestTopicNull() {
		sms.createMessage(USER_NAME, MESSAGE_VALID1, null);
	}
}
