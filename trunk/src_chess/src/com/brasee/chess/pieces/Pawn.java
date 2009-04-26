package com.brasee.chess.pieces;

import com.brasee.chess.Square;

/**
 * TODO: add directional knowledge to Pawns (white moves 1 to 8, black moves 8 to 1).
 */
public class Pawn extends AbstractPiece {

	@Override
	public boolean canMove(Square currentSquare, Square emptySquare) {
		if (emptySquare.distanceBetweenRank(currentSquare) == 1) {
			return true;
		}
		else if (isFirstMove() && emptySquare.distanceBetweenRank(currentSquare) == 2) {
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
		if (occupiedSquare.distanceBetweenRank(currentSquare) == 1 && 
				Math.abs(occupiedSquare.distanceBetweenFile(currentSquare)) == 1) {
			return true;
		}
		else {
			return false;
		}
	}

}
