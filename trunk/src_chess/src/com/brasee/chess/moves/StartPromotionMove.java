package com.brasee.chess.moves;

import com.brasee.chess.Board;
import com.brasee.chess.Square;
import com.brasee.chess.pieces.Piece;
import com.brasee.chess.pieces.Piece.Color;
import com.brasee.chess.pieces.Piece.PieceType;

public class StartPromotionMove extends AbstractMove {

	private Piece opposingPiece;
	
	private StartPromotionMove(Piece piece, Square startSquare, Square endSquare) {
		this.piece = piece;
		this.startSquare = startSquare;
		this.endSquare = endSquare;
		this.opposingPiece = null;
	}
	
	private StartPromotionMove(Piece piece, Square startSquare, Square endSquare, Piece opposingPiece) {
		this.piece = piece;
		this.startSquare = startSquare;
		this.endSquare = endSquare;
		this.opposingPiece = opposingPiece;
	}
	
	@Override
	public void undo(Board board) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public MoveType moveType() {
		return MoveType.START_PROMOTION;
	}


	public static boolean canBeExecuted(Board board, Square startSquare, Square endSquare) {
		boolean canBeExecuted = false;
		
		if (board != null) {
			Piece piece = board.pieceOn(startSquare);
			if (piece != null && PieceType.PAWN.equals(piece.pieceType()) &&
					endSquareInLastRow(endSquare, piece.color())) {
				if (piece.canMove(board, startSquare, endSquare) ||
					piece.canAttack(board, startSquare, endSquare)) {
					canBeExecuted = true;
				}
			}
		}
		
		return canBeExecuted;
	}

	public static Move execute(Board board, Square startSquare, Square endSquare) {
		StartPromotionMove startPromotionMove = null;
		
		if (board.hasPieceOn(endSquare)) {
			Piece piece = board.pieceOn(startSquare);
			Piece opposingPiece = board.pieceOn(endSquare);
			board.removePiece(endSquare);
			board.movePiece(piece, startSquare, endSquare);		
			piece.updateHasMoved();
			startPromotionMove = new StartPromotionMove(piece, startSquare, endSquare, opposingPiece);
		}
		else {
			Piece piece = board.pieceOn(startSquare);
			board.movePiece(piece, startSquare, endSquare);
			piece.updateHasMoved();
			startPromotionMove = new StartPromotionMove(piece, startSquare, endSquare);
		}
		
		return startPromotionMove;
	}
	

	private static boolean endSquareInLastRow(Square endSquare, Color color) {
		if ((color.equals(Color.WHITE) && endSquare.rank() == '8') ||
			(color.equals(Color.BLACK) && endSquare.rank() == '1')) {
			return true;
		}
		else {
			return false;
		}
	}

	public Piece opposingPiece() {
		return opposingPiece;
	}

}
