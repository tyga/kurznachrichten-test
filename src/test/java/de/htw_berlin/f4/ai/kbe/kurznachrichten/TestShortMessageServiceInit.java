package de.htw_berlin.f4.ai.kbe.kurznachrichten;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;

public class TestShortMessageServiceInit {

	protected static final String USER_NOT_EXISTING = "dummy_user";
	protected static final String TOPIC_NOT_EXISTING = "dummy_topic";
//	protected static final String 
	
	protected static final String USER_ADMIN = "admin";
	protected static final String USER_CITY = "München";
	protected static final String TOPIC_GLOBAL = "global";
	protected static final String MESSAGE_CONTENT = "message content";
	protected static final String MESSAGE_VALID = "this is a valid message";
	
	protected static String STR_LENGTH_254;
	protected static String STR_LENGTH_255;
	protected static String STR_LENGTH_256;
	protected static String STR_LENGTH_10;
	protected static String STR_LENGTH_9;
	protected static String STR_LENGTH_11;
	
	protected static long userIdCounter;
	
	protected ShortMessageService sms;
	
	protected HashMap<String, List<Message>> topics;
	protected HashMap<String, Long> users;
	
	
	@Before
	public void setUp() {
		//create collections
		topics = new HashMap<String, List<Message>>();
		users = new HashMap<String, Long>();
		
		User user = new User();
		user.setName(USER_ADMIN);
		user.setCity(USER_CITY);
		users.put(USER_ADMIN, ++userIdCounter);
		
		LinkedList<Message> messageList = new LinkedList<Message>();
		topics.put(TOPIC_GLOBAL, messageList);
		
		Message msg = new Message();
		msg.setOrigin(true);
		msg.setContent(MESSAGE_CONTENT);
		msg.setTopic(TOPIC_GLOBAL);
		messageList.add(msg);
	}
	
	@After
	public void tearDown() {
		//delete user
		topics.clear();
		users.clear();
		
		userIdCounter = 0;
		
		topics = null;
		users = null;
	}
	
	@BeforeClass
	public static void setUpClass() {
		STR_LENGTH_256 = stringCreator(256);
		STR_LENGTH_255 = stringCreator(255);
		STR_LENGTH_254 = stringCreator(254);
		STR_LENGTH_11 = stringCreator(11);
		STR_LENGTH_10 = stringCreator(10);
		STR_LENGTH_9 = stringCreator(9);
		
		// verify strings
		assertEquals(256, STR_LENGTH_256.length());
		assertEquals(255, STR_LENGTH_255.length());
		assertEquals(254, STR_LENGTH_254.length());
		assertEquals(11, STR_LENGTH_11.length());
		assertEquals(10, STR_LENGTH_10.length());
		assertEquals(9, STR_LENGTH_9.length());
	}
	
	@AfterClass
	public static void tearDownClass() {
		//TODO tear down class
	}
	
	protected static String stringCreator(String alphabet, int length) {
		StringBuilder sb = new StringBuilder();
		while(sb.length() < length) {
			sb.append(alphabet);
		}
		sb.setLength(length);
		return sb.toString();
	}
	
	protected static String stringCreator(int length) {
		return stringCreator("abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZäüöÄÜÖ-_", length);
	}
}
