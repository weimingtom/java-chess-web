package com.brasee.chess.pieces;

import com.brasee.chess.Board;
import com.brasee.chess.Square;

public interface Piece {

	public enum Color {
		WHITE, BLACK
	}
	
	public boolean canMove(Board board, Square currentSquare, Square emptySquare);
	public boolean canAttack(Board board, Square currentSquare, Square occupiedSquare);
	public boolean isFirstMove();
	public void updateHasMoved();
	public Piece.Color color();
	
}
