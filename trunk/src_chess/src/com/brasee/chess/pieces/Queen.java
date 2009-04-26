package com.brasee.chess.pieces;

import com.brasee.chess.Square;

public class Queen extends AbstractPiece {

	public Queen(Piece.Color color) {
		super(color);
	}
	
	@Override
	public boolean canAttack(Square currentSquare, Square occupiedSquare) {
		return currentSquare.inSameFileAs(occupiedSquare) || currentSquare.inSameRankAs(occupiedSquare) ||
		   currentSquare.inDiagonalPathWith(occupiedSquare);
	}

	@Override
	public boolean canMove(Square currentSquare, Square emptySquare) {
		return currentSquare.inSameFileAs(emptySquare) || currentSquare.inSameRankAs(emptySquare) ||
			   currentSquare.inDiagonalPathWith(emptySquare);
	}

}
