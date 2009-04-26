package com.brasee.chess.pieces;

public abstract class AbstractPiece implements Piece {

	private boolean hasMoved = false;
	protected int color = Piece.WHITE;
	
	public AbstractPiece(int color) {
		if (color == Piece.WHITE || color == Piece.BLACK) {
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
	public int color() {
		return color;
	}

}
