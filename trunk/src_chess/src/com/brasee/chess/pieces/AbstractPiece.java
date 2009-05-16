package com.brasee.chess.pieces;

import com.brasee.chess.Board;
import com.brasee.chess.Square;

public abstract class AbstractPiece implements Piece {

	private int timesMoved = 0;
	private Color color;
	
	public AbstractPiece(Color color) {
		if (color == Color.WHITE || color == Color.BLACK) {
			this.color = color;
		}
		else {
			throw new InvalidPieceColorException();
		}
	}
	
	@Override
	public boolean isFirstMove() {
		return timesMoved == 0;
	}
	
	@Override
	public void incrementTimesMoved() {
		timesMoved += 1;
	}
	
	@Override
	public void decrementTimesMove() {
		if (timesMoved > 0) {
			timesMoved -= 1;
		}
	}
	
	@Override
	public Color color() {
		return color;
	}

	@Override
	public abstract PieceType pieceType();
	
	protected boolean validSetupForMove(Board board, Square currentSquare, Square emptySquare) {
		return (this == board.pieceOn(currentSquare) && !board.hasPieceOn(emptySquare));
	}
	
	protected boolean validSetupForAttack(Board board, Square currentSquare, Square occupiedSquare) {
		return (this == board.pieceOn(currentSquare) &&
				board.hasPieceOn(occupiedSquare) && 
				!this.color().equals(board.pieceOn(occupiedSquare).color()));
	}

}
