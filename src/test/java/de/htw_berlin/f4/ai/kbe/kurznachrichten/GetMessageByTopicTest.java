package de.htw_berlin.f4.ai.kbe.kurznachrichten;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GetMessageByTopicTest extends TestShortMessageServiceInit {

	// /////////////////////////////////////////////////////
	// getMessageByTopicTests
	// /////////////////////////////////////////////////////
	
	private static final String THIS_TOPIC_DOES_NOT_HAVE_ANY_MESSAGES = "thisTopicDoesNotHaveAnyMessages";
	private Date d;
	private Long messageOrigin1Id;
	private Long messageOrigin2Id;
	
	@Before
	@Override
	public void setUp(){
		super.setUp();
//		Calendar c = Calendar.getInstance();
//		c.set(Calendar.YEAR, 2013);
//		c.set(Calendar.MONTH, Calendar.JANUARY);
//		c.set(Calendar.DAY_OF_MONTH, 1);
//		c.set(Calendar.HOUR_OF_DAY, 10);
//		c.set(Calendar.MINUTE, 0);
//		c.set(Calendar.SECOND, 0);
//		c.set(Calendar.MILLISECOND, 0);
		d = Calendar.getInstance().getTime();
		try {
			Thread.sleep(2);
		} catch (InterruptedException e) {
			fail(e.getMessage());
		}
		sms.createTopic(USER_NAME, TOPIC2);
		messageOrigin1Id = sms.createMessage(USER_NAME, MESSAGE_VALID1, TOPIC);
		sms.respondToMessage(USER_NAME, "This is an answer to a message", messageOrigin1Id);
		messageOrigin2Id = sms.createMessage(USER_NAME, MESSAGE_VALID2, TOPIC);
		sms.createMessage(USER_NAME, MESSAGE_VALID3, TOPIC2);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void getMessageByTopicTestTopicDoesNotExist(){
		sms.getMessageByTopic(TOPIC_NOT_EXISTING, d);
	}
	
	@Test (expected = NullPointerException.class)
	public void getMessageByTopicTestTopicArgumentNull(){
		sms.getMessageByTopic(null, d);
	}
	
	@Test
	public void getMessageByTopicTestMessageFitsTopic(){
		List<List<Message>> msgT = sms.getMessageByTopic(TOPIC, d);
		assertNotNull(msgT);
		assertNotNull(msgT.get(0));
		assertNotNull(msgT.get(0).get(0));
		assertEquals(TOPIC, msgT.get(0).get(0).getTopic());
		assertNotNull(msgT.get(0).get(1));
		assertEquals(TOPIC, msgT.get(0).get(1));
		assertNotNull(msgT.get(1));
		assertNotNull(msgT.get(1).get(0));
		assertEquals(TOPIC, msgT.get(1).get(0).getTopic());
		List<List<Message>> msgT2 = sms.getMessageByTopic(TOPIC2, d);
		assertNotNull(msgT2);
		assertNotNull(msgT2.get(0));
		assertNotNull(msgT2.get(0).get(0));
		assertEquals(TOPIC2, msgT2.get(0).get(0).getTopic());
	}
	
	@Test
	public void getMessageByTopicTestCorrectNumberOfMessages(){
		List<List<Message>> msgT = sms.getMessageByTopic(TOPIC, d);
		List<List<Message>> msgT2 = sms.getMessageByTopic(TOPIC2, d);
		assertNotNull(msgT);
		assertNotNull(msgT2);
		assertEquals(2, msgT.size()); //es gibt 2 threads mit origin-message zum topic1
		assertEquals(1, msgT2.size()); //es gibt 1 thread mit origin-message zum topic2
		assertNotNull(msgT.get(0));
		assertNotNull(msgT.get(1));
		assertEquals(2, msgT.get(0).size()); //es gibt 1 antwort im 1. thread zum topic1
		assertEquals(1, msgT.get(1).size()); //es gibt keine antwort im 2. thread zum topic1
		assertNotNull(msgT2.get(0));
		assertEquals(1, msgT2.get(0).size()); //es gibt keine antwort im 1.thread zum topic2
		assertNotNull(msgT.get(0).get(0));
		assertNotNull(msgT.get(0).get(1));
		assertNotNull(msgT.get(1).get(0));
		assertNotNull(msgT2.get(0).get(0));
	}
	
	@Test
	public void getMessageByTopicTestDateNull(){
		List<List<Message>> msgT = sms.getMessageByTopic(TOPIC, null);
		List<List<Message>> msgT2 = sms.getMessageByTopic(TOPIC2, null);
		assertNotNull(msgT);
		assertNotNull(msgT2);
		assertEquals(2, msgT.size()); //es gibt 2 threads mit origin-message zum topic1
		assertEquals(1, msgT2.size()); //es gibt 1 thread mit origin-message zum topic2
		assertNotNull(msgT.get(0));
		assertNotNull(msgT.get(1));
		assertEquals(2, msgT.get(0).size()); //es gibt 1 antwort im 1. thread zum topic1
		assertEquals(1, msgT.get(1).size()); //es gibt keine antwort im 2. thread zum topic1
		assertNotNull(msgT2.get(0));
		assertEquals(1, msgT2.get(0).size()); //es gibt keine antwort im 1.thread zum topic2
		assertNotNull(msgT.get(0).get(0));
		assertNotNull(msgT.get(0).get(1));
		assertNotNull(msgT.get(1).get(0));
		assertNotNull(msgT2.get(0).get(0));
	}
	
	@Test
	public void getMessageByTopicTestDateTest(){
		Date now = Calendar.getInstance().getTime();
		try {
			Thread.sleep(2);
		} catch (InterruptedException e) {
			fail(e.getMessage());
		}
		sms.createMessage(USER_NAME, MESSAGE_VALID4, TOPIC);
		List<List<Message>> msgT = sms.getMessageByTopic(TOPIC, now);
		assertNotNull(msgT);
		assertEquals(1, msgT.size()); //vor der zeit "now" gibt es noch 2 weitere threads zum topic1
	}
	
	@Test
	public void getMessageByTopicTestOrderedByDate(){
		try {
			Thread.sleep(2);
		} catch (InterruptedException e) {
			fail(e.getMessage());
		}
		Long messageOrigin4Id = sms.createMessage(USER_NAME, MESSAGE_VALID4, TOPIC);
		List<List<Message>> msgT = sms.getMessageByTopic(TOPIC, d);
		assertNotNull(msgT);
		assertNotNull(msgT.get(0));
		assertNotNull(msgT.get(1));
		assertNotNull(msgT.get(2));
		assertEquals(messageOrigin1Id, msgT.get(0));
		assertEquals(messageOrigin2Id, msgT.get(1));
		assertEquals(messageOrigin4Id, msgT.get(2));
	}
	
	@Test
	public void getMessageByTopicTestNoMessages(){
		sms.createTopic(USER_NAME, THIS_TOPIC_DOES_NOT_HAVE_ANY_MESSAGES);
		List<List<Message>> msgT = sms.getMessageByTopic(THIS_TOPIC_DOES_NOT_HAVE_ANY_MESSAGES, null);
		assertNotNull(msgT);
		assertEquals(0, msgT.size());
	}
	
	@After
	public void cleanUp(){
		sms.deleteUser(USER_NAME);
		super.tearDown();
	}
}
