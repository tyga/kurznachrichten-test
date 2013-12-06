package de.htw_berlin.f4.ai.kbe.kurznachrichten;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CreateMessageTest extends TestShortMessageServiceInit {

	// /////////////////////////////////////////////////////
	// setup and tear down
	// /////////////////////////////////////////////////////

	@Before
	@Override
	public void setUp() {
		super.setUp();

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
		// message mit der Länge 10 (untere Grenze) könnte noch überprüft werden
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
