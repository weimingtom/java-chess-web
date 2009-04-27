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
	
	public Game() {	}
	
	public void initializeBoard() {
		board = new Board();
		placeWhitePieces();
		placeBlackPieces();
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

	public Board board() {
		return board;
	}
	
	
}
