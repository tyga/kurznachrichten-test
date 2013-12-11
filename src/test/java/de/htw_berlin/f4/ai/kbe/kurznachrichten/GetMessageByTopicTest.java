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
	
	@Before
	@Override
	public void setUp(){
		super.setUp();
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, 2013);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 10);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		d = c.getTime();
		sms.createTopic(USER_NAME, TOPIC2);
		sms.createMessage(USER_NAME, MESSAGE_VALID1, TOPIC);
		try {
			Thread.sleep(2);
		} catch (InterruptedException e) {
			fail(e.getMessage());
		}
		sms.createMessage(USER_NAME, MESSAGE_VALID2, TOPIC);
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
		List<String> message1 = new ArrayList<String>();
		List<String> message2 = new ArrayList<String>();
		List<String> message3 = new ArrayList<String>();
		List<List<String>> threads1 = new ArrayList<List<String>>();//wird threads zum topic1 enthalten
		List<List<String>> threads2 = new ArrayList<List<String>>();//wird threads zum topic2 enthalten
		message1.add(MESSAGE_VALID1);
		message2.add(MESSAGE_VALID2);
		message3.add(MESSAGE_VALID3);
		threads1.add(message1);
		threads1.add(message2);
		threads2.add(message3);
		assertNotNull(sms.getMessageByTopic(TOPIC, d));
		assertEquals(threads1, sms.getMessageByTopic(TOPIC, d));
		assertNotNull(sms.getMessageByTopic(TOPIC2, d));
		assertEquals(threads1, sms.getMessageByTopic(TOPIC2, d));
	}
	
	@Test
	public void getMessageByTopicTestDateArgumentNull(){
		List<String> message1 = new ArrayList<String>();
		List<String> message2 = new ArrayList<String>();
		List<List<String>> threads1 = new ArrayList<List<String>>();
		message1.add(MESSAGE_VALID1);
		message2.add(MESSAGE_VALID2);
		threads1.add(message1);
		threads1.add(message2);
		assertNotNull(sms.getMessageByTopic(TOPIC2, null));
		assertEquals(threads1, sms.getMessageByTopic(TOPIC, null));
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
		List<String> message4 = new ArrayList<String>();
		List<List<String>> threads1 = new ArrayList<List<String>>();
		message4.add(MESSAGE_VALID4);
		threads1.add(message4);
		assertNotNull(sms.getMessageByTopic(TOPIC, now));
		assertEquals(threads1, sms.getMessageByTopic(TOPIC, now));
	}
	
	@Test
	public void getMessageByTopicTestOrderedByDate(){
		try {
			Thread.sleep(2);
		} catch (InterruptedException e) {
			fail(e.getMessage());
		}
		sms.createMessage(USER_NAME, MESSAGE_VALID4, TOPIC);
		List<String> message1 = new ArrayList<String>();
		List<String> message2 = new ArrayList<String>();
		List<String> message4 = new ArrayList<String>();
		message1.add(MESSAGE_VALID1);
		message2.add(MESSAGE_VALID2);
		message4.add(MESSAGE_VALID4);
		List<List<String>> threads1 = new ArrayList<List<String>>();
		threads1.add(message1);
		threads1.add(message2);
		threads1.add(message4);
		assertNotNull(sms.getMessageByTopic(TOPIC, d));
		assertEquals(threads1, sms.getMessageByTopic(TOPIC, d));
	}
	
	@Test
	public void getMessageByTopicTestNoMessages(){
		sms.createTopic(USER_NAME, THIS_TOPIC_DOES_NOT_HAVE_ANY_MESSAGES);
		List<List<String>> threads1 = new ArrayList<List<String>>();
		assertNotNull(sms.getMessageByTopic(THIS_TOPIC_DOES_NOT_HAVE_ANY_MESSAGES, null));
		assertEquals(threads1, sms.getMessageByTopic(THIS_TOPIC_DOES_NOT_HAVE_ANY_MESSAGES, null));
	}
	
	@After
	public void cleanUp(){
		sms.deleteUser(USER_NAME);
		super.tearDown();
	}
}
