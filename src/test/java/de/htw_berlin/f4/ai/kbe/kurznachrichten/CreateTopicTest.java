package de.htw_berlin.f4.ai.kbe.kurznachrichten;



import java.util.Set;

import org.junit.After;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CreateTopicTest extends TestShortMessageServiceInit {

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
	// createTopic Tests
	// /////////////////////////////////////////////////////

	@Test
	public void createTopicTestValidArguments() {
		sms.createTopic(USER_NAME, TOPIC_NOT_EXISTING);
		sms.createTopic(USER_NAME, STR_LENGTH_70);
		sms.createTopic(USER_NAME, STR_LENGTH_2);

		//verify that topic exists
		Set<String> topics = sms.getTopics();
		assertNotNull(topics);
		assertTrue(topics.contains(TOPIC_NOT_EXISTING));
		assertTrue(topics.contains(STR_LENGTH_70));
		assertTrue(topics.contains(STR_LENGTH_2));
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
