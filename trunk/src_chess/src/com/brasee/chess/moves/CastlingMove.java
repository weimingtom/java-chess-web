package com.brasee.chess.moves;

import com.brasee.chess.Board;
import com.brasee.chess.Square;
import com.brasee.chess.pieces.King;
import com.brasee.chess.pieces.Piece;
import com.brasee.chess.pieces.Rook;

public class CastlingMove extends AbstractMove {

	private Piece rook;
	private Square rookStartSquare;
	private Square rookEndSquare;
	
	private CastlingMove(Piece piece, Square startSquare, Square endSquare, Piece rook,
			Square rookStartSquare, Square rookEndSquare) {
		this.piece = piece;
		this.startSquare = startSquare;
		this.endSquare = endSquare;
		this.rook = rook;
		this.rookStartSquare = rookStartSquare;
		this.rookEndSquare = rookEndSquare;
	}

	public static boolean canBeExecuted(Board board, Square startSquare, Square endSquare) {
		boolean canBeExecuted = false;
				
		Piece king = board.pieceOn(startSquare);
		if (king != null && king instanceof King && king.isFirstMove()) {
			int horizontalSquaresMove = endSquare.distanceBetweenFile(startSquare);
			if (endSquare.inSameRankAs(startSquare) && Math.abs(horizontalSquaresMove) == 2) {
				char rookFile = (horizontalSquaresMove == 2 ? 'h' : 'a');
				Square rookSquare = new Square(rookFile, startSquare.rank());
				Piece rook = board.pieceOn(rookSquare);
				if (rook != null && rook instanceof Rook && rook.isFirstMove() &&
					board.clearPathBetween(startSquare, rookSquare)) {
					canBeExecuted = true;
				}
			}
		}
		
		return canBeExecuted;
	}

	public static CastlingMove execute(Board board, Square kingStartSquare, Square kingEndSquare) {
		Square rookStartSquare = null, rookEndSquare = null;
		if (kingEndSquare.distanceBetweenFile(kingStartSquare) == 2) {
			rookStartSquare = new Square('h', kingStartSquare.rank());
			rookEndSquare = new Square('f', kingStartSquare.rank());
		}
		else {
			rookStartSquare = new Square('a', kingStartSquare.rank());
			rookEndSquare = new Square('d', kingStartSquare.rank());
		}
		Piece king = board.pieceOn(kingStartSquare);
		Piece rook = board.pieceOn(rookStartSquare);
		board.movePiece(king, kingStartSquare, kingEndSquare);
		board.movePiece(rook, rookStartSquare, rookEndSquare);
		king.updateHasMoved();
		rook.updateHasMoved();
		return new CastlingMove(king, kingStartSquare, kingEndSquare, rook, rookStartSquare, rookEndSquare);
	}

	@Override
	public MoveType moveType() {
		return MoveType.CASTLING;
	}

	public Piece rook() {
		return rook;
	}
	
	public Square rookStartSquare() {
		return rookStartSquare;
	}
	
	public Square rookEndSquare() {
		return rookEndSquare;
	}
	
	

}
