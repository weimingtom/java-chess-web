package com.brasee.chess.pieces;

import com.brasee.chess.Square;

public class Rook extends AbstractPiece {

	public Rook(int color) {
		super(color);
	}
	
	@Override
	public boolean canAttack(Square currentSquare, Square occupiedSquare) {
		return canMove(currentSquare, occupiedSquare);
	}

	@Override
	public boolean canMove(Square currentSquare, Square emptySquare) {
		if (Math.abs(currentSquare.distanceBetweenFile(emptySquare)) > 0 && currentSquare.distanceBetweenRank(emptySquare) == 0) {
			return true;
		}
		else if (Math.abs(currentSquare.distanceBetweenRank(emptySquare)) > 0 && currentSquare.distanceBetweenFile(emptySquare) == 0) {
			return true;
		}
		else {
			return false;
		}
	}

}
