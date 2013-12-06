package de.htw_berlin.f4.ai.kbe.kurznachrichten;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GetMessageByTopicTest {

	// /////////////////////////////////////////////////////
	// getMessageByTopicTests
	// /////////////////////////////////////////////////////
	
	private ShortMessageService sms;
	private Date d;
	
	@Before
	public void initialize(){
	Calendar c = Calendar.getInstance();
	  c.set(Calendar.YEAR, 2013);
	  c.set(Calendar.MONTH, Calendar.JANUARY);
	  c.set(Calendar.DAY_OF_MONTH, 1);
	  c.set(Calendar.HOUR_OF_DAY, 10);
	  c.set(Calendar.MINUTE, 0);
	  c.set(Calendar.SECOND, 0);
	  c.set(Calendar.MILLISECOND, 0);
	  d = c.getTime();
	  sms.createUser("user1", "berlin");
	  sms.createTopic("user1", "topic1");
	  sms.createTopic("user1", "topic2");
	  sms.createTopic("user1", "thisTopicDoesNotHaveAnyMessages");
	  sms.createMessage("user1", "message1", "topic1");
	  try {
			Thread.sleep(2);
		} catch (InterruptedException e) {
			fail(e.getMessage());
		}
	  sms.createMessage("user1", "message2", "topic1");
	  sms.createMessage("user1", "message3", "topic2");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void getMessageByTopicTestTopicDoesNotExist(){
		sms.getMessageByTopic("thisTopicDoesntExist", d);
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
		message1.add("message1");
		message2.add("message2");
		message3.add("message3");
		threads1.add(message1);
		threads1.add(message2);
		threads2.add(message3);
		assertNotNull(sms.getMessageByTopic("topic1", d));
		assertEquals(threads1, sms.getMessageByTopic("topic1", d));
		assertNotNull(sms.getMessageByTopic("topic2", d));
		assertEquals(threads1, sms.getMessageByTopic("topic2", d));
	}
	
	@Test
	public void getMessageByTopicTestDateArgumentNull(){
		List<String> message1 = new ArrayList<String>();
		List<String> message2 = new ArrayList<String>();
		List<List<String>> threads1 = new ArrayList<List<String>>();
		message1.add("message1");
		message2.add("message2");
		threads1.add(message1);
		threads1.add(message2);
		assertNotNull(sms.getMessageByTopic("topic2", null));
		assertEquals(threads1, sms.getMessageByTopic("topic1", null));
	}
	
	@Test
	public void getMessageByTopicTestDateTest(){
		Date now = new Date(System.currentTimeMillis());
		try {
			Thread.sleep(2);
		} catch (InterruptedException e) {
			fail(e.getMessage());
		}
		sms.createMessage("user1", "message4", "topic1");
		List<String> message4 = new ArrayList<String>();
		List<List<String>> threads1 = new ArrayList<List<String>>();
		message4.add("message4");
		threads1.add(message4);
		assertNotNull(sms.getMessageByTopic("topic1", now));
		assertEquals(threads1, sms.getMessageByTopic("topic1", now));
	}
	
	@Test
	public void getMessageByTopicTestOrderedByDate(){
		try {
			Thread.sleep(2);
		} catch (InterruptedException e) {
			fail(e.getMessage());
		}
		sms.createMessage("user1", "message4", "topic1");
		List<String> message1 = new ArrayList<String>();
		List<String> message2 = new ArrayList<String>();
		List<String> message4 = new ArrayList<String>();
		message1.add("message1");
		message2.add("message2");
		message4.add("message4");
		List<List<String>> threads1 = new ArrayList<List<String>>();
		threads1.add(message1);
		threads1.add(message2);
		threads1.add(message4);
		assertNotNull(sms.getMessageByTopic("topic1", d));
		assertEquals(threads1, sms.getMessageByTopic("topic1", d));
	}
	
	@Test
	public void getMessageByTopicTestNoMessages(){
		List<List<String>> threads1 = new ArrayList<List<String>>();
		assertNotNull(sms.getMessageByTopic("thisTopicDoesNotHaveAnyMessages", d));
		assertEquals(threads1, sms.getMessageByTopic("thisTopicDoesNotHaveAnyMessages", d));
	}
	
	@After
	public void cleanUp(){
		sms.deleteUser("user1");
	}
}
