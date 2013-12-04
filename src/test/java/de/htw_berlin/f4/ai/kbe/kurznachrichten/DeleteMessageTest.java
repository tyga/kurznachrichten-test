package de.htw_berlin.f4.ai.kbe.kurznachrichten;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeleteMessageTest extends TestShortMessageServiceInit {

//	public ShortMessageService sms;
	public Long PREDECESSOR;

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
		PREDECESSOR = sms.createMessage(USER_NAME, MESSAGE_VALID2, TOPIC);
	}

	@After
	@Override
	public void tearDown() {
		super.tearDown();
	}

	// /////////////////////////////////////////////////////
	// deleteMessage Tests
	// /////////////////////////////////////////////////////

	@Test
	public void deleteMessageTestValidArguments() throws AuthorizationException {
		sms.respondToMessage(USER_NAME, "This is a response...", PREDECESSOR);
		sms.deleteMessage(USER_NAME, PREDECESSOR);
		//TODO verify that there are no messages anymore
	}

	@Test(expected = IllegalArgumentException.class)
	public void deleteMessageTestMessageNotExisting()
			throws AuthorizationException {
		sms.deleteMessage(USER_NAME, PREDECESSOR + 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void deleteMessageTestIsNoOrigin()
			throws AuthorizationException {
		Long preNoOrigin = sms.respondToMessage(USER_NAME, MESSAGE_VALID2, PREDECESSOR);
		sms.deleteMessage(USER_NAME, preNoOrigin);
	}

	@Test(expected = IllegalArgumentException.class)
	public void deleteMessageTestUserNotExisting()
			throws AuthorizationException {
		sms.deleteMessage(USER_NAME_NOT_EXISTING, PREDECESSOR);
	}

	@Test(expected = NullPointerException.class)
	public void deleteMessageTestUserNameNull() throws AuthorizationException {
		sms.deleteMessage(null, PREDECESSOR);
	}

	@Test(expected = NullPointerException.class)
	public void deleteMessageTestMessageIdNull() throws AuthorizationException {
		sms.deleteMessage(USER_NAME, null);
	}

	@Test(expected = AuthorizationException.class)
	public void deleteMessageTestUserNotAuthorized()
			throws AuthorizationException {
		String newUser = "NEW_USER";
		sms.createUser(newUser, "NewCity");
		Long newMessId = sms.createMessage(newUser, "This is another message", TOPIC);
		//verify 
		assertNotEquals(newMessId, PREDECESSOR);
		sms.deleteMessage(newUser, PREDECESSOR);
	}

}
