package com.brasee.chess.pieces;

import com.brasee.chess.Square;

public interface Piece {

	public static int WHITE = 0;
	public static int BLACK = 1;
	
	public boolean canMove(Square currentSquare, Square emptySquare);
	public boolean canAttack(Square currentSquare, Square occupiedSquare);
	public boolean isFirstMove();
	public void updateHasMoved();
	public int color();
	
}
