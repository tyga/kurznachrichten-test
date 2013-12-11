package de.htw_berlin.f4.ai.kbe.kurznachrichten;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.List;

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

		Calendar c = Calendar.getInstance();
		Long id1 = sms.createMessage(USER_NAME, STR_LENGTH_255, TOPIC);
		Long id2 = sms.createMessage(USER_NAME, MESSAGE_VALID2, TOPIC);
		Long id3 = sms.createMessage(USER_NAME, STR_LENGTH_10, TOPIC);

		assertNotNull(id1);
		assertNotNull(id2);
		assertNotNull(id3);
		
		List<List<Message>> msgs = sms.getMessageByTopic(TOPIC, c.getTime());
		assertEquals(3, msgs.size());
//		assertEquals(STR_LENGTH_255, msgs.get(0).get(0).getContent());
//		assertEquals(MESSAGE_VALID2, msgs.get(1).get(0).getContent());
//		assertEquals(STR_LENGTH_10, msgs.get(2).get(0).getContent());
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
