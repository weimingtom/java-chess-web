package com.brasee.games.lobby;

import com.brasee.chess.Game;

public class MultipleClientGame {

	private Game game;
	private String whitePlayerSessionId;
	private String blackPlayerSessionId;
	
	public MultipleClientGame() {
		this.game = new Game();
	}
	
	public Game getGame() {
		return game;
	}

	public String getWhitePlayerSessionId() {
		return whitePlayerSessionId;
	}

	public void setWhitePlayerSessionId(String whitePlayerSessionId) {
		this.whitePlayerSessionId = whitePlayerSessionId;
	}

	public String getBlackPlayerSessionId() {
		return blackPlayerSessionId;
	}

	public void setBlackPlayerSessionId(String blackPlayerSessionId) {
		this.blackPlayerSessionId = blackPlayerSessionId;
	}
}
