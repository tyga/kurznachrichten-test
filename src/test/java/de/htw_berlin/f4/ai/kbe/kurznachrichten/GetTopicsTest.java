package de.htw_berlin.f4.ai.kbe.kurznachrichten;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GetTopicsTest {
	
	// /////////////////////////////////////////////////////
	// getTopicsTests
	// /////////////////////////////////////////////////////
	
	private ShortMessageService sms;
	
	@Before
	public void initialize(){
		sms = new ShortMessageServiceImpl();
		sms.createUser("user1", "berlin");
	}
	
	@Test
	public void getTopicsTestCorrectTopics(){
		sms.createTopic("user1", "topic1");
		sms.createTopic("user1", "topic2");
		sms.createTopic("user1", "topic3");
		Set<String> topics = new HashSet<String>();
		topics.add("topic1");
		topics.add("topic2");
		topics.add("topic3");
		assertNotNull(sms.getTopics());
		assertEquals(topics, sms.getTopics());
	}
	
	@Test
	public void getTopicsTestNoTopicsExist(){
		Set<String> topics = new HashSet<String>();
		assertNotNull(sms.getTopics());
		assertEquals(topics, sms.getTopics());
	}
	
	@After
	public void cleanUp(){
		sms.deleteUser("user1");
	}
}
