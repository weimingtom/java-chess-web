package com.brasee.chess.pieces;

import com.brasee.chess.Square;

public interface Piece {

	enum Color {
		WHITE, BLACK
	}
	
	public boolean canMove(Square currentSquare, Square emptySquare);
	public boolean canAttack(Square currentSquare, Square occupiedSquare);
	public boolean isFirstMove();
	public void updateHasMoved();
	public Piece.Color color();
	
}
