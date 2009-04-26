package com.brasee.chess.pieces;

public abstract class AbstractPiece implements Piece {

	public boolean hasMoved = false;
	
	@Override
	public boolean isFirstMove() {
		return !hasMoved;
	}
	
	@Override
	public void updateHasMoved() {
		hasMoved = true;
	}

}
