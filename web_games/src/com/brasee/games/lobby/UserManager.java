package com.brasee.games.lobby;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class UserManager {
	
	/** Maps the sessionId to the user's name */
	private Map<String, String> userNameMap = new ConcurrentHashMap<String, String>();
	
	/** Maps the sessionId to the time it last sent a refresh */
	private Map<String, Long> userRefreshMap = new ConcurrentHashMap<String, Long>();
	
	private long expiryTimeInMilliseconds = 5000;
	
	/**
	 * Returns true if the given sessionId is connected to the game, otherwise returns false.
	 * 
	 * @param sessionId
	 * @return
	 */
	public boolean isConnected(String sessionId) {
		return userNameMap.containsKey(sessionId);
	}
	
	/**
	 * Indicates that this user is connected at the current time.
	 * 
	 * @param sessionId
	 * @param userName
	 * @return
	 */
	public void refreshUser(String sessionId, String userName) {
		Long currentTime = System.currentTimeMillis();
		synchronized(userRefreshMap) {
			userNameMap.put(sessionId, userName);
			userRefreshMap.put(sessionId, currentTime);
		}
	}
	
	/**
	 * Retrieve the userNames of all users that have refreshed within the last
	 * expiryTimeInMilliseconds milliseconds.  If a user has not refreshed within
	 * that amount of time, remove their sessionId from both the maps.
	 *   
	 * @return
	 */
	public List<String> getCurrentUserNames() {
		List<String> currentUserNames = new ArrayList<String>();
		Long currentTime = System.currentTimeMillis();
		
		Set<String> sessionIds = new HashSet<String>();
		synchronized(userRefreshMap) {
			for (String sessionId : userRefreshMap.keySet()) {
				sessionIds.add(new String(sessionId));
			}
		}

		for (String sessionId : sessionIds) {
			Long lastRefreshTime = userRefreshMap.get(sessionId);
			if (lastRefreshTime == null || 
					currentTime.longValue() - lastRefreshTime.longValue() > expiryTimeInMilliseconds) {
				userNameMap.remove(sessionId);
				userRefreshMap.remove(sessionId);
			}
			else {
				currentUserNames.add(userNameMap.get(sessionId));
			}
		}

		Collections.sort(currentUserNames);
		return currentUserNames;
	}

	public void setExpiryTimeInMilliseconds(long expiryTimeInMilliseconds) {
		this.expiryTimeInMilliseconds = expiryTimeInMilliseconds;
	}
	
}
