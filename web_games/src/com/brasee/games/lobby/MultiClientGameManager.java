package com.brasee.games.lobby;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;

public class MultiClientGameManager {

	private static int DEFAULT_USER_EXPIRY_TIME = 7000;
	
	private int numberOfGames;
	private Map<Integer, MultiClientGame> games;
	private GamePreviewImageGeneratorFactory imageGeneratorFactory;

	public MultiClientGameManager(int numberOfGames, GamePreviewImageGeneratorFactory imageGeneratorFactory) {
		if (numberOfGames <= 0) {
			throw new IllegalArgumentException("numberOfGames must be greater than 0");
		}
		if (imageGeneratorFactory == null) {
			throw new IllegalArgumentException("imageGeneratoryFactory must not be null");
		}
		
		this.imageGeneratorFactory = imageGeneratorFactory;
		this.numberOfGames = numberOfGames;
		this.games = new HashMap<Integer, MultiClientGame>();
		for (int gameId = 1; gameId <= numberOfGames; gameId++) {
			UserManager userManager = new UserManager();
			userManager.setExpiryTimeInMilliseconds(DEFAULT_USER_EXPIRY_TIME);
			ChatManager	chatManager = new ChatManager();
			MultiClientGame game = new MultiClientGame(this.imageGeneratorFactory.getInstance(), userManager, chatManager);
			games.put(gameId, game);
		}
	}
	
	public MultiClientGame retrieveGame(String gameIdString) {
		MultiClientGame multipleClientGame = null;
		
		try {
			if (gameIdString != null && StringUtils.isNumeric(gameIdString)) {
				Integer gameId = Integer.parseInt(gameIdString);
				multipleClientGame = this.getGame(gameId);
			}
		}
		catch (Exception e) {
			Logger.getLogger(MultiClientGameManager.class.getName()).warning("Exception while retrieving game with id " 
					+ gameIdString + ", returning null.");
		}
		
		return multipleClientGame;
	}
	
	public int getNumberOfGames() {
		return numberOfGames;
	}
	
	public MultiClientGame getGame(int gameId) {
		return games.get(gameId);
	}


	
}
