package de.htw_berlin.f4.ai.kbe.kurznachrichten;



import java.util.Set;

import org.junit.After;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CreateTopicTest extends TestShortMessageServiceInit {

	//TODO move to TestShortMessageServiceInit or constants
	public static final String STR_LENGTH_71 = "THIS TOPIC IS TOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO LONG";
	public static final String STR_LENGTH_1 = "T";
	
//	public ShortMessageService sms;

	// /////////////////////////////////////////////////////
	// setup and tear down
	// /////////////////////////////////////////////////////
	
	@BeforeClass
	public static void setUpClass() {
		//verify length
		assertEquals(71, STR_LENGTH_71.length());
		assertEquals(1, STR_LENGTH_1);
	}

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
	// createTopic Tests
	// /////////////////////////////////////////////////////

	@Test
	public void createTopicTestValidArguments() {
		sms.createTopic(USER_NAME, TOPIC_NOT_EXISTING);
		//verify that topic exists
		Set<String> topics = sms.getTopics();
		boolean created = false;
		for (String item : topics) {
			if(item.equals(TOPIC_NOT_EXISTING))
				created = true;
		}
		assertTrue("Topic was not found!", created);
	}

	@Test(expected = IllegalArgumentException.class)
	public void createTopicTestUserNotExisting() {
		sms.createTopic(USER_NAME_NOT_EXISTING, TOPIC_NOT_EXISTING);
	}

	@Test(expected = IllegalArgumentException.class)
	public void createTopicTestTopicAlreadyExisting() {
		sms.createTopic(USER_NAME, TOPIC);
	}

	@Test(expected = IllegalArgumentException.class)
	public void createTopicTestTooShort() {
		sms.createTopic(USER_NAME, STR_LENGTH_1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void createTopicTestTooLong() {
		sms.createTopic(USER_NAME, STR_LENGTH_71);
	}

	@Test(expected = NullPointerException.class)
	public void createTopicTestUserNameNull() {
		sms.createTopic(null, TOPIC_NOT_EXISTING);
	}

	@Test(expected = NullPointerException.class)
	public void createTopicTestTopicNull() {
		sms.createTopic(USER_NAME, null);
	}
}
