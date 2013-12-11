package de.htw_berlin.f4.ai.kbe.kurznachrichten;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class TestShortMessageServiceInit {

	
	public static final String MESSAGE_VALID1 = "message content";
	public static final String MESSAGE_VALID2 = "this is a valid message";
	public static final String MESSAGE_VALID3 = "This is another valid message";
	public static final String MESSAGE_VALID4 = "This is also a valid message";

	
	public static final String USER_NAME = "kinmin";
	public static final String USER_NAME2 = "user2";
	public static final String USER_NAME3 = "user3";
	public static final String CITY = "honeynut";
	public static final String TOPIC = "Kinmins new Smart Home";
	public static final String TOPIC2 = "topic2";
	public static final String TOPIC3 = "topic3";
	public static final String USER_NAME_NOT_EXISTING = "notexists";
	public static final String TOPIC_NOT_EXISTING = "This topic is not existing";

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
	public static final String STR_LENGTH_71 = "THIS TOPIC IS TOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO LONG";
	public static final String STR_LENGTH_70 = "THIS TOPIC IS SOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO LONG";
	public static final String STR_LENGTH_2 = "TH";
	public static final String STR_LENGTH_1 = "T";

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
		
		String err_msg = "SETUP_ERROR__ Invalid string length!";

		// verify strings
		assertEquals(err_msg, 256, STR_LENGTH_256.length());
		assertEquals(err_msg, 255, STR_LENGTH_255.length());
		assertEquals(err_msg, 254, STR_LENGTH_254.length());
		assertEquals(err_msg, 71, STR_LENGTH_71.length());
		assertEquals(err_msg, 70, STR_LENGTH_70.length());
		assertEquals(err_msg, 31, STR_LENGTH_31.length());
		assertEquals(err_msg, 30, STR_LENGTH_30.length());
		assertEquals(err_msg, 11, STR_LENGTH_11.length());
		assertEquals(err_msg, 10, STR_LENGTH_10.length());
		assertEquals(err_msg, 9, STR_LENGTH_9.length());
		assertEquals(err_msg, 4, STR_LENGTH_4.length());
		assertEquals(err_msg, 3, STR_LENGTH_3.length());
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
