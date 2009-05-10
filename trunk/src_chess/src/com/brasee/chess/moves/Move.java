package com.brasee.chess.moves;

import com.brasee.chess.Square;
import com.brasee.chess.pieces.Piece;

public interface Move {

	public enum MoveType {
		INVALID, NORMAL, CAPTURE, CASTLING, EN_PASSANT
	}
	
	public Piece piece();
	public Square startSquare();
	public Square endSquare();
	public MoveType moveType();

}


