package de.htw_berlin.f4.ai.kbe.kurznachrichten;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RespondToMessageTest extends TestShortMessageServiceInit {

	private Long PREDECESSOR;
	private Calendar date;

	// /////////////////////////////////////////////////////
	// setup and tear down
	// /////////////////////////////////////////////////////

	@Before
	@Override
	public void setUp() {
		super.setUp();
		date = Calendar.getInstance();
		PREDECESSOR = sms.createMessage(USER_NAME, MESSAGE_VALID2, TOPIC);
	}

	@After
	@Override
	public void tearDown() {
		super.tearDown();
	}

	// /////////////////////////////////////////////////////
	// respondToMessage() Tests
	// /////////////////////////////////////////////////////

	@Test
	public void respondToMessageTestValidArguments() {
		Long msgId1 = sms.respondToMessage(USER_NAME, "Response", PREDECESSOR);
		Long msgId2 = sms.respondToMessage(USER_NAME, STR_LENGTH_255,
				PREDECESSOR);
		Long msgId3 = sms.respondToMessage(USER_NAME, STR_LENGTH_10,
				PREDECESSOR);
		assertNotNull(msgId1);
		assertNotNull(msgId2);
		assertNotNull(msgId3);

		List<List<Message>> msgs = sms.getMessageByTopic(TOPIC, date.getTime());
		for (Message msg : msgs.get(0)) {
			assertEquals(TOPIC, msg.getTopic());
		}

	}

	@Test(expected = IllegalArgumentException.class)
	public void respondToMessageTestTooLong() {
		sms.respondToMessage(USER_NAME, STR_LENGTH_256, PREDECESSOR);
	}

	@Test(expected = IllegalArgumentException.class)
	public void respondToMessageTestTooShort() {
		sms.respondToMessage(USER_NAME, STR_LENGTH_9, PREDECESSOR);
	}

	@Test(expected = IllegalArgumentException.class)
	public void respondToMessageTestUserNotExisting() {
		sms.respondToMessage(USER_NAME_NOT_EXISTING, MESSAGE_VALID2,
				PREDECESSOR);
	}

	@Test(expected = IllegalArgumentException.class)
	public void respondToMessageTestPredecessorNotExisting() {
		sms.respondToMessage(USER_NAME, MESSAGE_VALID2, PREDECESSOR + 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void respondToMessageTestPredecessorNotOrigin() {
		Long preNotOrigin = sms.respondToMessage(USER_NAME,
				"This is not a origin", PREDECESSOR);
		sms.respondToMessage(USER_NAME, "New message", preNotOrigin);
	}

	@Test(expected = NullPointerException.class)
	public void respondToMessageTestUserNameNull() {
		sms.respondToMessage(null, MESSAGE_VALID2, PREDECESSOR);
	}

	@Test(expected = NullPointerException.class)
	public void respondToMessageTestMessageNull() {
		sms.respondToMessage(USER_NAME, null, PREDECESSOR);
	}

	@Test(expected = NullPointerException.class)
	public void respondToMessageTestPredecessorNull() {
		sms.respondToMessage(USER_NAME, MESSAGE_VALID2, null);
	}
}
