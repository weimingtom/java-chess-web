package com.brasee.games.lobby;

import com.brasee.chess.pieces.Piece.Color;

public class MultipleClientGameInfo {

	private final String description;
	private final String blackPlayerName;
	private final String whitePlayerName;
	
	public MultipleClientGameInfo(MultipleClientGame game) {
		if (game == null) {
			throw new IllegalArgumentException("MultipleClientGame parameter must not be null");
		}
		
		this.whitePlayerName = game.getPlayer(Color.WHITE) != null ? game.getPlayer(Color.WHITE).getName() : null;
		this.blackPlayerName = game.getPlayer(Color.BLACK) != null ? game.getPlayer(Color.BLACK).getName() : null;
		this.description = generateGameDescription(game);
	}
	
	private String generateGameDescription(MultipleClientGame game) {
		String gameDescription = "";

		if (game.getPlayer(Color.WHITE) == null && game.getPlayer(Color.BLACK) == null) {
			gameDescription = "Empty game";
		}
		else if (game.getPlayer(Color.WHITE) != null && game.getPlayer(Color.BLACK) != null) {
			gameDescription = game.getPlayer(Color.WHITE).getName() + " vs. " + game.getPlayer(Color.BLACK).getName();
		}
		else {
			String currentPlayerName = game.getPlayer(Color.WHITE) != null ? game.getPlayer(Color.WHITE).getName() : 
				game.getPlayer(Color.BLACK).getName();
			gameDescription = currentPlayerName + " waiting for opponent";
		}

		return gameDescription;
	}

	public String getDescription() {
		return description;
	}

	public String getBlackPlayerName() {
		return blackPlayerName;
	}

	public String getWhitePlayerName() {
		return whitePlayerName;
	}
}
