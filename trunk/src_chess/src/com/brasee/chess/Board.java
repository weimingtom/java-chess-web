package com.brasee.chess;

import java.util.HashMap;
import java.util.Map;

import com.brasee.chess.pieces.Piece;

public class Board {

	Map<Square, Piece> pieceLocations = new HashMap<Square, Piece>();
	
	public void placePiece(Square square, Piece piece) {
		if (pieceLocations.get(square) == null) {
			pieceLocations.put(square, piece);
		}
		else {
			throw new SquareAlreadyOccupiedException();
		}
	}

	public boolean isPieceOn(Square square) {
		return pieceOn(square) != null;
	}

	public Piece pieceOn(Square square) {
		return pieceLocations.get(square);
	}

	public void movePiece(Piece piece, Square startSquare, Square endSquare) {
		if (piece != null && piece.equals(pieceOn(startSquare))) {
			pieceLocations.remove(startSquare);
			pieceLocations.put(endSquare, piece);
		}
	}

}
