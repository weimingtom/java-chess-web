package com.brasee.chess.pieces;

import com.brasee.chess.Square;

public class Rook extends AbstractPiece {

	public Rook(Piece.Color color) {
		super(color);
	}
	
	@Override
	public boolean canAttack(Square currentSquare, Square occupiedSquare) {
		return inSameRankOrFile(currentSquare, occupiedSquare);
	}

	@Override
	public boolean canMove(Square currentSquare, Square emptySquare) {
		return inSameRankOrFile(currentSquare, emptySquare);
	}
	
	private boolean inSameRankOrFile(Square square1, Square square2) {
		return square1.inSameRankAs(square2) || square1.inSameFileAs(square2);
	}

}
