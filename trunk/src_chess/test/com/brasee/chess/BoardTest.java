package com.brasee.chess;

import org.junit.Before;
import org.junit.Test;

import com.brasee.chess.pieces.Pawn;
import com.brasee.chess.pieces.Piece;

import static org.junit.Assert.*;

public class BoardTest {

	private Board board;
	
	@Before
	public void setUp() {
		board = new Board();
	}
	
	@Test
	public void testCanCheckForAPieceAtPosition() {
		Piece pawn = new Pawn(Piece.WHITE);
		Square square = new Square("a2");
		board.placePiece(square, pawn);
		assertTrue(board.isPieceOn(square));
	}
	
	@Test
	public void testCanCheckForNoPieceAtPosition() {
		Piece pawn = new Pawn(Piece.WHITE);
		Square pawnSquare = new Square("a2");
		Square emptySquare =  new Square("a3");
		board.placePiece(pawnSquare, pawn);
		assertFalse(board.isPieceOn(emptySquare));
	}
	
	@Test
	public void testCanCheckForWhichPieceIsAtPosition() {
		Piece pawn = new Pawn(Piece.WHITE);
		Square square = new Square("a2");
		board.placePiece(square, pawn);
		assertTrue(pawn.equals(board.pieceOn(square)));
	}
	
	@Test
	public void testThrowsExceptionIfPiecePlacedAtAnOccupiedPosition() {
		Piece pawn1 = new Pawn(Piece.WHITE);
		Piece pawn2 = new Pawn(Piece.WHITE);
		Square square = new Square("a2");
		board.placePiece(square, pawn1);
		try {
			board.placePiece(square, pawn2);
		}
		catch (SquareAlreadyOccupiedException e) { 
			return;
		}
		fail("Should throw a SquareAlreadyOccupiedException");
	}
	
	@Test
	public void testMoveRemovesAPieceFromASquare() {
		Piece pawn = new Pawn(Piece.WHITE);
		Square startSquare = new Square("a2");
		Square endSquare = new Square("a3");
		board.placePiece(startSquare, pawn);
		board.movePiece(pawn, startSquare, endSquare);
		assertFalse(board.isPieceOn(startSquare));
	}
	
	@Test
	public void testMoveOccupiesASquare() {
		Piece pawn = new Pawn(Piece.WHITE);
		Square startSquare = new Square("a2");
		Square endSquare = new Square("a3");
		board.placePiece(startSquare, pawn);
		board.movePiece(pawn, startSquare, endSquare);
		assertTrue(board.isPieceOn(endSquare));
	}
	
	@Test
	public void testMoveMovesAPieceToASquare() {
		Piece pawn = new Pawn(Piece.WHITE);
		Square startSquare = new Square("a2");
		Square endSquare = new Square("a3");
		board.placePiece(startSquare, pawn);
		board.movePiece(pawn, startSquare, endSquare);
		assertTrue(pawn.equals(board.pieceOn(endSquare)));
	}
	
}
