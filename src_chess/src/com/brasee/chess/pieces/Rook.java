package com.brasee.chess.pieces;

import com.brasee.chess.Board;
import com.brasee.chess.Square;

public class Rook extends AbstractPiece {

	public Rook(Piece.Color color) {
		super(color);
	}
	
	@Override
	public boolean canAttack(Board board, Square currentSquare, Square occupiedSquare) {
		return differentSquareInSameRankOrFile(currentSquare, occupiedSquare);
	}

	@Override
	public boolean canMove(Board board, Square currentSquare, Square emptySquare) {
		return differentSquareInSameRankOrFile(currentSquare, emptySquare);
	}
	
	private boolean differentSquareInSameRankOrFile(Square square1, Square square2) {
		return !square1.equals(square2) && (square1.inSameRankAs(square2) || square1.inSameFileAs(square2));
	}

}
