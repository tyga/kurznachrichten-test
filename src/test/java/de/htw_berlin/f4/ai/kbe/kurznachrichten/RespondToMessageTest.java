package de.htw_berlin.f4.ai.kbe.kurznachrichten;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class RespondToMessageTest extends TestShortMessageServiceInit {

	public ShortMessageService sms;
	public Long PREDECESSOR;
	
	// /////////////////////////////////////////////////////
	// setup and tear down
	// /////////////////////////////////////////////////////

	@Before
	public void setUp() {
		sms = new ShortMessageServiceImpl();
		sms.createUser(USER_NAME, CITY);
		sms.createTopic(USER_NAME, TOPIC);
		PREDECESSOR = sms.createMessage(USER_NAME, MESSAGE_VALID, TOPIC);
	}
	
	@After
	public void tearDown() {
		sms.deleteUser(USER_NAME);
	}
	
	// /////////////////////////////////////////////////////
	// respondToMessage() Tests
	// /////////////////////////////////////////////////////
	
	@Test
	public void respondToMessageTestValidArguments() {
		Long msgId = sms.respondToMessage(USER_NAME, "Response", PREDECESSOR);
		assertNotNull(msgId);
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
		sms.respondToMessage(USER_NAME_NOT_EXISTING, MESSAGE_VALID, PREDECESSOR);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void respondToMessageTestPredecessorNotExisting() {
		sms.respondToMessage(USER_NAME, MESSAGE_VALID, PREDECESSOR + 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void respondToMessageTestPredecessorNotOrigin() {
		Long preNotOrigin = sms.respondToMessage(USER_NAME, "This is not a origin", PREDECESSOR);
		sms.respondToMessage(USER_NAME, "New message", preNotOrigin);
	}
	
	@Test(expected = NullPointerException.class)
	public void respondToMessageTestUserNameNull() {
		sms.respondToMessage(null, MESSAGE_VALID, PREDECESSOR);
	}
	
	@Test(expected = NullPointerException.class)
	public void respondToMessageTestMessageNull() {
		sms.respondToMessage(USER_NAME, null, PREDECESSOR);
	}
	
	@Test(expected = NullPointerException.class)
	public void respondToMessageTestPredecessorNull() {
		sms.respondToMessage(USER_NAME, MESSAGE_VALID, null);
	}
}
