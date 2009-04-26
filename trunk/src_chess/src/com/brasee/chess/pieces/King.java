package com.brasee.chess.pieces;

import com.brasee.chess.Square;

public class King extends AbstractPiece {

	public King(Piece.Color color) {
		super(color);
	}
	
	@Override
	public boolean canAttack(Square currentSquare, Square occupiedSquare) {
		return areAdjacentSquares(currentSquare, occupiedSquare);
	}

	@Override
	public boolean canMove(Square currentSquare, Square emptySquare) {
		return areAdjacentSquares(currentSquare, emptySquare);
	}
	
	private boolean areAdjacentSquares(Square square1, Square square2) {
		boolean adjacentHorizontally = Math.abs(square1.distanceBetweenFile(square2)) == 1 &&
			square1.distanceBetweenRank(square2) == 0;
		boolean adjacentVertically = Math.abs(square1.distanceBetweenRank(square2)) == 1 &&
			square1.distanceBetweenFile(square2) == 0;
		boolean adjacentDiagonally = Math.abs(square1.distanceBetweenFile(square2)) == 1 &&
			Math.abs(square1.distanceBetweenRank(square2)) == 1;
		return adjacentHorizontally || adjacentVertically || adjacentDiagonally;
	}

}
