package com.brasee.games.lobby;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.brasee.chess.Game;
import com.brasee.chess.Square;
import com.brasee.chess.moves.Move;
import com.brasee.chess.moves.Move.MoveType;
import com.brasee.chess.pieces.Piece;
import com.brasee.chess.pieces.Piece.Color;
import com.brasee.chess.pieces.Piece.PieceType;

public class MultipleClientGame {

	private Game game;
	private Map<Color, String> playerSessionIds;
	private Map<Color, String> playerNames;
	
	private BufferedImage previewImage;
	private GamePreviewImageGenerator imageGenerator;
	
	private Object previewImageLock = new Object();
	
	public MultipleClientGame(GamePreviewImageGenerator imageGenerator) {
		this.imageGenerator	= imageGenerator;
		
		playerSessionIds = new ConcurrentHashMap<Color, String>();
		playerNames = new ConcurrentHashMap<Color, String>();
		this.game = new Game();
		this.game.initializeBoard();

		this.previewImage = this.imageGenerator.createPngPreviewImage(this.game);
	}
	
	public Move move(Square startSquare, Square endSquare) {
		Move move = game.move(startSquare, endSquare);
		if (!move.moveType().equals(MoveType.INVALID)) {
			synchronized (previewImageLock) {
				this.previewImage = this.imageGenerator.createPngPreviewImage(game);	
			}
		}
		return move;
	}
	
	public List<Move> moves() {
		return game.moves();
	}
	
	public Color playersTurn() {
		return game.playersTurn();
	}
	
	public Move promote(Square startSquare, Square endSquare, PieceType pieceType) {
		Move move = game.promote(startSquare, endSquare, pieceType);
		if (!move.moveType().equals(MoveType.INVALID)) {
			synchronized (previewImageLock) {
				this.previewImage = this.imageGenerator.createPngPreviewImage(game);
			}
		}
		return move;
	}
	
	public Map<Square, Piece> pieces() {
		return game.pieces();
	}
	
	public List<Piece> capturedPieces(Color color) {
		return game.capturedPieces(color);
	}
	
	public BufferedImage getPreviewImage() {
		synchronized (previewImageLock) {
			return previewImage;
		}
	}
	
	public String getPlayerName(Color color) {
		return playerNames.get(color);
	}
	
	public String getPlayerSessionId(Color color) {
		return playerSessionIds.get(color);
	}
	
}
