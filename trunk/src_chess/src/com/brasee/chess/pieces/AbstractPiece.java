package com.brasee.chess.pieces;

import com.brasee.chess.Board;
import com.brasee.chess.Square;

public abstract class AbstractPiece implements Piece {

	private boolean hasMoved = false;
	private Piece.Color color;
	
	public AbstractPiece(Piece.Color color) {
		if (color == Piece.Color.WHITE || color == Piece.Color.BLACK) {
			this.color = color;
		}
		else {
			throw new InvalidPieceColorException();
		}
	}
	
	@Override
	public boolean isFirstMove() {
		return !hasMoved;
	}
	
	@Override
	public void updateHasMoved() {
		hasMoved = true;
	}
	
	@Override
	public Piece.Color color() {
		return color;
	}
	
	protected boolean validSetupForMove(Board board, Square currentSquare, Square emptySquare) {
		return (this == board.pieceOn(currentSquare) && !board.hasPieceOn(emptySquare));
	}
	
	protected boolean validSetupForAttack(Board board, Square currentSquare, Square occupiedSquare) {
		return (this == board.pieceOn(currentSquare) &&
				board.hasPieceOn(occupiedSquare) && 
				!this.color().equals(board.pieceOn(occupiedSquare).color()));
	}

}
