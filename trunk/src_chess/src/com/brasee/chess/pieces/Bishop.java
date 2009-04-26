package com.brasee.chess.pieces;

import com.brasee.chess.Square;

public class Bishop extends AbstractPiece {

	public Bishop(Piece.Color color) {
		super(color);
	}
	
	@Override
	public boolean canAttack(Square currentSquare, Square occupiedSquare) {
		return currentSquare.inDiagonalPathWith(occupiedSquare);
	}

	@Override
	public boolean canMove(Square currentSquare, Square emptySquare) {
		return currentSquare.inDiagonalPathWith(emptySquare);
	}

}
