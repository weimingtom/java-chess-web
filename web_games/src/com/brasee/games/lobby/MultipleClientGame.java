package com.brasee.games.lobby;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import com.brasee.chess.Game;
import com.brasee.chess.pieces.Piece.Color;

public class MultipleClientGame {

	private Game game;
	private Map<Color, String> playerSessionIds;
	private BufferedImage previewImage;
	private GamePreviewImageGenerator imageGenerator;
	
	public MultipleClientGame(GamePreviewImageGenerator imageGenerator) {
		this.imageGenerator	= imageGenerator;
		
		playerSessionIds = new HashMap<Color, String>();
		this.game = new Game();
		this.game.initializeBoard();

		this.previewImage = this.imageGenerator.createPngPreviewImage(this.game);
	}
	
	public Game getGame() {
		return game;
	}
	
	public BufferedImage getPreviewImage() {
		return previewImage;
	}
	
}
