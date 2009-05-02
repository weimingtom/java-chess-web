package com.brasee.chess;

import com.brasee.chess.pieces.Bishop;
import com.brasee.chess.pieces.King;
import com.brasee.chess.pieces.Knight;
import com.brasee.chess.pieces.Pawn;
import com.brasee.chess.pieces.Piece;
import com.brasee.chess.pieces.Queen;
import com.brasee.chess.pieces.Rook;

public class Game {

	private Board board;
	private Piece.Color playersTurn;
	
	public enum MoveType {
		INVALID, NORMAL
	}
	
	public Game() {
		board = new Board();
		playersTurn = Piece.Color.WHITE;
	}
	
	public void initializeBoard() {
		board = new Board();
		playersTurn = Piece.Color.WHITE;
		placeWhitePieces();
		placeBlackPieces();
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
	
	public Board board() {
		return board;
	}

	private MoveType executeMoveOrAttack(Square startSquare, Square endSquare) {
		MoveType moveType = MoveType.INVALID;
		
		if (board().hasPieceOn(startSquare)) {
			Piece piece = board().pieceOn(startSquare);
			if (piece.canMove(board, startSquare, endSquare)) {
				board.movePiece(piece, startSquare, endSquare);
				moveType = MoveType.NORMAL;
			}
			else if (piece.canAttack(board, startSquare, endSquare)) {
				// TODO: implement this!
			}
		}
		
		return moveType;
	}

	private void changePlayersTurn() {
		if (playersTurn.equals(Piece.Color.WHITE)) {
			playersTurn = Piece.Color.BLACK;
		}
		else {
			playersTurn = Piece.Color.WHITE;
		}
	}

	private void placeWhitePieces() {
		board.placePiece(new Square("a1"), new Rook(Piece.Color.WHITE));
		board.placePiece(new Square("b1"), new Knight(Piece.Color.WHITE));
		board.placePiece(new Square("c1"), new Bishop(Piece.Color.WHITE));
		board.placePiece(new Square("d1"), new Queen(Piece.Color.WHITE));
		board.placePiece(new Square("e1"), new King(Piece.Color.WHITE));
		board.placePiece(new Square("f1"), new Bishop(Piece.Color.WHITE));
		board.placePiece(new Square("g1"), new Knight(Piece.Color.WHITE));
		board.placePiece(new Square("h1"), new Rook(Piece.Color.WHITE));
		for (char row : new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'}) {
			board.placePiece(new Square(Character.toString(row) + "2"), new Pawn(Piece.Color.WHITE));
		}		
	}
	
	public void placeBlackPieces() {
		board.placePiece(new Square("a8"), new Rook(Piece.Color.BLACK));
		board.placePiece(new Square("b8"), new Knight(Piece.Color.BLACK));
		board.placePiece(new Square("c8"), new Bishop(Piece.Color.BLACK));
		board.placePiece(new Square("d8"), new King(Piece.Color.BLACK));
		board.placePiece(new Square("e8"), new Queen(Piece.Color.BLACK));
		board.placePiece(new Square("f8"), new Bishop(Piece.Color.BLACK));
		board.placePiece(new Square("g8"), new Knight(Piece.Color.BLACK));
		board.placePiece(new Square("h8"), new Rook(Piece.Color.BLACK));
		for (char row : new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'}) {
			board.placePiece(new Square(Character.toString(row) + "7"), new Pawn(Piece.Color.BLACK));
		}
	}
}
