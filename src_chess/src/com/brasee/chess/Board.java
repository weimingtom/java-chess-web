package com.brasee.chess;

import java.util.HashMap;
import java.util.Map;

import com.brasee.chess.pieces.Piece;

public class Board {

	Map<String, Piece> pieceLocations = new HashMap<String, Piece>();
	
	public void placePiece(String square, Piece piece) {
		pieceLocations.put(square, piece);
	}

	public boolean isPieceOn(String square) {
		return pieceOn(square) != null;
	}

	public Piece pieceOn(String square) {
		return pieceLocations.get(square);
	}

	public void movePiece(Piece piece, String startSquare, String endSquare) {
		if (piece != null && piece.equals(pieceOn(startSquare))) {
			pieceLocations.remove(startSquare);
			pieceLocations.put(endSquare, piece);
		}
	}

}
