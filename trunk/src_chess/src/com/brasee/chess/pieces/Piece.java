package com.brasee.chess.pieces;

import com.brasee.chess.Square;

public interface Piece {

	public boolean canMove(Square currentSquare, Square emptySquare);
	public boolean canAttack(Square currentSquare, Square occupiedSquare);
	public boolean isFirstMove();
	public void updateHasMoved();
	
}
