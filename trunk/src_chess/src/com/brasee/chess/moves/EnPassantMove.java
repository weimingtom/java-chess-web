package com.brasee.chess.moves;

import com.brasee.chess.Board;
import com.brasee.chess.Square;
import com.brasee.chess.pieces.Pawn;
import com.brasee.chess.pieces.Piece;
import com.brasee.chess.pieces.Piece.PieceType;

public class EnPassantMove extends AbstractMove {

	private Piece opposingPiece;
	private Square opposingPieceSquare;
	
	private EnPassantMove(Piece piece, Square startSquare, Square endSquare, Piece opposingPawn,
			Square opposingPawnSquare) {
		this.piece = piece;
		this.startSquare = startSquare;
		this.endSquare = endSquare;
		this.opposingPiece = opposingPawn;
		this.opposingPieceSquare = opposingPawnSquare;
	}

	public static boolean canBeExecuted(Board board, Square startSquare, Square endSquare, Move lastMove) {
		boolean canBeExecuted = false;
		
		Piece piece = board.pieceOn(startSquare);
		if (piece != null && PieceType.PAWN.equals(piece.pieceType())) {
			Pawn pawn = (Pawn) piece;
			if (pawn.canMoveEnPassant(board, startSquare, endSquare)) {
				Piece opposingPawn = board.pieceOn(new Square(endSquare.file(), startSquare.rank()));
				if (opposingPawn != null && PieceType.PAWN.equals(opposingPawn.pieceType()) &&
					lastMove != null && lastMove.piece() == opposingPawn && 
					Math.abs(lastMove.startSquare().distanceBetweenRank(lastMove.endSquare())) == 2) {
					canBeExecuted = true;
				}
			}
		}
		
		return canBeExecuted;
	}

	public static EnPassantMove execute(Board board, Square startSquare, Square endSquare) {
		Piece pawn = board.pieceOn(startSquare);
		Square opposingPawnSquare = new Square(endSquare.file(), startSquare.rank());
		Piece opposingPawn = board.pieceOn(opposingPawnSquare);
		board.movePiece(pawn, startSquare, endSquare);
		board.removePiece(opposingPawnSquare);
		pawn.updateHasMoved();
		return new EnPassantMove(pawn, startSquare, endSquare, opposingPawn, opposingPawnSquare);
	}

	@Override
	public MoveType moveType() {
		return MoveType.EN_PASSANT;
	}

	public Piece opposingPiece() {
		return opposingPiece;
	}
	
	public Square opposingPieceSquare() {
		return opposingPieceSquare;
	}

}
