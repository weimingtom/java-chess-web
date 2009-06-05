package com.brasee.games.lobby;

import java.util.LinkedList;
import java.util.List;

public class LobbyChatManager {
	
	private static int MIN_MESSAGE_ARRAY_SIZE = 2;
	private static int MIN_MAX_MESSAGES_TO_RETURN = 1;
	
	private String[] messages;
	private int nextMessageIndex;
	private int maxMessagesToReturn;
	
	public LobbyChatManager(int messageArraySize, int maxMessagesToReturn) {
		if (messageArraySize < MIN_MESSAGE_ARRAY_SIZE) {
			throw new IllegalArgumentException("Message array size must be greater than or equal to " 
					+ MIN_MESSAGE_ARRAY_SIZE);
		}
		if (maxMessagesToReturn < MIN_MAX_MESSAGES_TO_RETURN) {
			throw new IllegalArgumentException("Maximum messages to return must be greater than or equal to " 
					+ MIN_MAX_MESSAGES_TO_RETURN);
		}
		if (maxMessagesToReturn > messageArraySize) {
			throw new IllegalArgumentException("Maximum messages to return must be less than or equal to "
					+ " message array size.");
		}
		
		this.maxMessagesToReturn = maxMessagesToReturn;
		this.messages = new String[messageArraySize];
		this.nextMessageIndex = 0;
	}
	
	public synchronized void addMessage(String message) {
		int newMessageIndex = incrementMessageIndex(this.nextMessageIndex);
	
		// add new message before updating index to make sure
		// getMessages doesn't miss a message
		this.messages[nextMessageIndex] = message;
		nextMessageIndex = newMessageIndex;
	}
	
	public Integer getMessageIndex() {
		return nextMessageIndex;
	}
	
	public List<String> getMessages(int previousNextMessageIndex) {
		LinkedList<String> newMessages = new LinkedList<String>();
		
		if (isValidIndex(previousNextMessageIndex)) {
			int i = 0;
			int iterNextMessageIndex = this.nextMessageIndex;
			while (iterNextMessageIndex != previousNextMessageIndex && i < this.maxMessagesToReturn) {
				iterNextMessageIndex = decrementMessageIndex(iterNextMessageIndex);
				if (this.messages[iterNextMessageIndex] != null) {
					newMessages.addFirst(this.messages[iterNextMessageIndex]);
				}
				i++;
			}
		}
		
		return newMessages;
	}

	private int decrementMessageIndex(int messageIteratorIndex) {
		int nextMessageIteratorIndex;
		
		if (messageIteratorIndex == 0) {
			nextMessageIteratorIndex = this.messages.length - 1;
		}
		else {
			nextMessageIteratorIndex = messageIteratorIndex - 1;	
		}
		
		return nextMessageIteratorIndex;
	}
	
	private int incrementMessageIndex(int messageIteratorIndex) {
		int nextMessageIteratorIndex;
		
		if (messageIteratorIndex == this.messages.length - 1) {
			nextMessageIteratorIndex = 0;
		}
		else {
			nextMessageIteratorIndex = messageIteratorIndex + 1;
		}
		
		return nextMessageIteratorIndex;
	}

	private boolean isValidIndex(int previousIndex) {
		return previousIndex >= 0 && previousIndex < this.messages.length;
	}
	
}
