package com.brasee.chess.moves;

import com.brasee.chess.Square;
import com.brasee.chess.pieces.Piece;

public abstract class AbstractMove implements Move {

	protected Piece piece;
	protected Square startSquare;
	protected Square endSquare;
	
	@Override
	public Square endSquare() {
		return endSquare;
	}

	@Override
	public Piece piece() {
		return piece;
	}

	@Override
	public Square startSquare() {
		return startSquare;
	}
	
	@Override
	public abstract MoveType moveType();

}
