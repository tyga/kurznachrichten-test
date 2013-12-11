package de.htw_berlin.f4.ai.kbe.kurznachrichten;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class TestShortMessageServiceInit {

	
	protected static final String MESSAGE_VALID1 = "message content";
	protected static final String MESSAGE_VALID2 = "this is a valid message";

	
	public static String USER_NAME = "kinmin";
	public static String CITY = "honeynut";
	public static String TOPIC = "Kinmins new Smart Home";
	public static String USER_NAME_NOT_EXISTING = "notexists";
	public static String TOPIC_NOT_EXISTING = "This topic is not existing";

	protected static String STR_LENGTH_254;
	protected static String STR_LENGTH_255;
	protected static String STR_LENGTH_256;
	protected static String STR_LENGTH_10;
	protected static String STR_LENGTH_9;
	protected static String STR_LENGTH_11;
	protected static String STR_LENGTH_30;
	protected static String STR_LENGTH_31;
	protected static String STR_LENGTH_4;
	protected static String STR_LENGTH_3;

	protected static ShortMessageService sms;

	@Before
	public void setUp() {
		sms = new ShortMessageServiceImpl();
		sms.createUser(USER_NAME, CITY);
		sms.createTopic(USER_NAME, TOPIC);
	}

	@After
	public void tearDown() {
		sms = null;
	}

	@BeforeClass
	public static void setUpClass() {
		STR_LENGTH_256 = stringCreator(256);
		STR_LENGTH_255 = stringCreator(255);
		STR_LENGTH_254 = stringCreator(254);
		STR_LENGTH_31 = stringCreator(31);
		STR_LENGTH_30 = stringCreator(30);
		STR_LENGTH_11 = stringCreator(11);
		STR_LENGTH_10 = stringCreator(10);
		STR_LENGTH_9 = stringCreator(9);
		STR_LENGTH_4 = stringCreator(4);
		STR_LENGTH_3 = stringCreator(3);

		// verify strings
		assertEquals(256, STR_LENGTH_256.length());
		assertEquals(255, STR_LENGTH_255.length());
		assertEquals(254, STR_LENGTH_254.length());
		assertEquals(31, STR_LENGTH_31.length());
		assertEquals(30, STR_LENGTH_30.length());
		assertEquals(11, STR_LENGTH_11.length());
		assertEquals(10, STR_LENGTH_10.length());
		assertEquals(9, STR_LENGTH_9.length());
		assertEquals(4, STR_LENGTH_4.length());
		assertEquals(3, STR_LENGTH_3.length());
	}

	@AfterClass
	public static void tearDownClass() {
		// TODO tear down class
	}

	protected static String stringCreator(String alphabet, int length) {
		StringBuilder sb = new StringBuilder();
		while (sb.length() < length) {
			sb.append(alphabet);
		}
		sb.setLength(length);
		return sb.toString();
	}

	protected static String stringCreator(int length) {
		return stringCreator(
				"abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZäüöÄÜÖ-_",
				length);
	}
}
