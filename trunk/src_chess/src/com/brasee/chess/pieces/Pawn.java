package com.brasee.chess.pieces;

import com.brasee.chess.Board;
import com.brasee.chess.Square;

public class Pawn extends AbstractPiece {

	public Pawn(Piece.Color color) {
		super(color);
	}
	
	@Override
	public boolean canMove(Board board, Square currentSquare, Square emptySquare) {
		if (this == board.pieceOn(currentSquare) &&
			!board.hasPieceOn(emptySquare) &&
			emptySquare.distanceBetweenRank(currentSquare) == forwardDistanceForColor(1) &&
			emptySquare.inSameFileAs(currentSquare)) {
			return true;
		}
		else if (isFirstMove() && 
				this == board.pieceOn(currentSquare) &&
				!board.hasPieceOn(emptySquare) &&
				board.clearPathBetween(currentSquare, emptySquare) &&
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
	public boolean canAttack(Board board, Square currentSquare, Square occupiedSquare) {
		if (this == board.pieceOn(currentSquare) &&
			board.hasPieceOn(occupiedSquare) && !this.color().equals(board.pieceOn(occupiedSquare).color()) &&
			occupiedSquare.distanceBetweenRank(currentSquare) == forwardDistanceForColor(1) && 
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
