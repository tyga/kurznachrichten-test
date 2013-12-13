package de.htw_berlin.f4.ai.kbe.kurznachrichten;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GetTopicsTest extends TestShortMessageServiceInit {
	
	// /////////////////////////////////////////////////////
	// getTopicsTests
	// /////////////////////////////////////////////////////

	@Before
	@Override
	public void setUp(){
		sms = new ShortMessageServiceImpl();
		sms.createUser(USER_NAME, CITY);
	}
	
	@Test
	public void getTopicsTestCorrectTopics(){
		sms.createTopic(USER_NAME, TOPIC);
		sms.createTopic(USER_NAME, TOPIC2);
		sms.createTopic(USER_NAME, TOPIC3);
		Set<String> topics = new HashSet<String>();
		topics.add(TOPIC);
		topics.add(TOPIC2);
		topics.add(TOPIC3);
		Set<String> getted = sms.getTopics();
		assertNotNull(getted);
//		assertTrue(topics.containsAll(getted));
//		assertTrue(getted.containsAll(topics));
		assertEquals(topics, getted);
	}
	
	@Test
	public void getTopicsTestNoTopicsExist(){
		Set<String> topics = new HashSet<String>();
		Set<String> getted = sms.getTopics();
		assertNotNull(getted);
//		assertTrue(topics.containsAll(getted));
//		assertTrue(getted.containsAll(topics));
		assertEquals(topics, getted);
	}
	
	@After
	public void cleanUp(){
		sms.deleteUser(USER_NAME);
		super.tearDown();
	}
}
