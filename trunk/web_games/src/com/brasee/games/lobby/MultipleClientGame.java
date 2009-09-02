package com.brasee.games.lobby;

import java.util.HashMap;
import java.util.Map;

import com.brasee.chess.Game;
import com.brasee.chess.pieces.Piece.Color;

public class MultipleClientGame {

	private Game game;
	private Map<Color, String> playerSessionIds;
	
	public MultipleClientGame() {
		playerSessionIds = new HashMap<Color, String>();
		this.game = new Game();
		game.initializeBoard();
	}
	
	public Game getGame() {
		return game;
	}
	
}
