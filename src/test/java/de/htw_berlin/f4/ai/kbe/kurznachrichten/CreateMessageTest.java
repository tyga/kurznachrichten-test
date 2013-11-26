package de.htw_berlin.f4.ai.kbe.kurznachrichten;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class CreateMessageTest extends TestShortMessageServiceInit {

	// /////////////////////////////////////////////////////
	// createMessageTests
	// /////////////////////////////////////////////////////

	@Test
	public void createMessageTestValidArguments() {
		//creating user before
		Long id1 = sms.createMessage(USER_ADMIN, STR_LENGTH_255, TOPIC_GLOBAL);
		Long id2 = sms.createMessage(USER_ADMIN, MESSAGE_VALID, TOPIC_GLOBAL);
		assertNotNull(id1);
		assertNotNull(id2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void createMessageTestTooLong() {
		sms.createMessage(USER_ADMIN, STR_LENGTH_256, TOPIC_GLOBAL);
	}

	@Test(expected = IllegalArgumentException.class)
	public void createMessageTestTooShort() {
		sms.createMessage(USER_ADMIN, STR_LENGTH_9, TOPIC_GLOBAL);
	}

	@Test(expected = IllegalArgumentException.class)
	public void createMessageTestUserNotExists() {
		sms.createMessage(USER_NOT_EXISTING, STR_LENGTH_254, TOPIC_GLOBAL);
	}

	@Test(expected = IllegalArgumentException.class)
	public void createMessageTestTopicNotExists() {
		sms.createMessage(USER_ADMIN, STR_LENGTH_254, TOPIC_NOT_EXISTING);
	}

	@Test(expected = NullPointerException.class)
	public void createMessageTestUsernameNull() {
		sms.createMessage(null, MESSAGE_CONTENT, TOPIC_GLOBAL);
	}

	@Test(expected = NullPointerException.class)
	public void createMessageTestMessageNull() {
		sms.createMessage(USER_ADMIN, null, TOPIC_GLOBAL);
	}

	@Test(expected = NullPointerException.class)
	public void createMessageTestTopicNull() {
		sms.createMessage(USER_ADMIN, MESSAGE_CONTENT, null);
	}
}
