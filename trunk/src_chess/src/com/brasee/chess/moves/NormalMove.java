package com.brasee.chess.moves;

import com.brasee.chess.Board;
import com.brasee.chess.Square;
import com.brasee.chess.pieces.Piece;

public class NormalMove extends AbstractMove {

	private NormalMove(Piece piece, Square startSquare, Square endSquare) {
		this.piece = piece;
		this.startSquare = startSquare;
		this.endSquare = endSquare;
	}

	public static boolean canBeExecuted(Board board, Square startSquare, Square endSquare) {
		boolean canBeExecuted = false;
		
		if (board != null) {
			Piece piece = board.pieceOn(startSquare);
			if (piece != null && piece.canMove(board, startSquare, endSquare)) {
				canBeExecuted = true;
			}
		}
		
		return canBeExecuted;
	}

	public static NormalMove execute(Board board, Square startSquare, Square endSquare) {
		Piece piece = board.pieceOn(startSquare);
		board.movePiece(piece, startSquare, endSquare);
		piece.incrementTimesMoved();
		return new NormalMove(piece, startSquare, endSquare);
	}
	
	@Override
	public void undo(Board board) {
		board.movePiece(piece, endSquare, startSquare);
		piece.decrementTimesMove();
	}

	@Override
	public MoveType moveType() {
		return MoveType.NORMAL;
	}
}
