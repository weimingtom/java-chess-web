package com.brasee.games.lobby;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LobbyChatManagerTest {
	
	private static int MESSAGE_ARRAY_SIZE = 100;
	private static int MAX_MESSAGES_RETURNED = 100;
	
	private LobbyChatManager manager;
	
	@Before
	public void setUp() {
		manager = new LobbyChatManager(MESSAGE_ARRAY_SIZE, MAX_MESSAGES_RETURNED);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testChatManagerMessageArraySizeMustBeGreaterThan1() {
		new LobbyChatManager(1, MAX_MESSAGES_RETURNED);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testChatManagerMaxMessagesReturnedMustBeGreaterThan0() {
		new LobbyChatManager(MESSAGE_ARRAY_SIZE, 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testChatManagerMaxMessagesReturnedCannotBeGreaterThanMessageArraySize() {
		new LobbyChatManager(MESSAGE_ARRAY_SIZE, MESSAGE_ARRAY_SIZE + 1);
	}
	
	@Test
	public void testUsingOutOfBoundsPreviousIndexReturnsNoMessages() {
		manager.addMessage("test message");
		List<String> messages = manager.getMessages(-1);
		assertTrue(messages.isEmpty());
	}
	
	@Test
	public void testChatManagerReturnsCorrectMessageIndex() {
		assertEquals(new Integer(0), manager.getMessageIndex());
		manager.addMessage("test message");
		assertEquals(new Integer(1), manager.getMessageIndex());
	}
	
	@Test
	public void testChatManagerReturnsCorrectSingleMessage() {
		int previousIndex = manager.getMessageIndex();
		manager.addMessage("test message");
		List<String> newMessages = manager.getMessages(previousIndex);
		assertEquals(new Integer(1), new Integer(newMessages.size()));
		assertEquals("test message", newMessages.get(0));
	}
	
	@Test
	public void testChatManagerReturnsCorrectMultipleMessages() {
		int previousIndex = manager.getMessageIndex();
		manager.addMessage("test message 1");
		manager.addMessage("test message 2");
		List<String> newMessages = manager.getMessages(previousIndex);
		assertEquals(new Integer(2), new Integer(newMessages.size()));
		assertEquals("test message 1", newMessages.get(0));
		assertEquals("test message 2", newMessages.get(1));
	}
	
	@Test
	public void testChatManagerWorksCorrectlyWhenCrossingInteralMessageArrayBounds() {
		LobbyChatManager newManager = new LobbyChatManager(3, 2);
		newManager.addMessage("test message 1");
		newManager.addMessage("test message 2");
		int previousIndex = newManager.getMessageIndex();
		newManager.addMessage("test message 3");
		newManager.addMessage("test message 4");
		List<String> messages = newManager.getMessages(previousIndex);
		assertEquals(new Integer(2), new Integer(messages.size()));
		assertEquals("test message 3", messages.get(0));
		assertEquals("test message 4", messages.get(1));
	}

}