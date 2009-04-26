package com.brasee.chess.pieces;

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

}
