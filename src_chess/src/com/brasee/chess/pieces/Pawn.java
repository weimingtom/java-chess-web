package com.brasee.chess.pieces;

import com.brasee.chess.Square;

public class Pawn extends AbstractPiece {

	public Pawn(Piece.Color color) {
		super(color);
	}
	
	@Override
	public boolean canMove(Square currentSquare, Square emptySquare) {
		if (emptySquare.distanceBetweenRank(currentSquare) == forwardDistanceForColor(1) &&
			emptySquare.inSameFileAs(currentSquare)) {
			return true;
		}
		else if (isFirstMove() && 
				 emptySquare.distanceBetweenRank(currentSquare) == forwardDistanceForColor(2) &&
				 emptySquare.inSameFileAs(currentSquare)) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * TODO: still need to add en passant logic.
	 */
	@Override
	public boolean canAttack(Square currentSquare, Square occupiedSquare) {
		if (occupiedSquare.distanceBetweenRank(currentSquare) == forwardDistanceForColor(1) && 
				Math.abs(occupiedSquare.distanceBetweenFile(currentSquare)) == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private int forwardDistanceForColor(int forwardDistance) {
		if (color() == Piece.Color.WHITE) {
			return forwardDistance;
		}
		else {
			return forwardDistance * -1;
		}
	}

}
