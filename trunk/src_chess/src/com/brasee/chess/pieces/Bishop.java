package com.brasee.chess.pieces;

import com.brasee.chess.Board;
import com.brasee.chess.Square;

public class Bishop extends AbstractPiece {

	public Bishop(Piece.Color color) {
		super(color);
	}
	
	@Override
	public boolean canAttack(Board board, Square currentSquare, Square occupiedSquare) {
		return !currentSquare.equals(occupiedSquare) && currentSquare.inDiagonalPathWith(occupiedSquare);
	}

	@Override
	public boolean canMove(Board board, Square currentSquare, Square emptySquare) {
		return !currentSquare.equals(emptySquare) && currentSquare.inDiagonalPathWith(emptySquare);
	}

}
