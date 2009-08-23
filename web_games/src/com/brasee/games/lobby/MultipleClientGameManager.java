package com.brasee.games.lobby;

import java.util.HashMap;
import java.util.Map;

public class MultipleClientGameManager {

	private int numberOfGames;
	private Map<Integer, MultipleClientGame> games;
	
	public MultipleClientGameManager(int numberOfGames) {
		if (numberOfGames <= 0) {
			throw new IllegalArgumentException("numberOfGames must be greater than 0");
		}
		
		this.numberOfGames = numberOfGames;
		this.games = new HashMap<Integer, MultipleClientGame>();
		for (int gameId = 1; gameId <= numberOfGames; gameId++) {
			MultipleClientGame game = new MultipleClientGame();
			games.put(gameId, game);
		}
	}
	
	public int getNumberOfGames() {
		return numberOfGames;
	}
	
	public MultipleClientGame getGame(int gameId) {
		return games.get(gameId);
	}
	
}
