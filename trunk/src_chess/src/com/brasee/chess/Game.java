package com.brasee.chess;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.brasee.chess.pieces.Bishop;
import com.brasee.chess.pieces.King;
import com.brasee.chess.pieces.Knight;
import com.brasee.chess.pieces.Pawn;
import com.brasee.chess.pieces.Piece;
import com.brasee.chess.pieces.Queen;
import com.brasee.chess.pieces.Rook;
import com.brasee.chess.pieces.Piece.Color;

public class Game {

	private Board board;
	private Color playersTurn;
	private Map<Color, Set<Piece>> capturedPieces;
	
	public enum MoveType {
		INVALID, NORMAL, CAPTURE, CASTLING
	}
	
	public Game() {
		board = new Board();
		playersTurn = Color.WHITE;
		clearCapturedPieces();
	}

	public void initializeBoard() {
		board = new Board();
		playersTurn = Color.WHITE;
		placeWhitePieces();
		placeBlackPieces();
		clearCapturedPieces();
	}
	
	public MoveType move(Square startSquare, Square endSquare) {
		MoveType moveType = MoveType.INVALID;	
		if (board.hasPieceOn(startSquare) && board.pieceOn(startSquare).color().equals(playersTurn)) {
			moveType = executeMoveOrAttack(startSquare, endSquare);
			if (!moveType.equals(MoveType.INVALID)) {
				changePlayersTurn();
			}
		}
		return moveType;
	}
	
	public Set<Piece> capturedPieces(Color color) {
		return capturedPieces.get(color);
	}
	
	protected Board board() {
		return board;
	}
	
	private MoveType executeMoveOrAttack(Square startSquare, Square endSquare) {
		MoveType moveType = MoveType.INVALID;
		
		if (board().hasPieceOn(startSquare)) {
			Piece piece = board().pieceOn(startSquare);
			if (validCastlingMove(startSquare, endSquare)) {
				performCastlingMove(startSquare, endSquare);
				moveType = MoveType.CASTLING;
			}
			else if (piece.canMove(board, startSquare, endSquare)) {
				performNormalMove(piece, startSquare, endSquare);
				moveType = MoveType.NORMAL;
			}
			else if (piece.canAttack(board, startSquare, endSquare)) {
				performCaptureMove(piece, startSquare, endSquare);
				moveType = MoveType.CAPTURE;
			}
		}
		
		return moveType;
	}

	private void changePlayersTurn() {
		if (playersTurn.equals(Color.WHITE)) {
			playersTurn = Color.BLACK;
		}
		else {
			playersTurn = Color.WHITE;
		}
	}

	private void performNormalMove(Piece piece, Square startSquare, Square endSquare) {
		board.movePiece(piece, startSquare, endSquare);
		piece.updateHasMoved();
	}
	
	private void performCaptureMove(Piece piece, Square startSquare, Square endSquare) {
		Piece enemyPiece = board.pieceOn(endSquare);
		capturedPieces.get(enemyPiece.color()).add(enemyPiece);
		board.removePiece(endSquare);
		board.movePiece(piece, startSquare, endSquare);		
		piece.updateHasMoved();
	}
	
	/** TODO: need to add logic to disallow castling if king is currently in check */
	private boolean validCastlingMove(Square startSquare, Square endSquare) {
		boolean validCastlingMove = false;
		
		Piece king = board.pieceOn(startSquare);
		if (king != null && king instanceof King && king.isFirstMove()) {
			int horizontalSquaresMove = endSquare.distanceBetweenFile(startSquare);
			if (endSquare.inSameRankAs(startSquare) && Math.abs(horizontalSquaresMove) == 2) {
				char rookFile = (horizontalSquaresMove == 2 ? 'h' : 'a');
				Square rookSquare = new Square(rookFile, startSquare.rank());
				Piece rook = board.pieceOn(rookSquare);
				if (rook != null && rook instanceof Rook && rook.isFirstMove() &&
					board.clearPathBetween(startSquare, rookSquare)) {
					validCastlingMove = true;
				}
			}
		}
		
		return validCastlingMove;
	}
	
	private void performCastlingMove(Square kingStartSquare, Square kingEndSquare) {
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
	}
	
	private void placeWhitePieces() {
		board.placePiece(new Square("a1"), new Rook(Color.WHITE));
		board.placePiece(new Square("b1"), new Knight(Color.WHITE));
		board.placePiece(new Square("c1"), new Bishop(Color.WHITE));
		board.placePiece(new Square("d1"), new Queen(Color.WHITE));
		board.placePiece(new Square("e1"), new King(Color.WHITE));
		board.placePiece(new Square("f1"), new Bishop(Color.WHITE));
		board.placePiece(new Square("g1"), new Knight(Color.WHITE));
		board.placePiece(new Square("h1"), new Rook(Color.WHITE));
		for (char row : new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'}) {
			board.placePiece(new Square(Character.toString(row) + "2"), new Pawn(Color.WHITE));
		}		
	}
	
	private void placeBlackPieces() {
		board.placePiece(new Square("a8"), new Rook(Color.BLACK));
		board.placePiece(new Square("b8"), new Knight(Color.BLACK));
		board.placePiece(new Square("c8"), new Bishop(Color.BLACK));
		board.placePiece(new Square("d8"), new King(Color.BLACK));
		board.placePiece(new Square("e8"), new Queen(Color.BLACK));
		board.placePiece(new Square("f8"), new Bishop(Color.BLACK));
		board.placePiece(new Square("g8"), new Knight(Color.BLACK));
		board.placePiece(new Square("h8"), new Rook(Color.BLACK));
		for (char row : new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'}) {
			board.placePiece(new Square(Character.toString(row) + "7"), new Pawn(Color.BLACK));
		}
	}
	
	private void clearCapturedPieces() {
		capturedPieces = new HashMap<Color, Set<Piece>>();
		capturedPieces.put(Color.WHITE, new HashSet<Piece>());
		capturedPieces.put(Color.BLACK, new HashSet<Piece>());
	}
}
