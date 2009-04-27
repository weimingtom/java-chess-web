package com.brasee.chess.pieces;

import com.brasee.chess.Square;

public class Knight extends AbstractPiece {

	public Knight(Piece.Color color) {
		super(color);
	}
	
	@Override
	public boolean canAttack(Square currentSquare, Square occupiedSquare) {
		return differByTwoVerticalAndOneHorizontal(currentSquare, occupiedSquare) ||
			differByOneVerticalAndTwoHorizontal(currentSquare, occupiedSquare);
	}

	@Override
	public boolean canMove(Square currentSquare, Square emptySquare) {
		return differByTwoVerticalAndOneHorizontal(currentSquare, emptySquare) ||
			differByOneVerticalAndTwoHorizontal(currentSquare, emptySquare);
	}

	private boolean differByOneVerticalAndTwoHorizontal(Square square1, Square square2) {
		return Math.abs(square1.distanceBetweenRank(square2)) == 2 && 
			Math.abs(square1.distanceBetweenFile(square2)) == 1;
	}

	private boolean differByTwoVerticalAndOneHorizontal(Square square1, Square square2) {
		return Math.abs(square1.distanceBetweenRank(square2)) == 1 && 
			Math.abs(square1.distanceBetweenFile(square2)) == 2;
	}

}