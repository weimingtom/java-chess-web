package com.brasee.chess.moves;

import java.util.Arrays;
import java.util.List;

import com.brasee.chess.Board;
import com.brasee.chess.Square;
import com.brasee.chess.pieces.Bishop;
import com.brasee.chess.pieces.Knight;
import com.brasee.chess.pieces.Piece;
import com.brasee.chess.pieces.Queen;
import com.brasee.chess.pieces.Rook;
import com.brasee.chess.pieces.Piece.Color;
import com.brasee.chess.pieces.Piece.PieceType;

public class PromotionMove extends AbstractMove {

	private static List<PieceType> promotionPieceTypes = 
		Arrays.asList(new PieceType[] {PieceType.QUEEN, PieceType.ROOK, PieceType.BISHOP, PieceType.KNIGHT});
	
	private Piece promotionPiece;
	private Piece opposingPiece;
	
	public PromotionMove(Piece piece, Square startSquare,
			Square promotionSquare, Piece promotionPiece, Piece opposingPiece) {
		this.piece = piece;
		this.startSquare = startSquare;
		this.endSquare = promotionSquare;
		this.promotionPiece = promotionPiece;
		this.opposingPiece = opposingPiece;
	}

	@Override
	public MoveType moveType() {
		return MoveType.PROMOTION;
	}

	@Override
	public void undo(Board board) {
		// TODO Implement this!
	}

	public static boolean canBeExecuted(Board board, PieceType pieceType, Square promotionSquare, Move lastMove) {
		boolean canBeExecuted = false;
		
		if (lastMove != null && lastMove.moveType().equals(MoveType.START_PROMOTION) &&
			promotionSquare != null && promotionSquare.equals(lastMove.endSquare())) {
			if (promotionPieceTypes.contains(pieceType)) {
				canBeExecuted = true;
			}
		}
		
		return canBeExecuted;
	}

	public static Move execute(Board board, PieceType pieceType, Square promotionSquare, Move lastMove) {
		StartPromotionMove startPromotionMove = (StartPromotionMove) lastMove;
		Piece piece = board.pieceOn(promotionSquare);
		Piece promotionPiece = createPromotionPiece(pieceType, piece.color());
		
		board.removePiece(promotionSquare);
		board.placePiece(promotionSquare, promotionPiece);
		
		return new PromotionMove(piece, startPromotionMove.startSquare(), promotionSquare,
				promotionPiece, startPromotionMove.opposingPiece());
	}

	private static Piece createPromotionPiece(PieceType pieceType, Color color) {
		Piece promotionPiece = null;
		
		if (PieceType.QUEEN.equals(pieceType)) {
			promotionPiece = new Queen(color);
		}
		else if (PieceType.ROOK.equals(pieceType)) {
			promotionPiece = new Rook(color);
		}
		else if (PieceType.BISHOP.equals(pieceType)) {
			promotionPiece = new Bishop(color);
		}
		else if (PieceType.KNIGHT.equals(pieceType)) {
			promotionPiece = new Knight(color);
		}
		
		return promotionPiece;
	}

}
