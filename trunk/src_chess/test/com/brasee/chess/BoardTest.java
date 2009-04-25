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
	public void testBoardCanCheckForAPieceAtPosition() {
		Piece pawn = new Pawn();
		String square = "A2";
		board.placePiece(square, pawn);
		assertTrue(board.isPieceOn(square));
	}
	
	@Test
	public void testBoardCanCheckForNoPieceAtPosition() {
		Piece pawn = new Pawn();
		String pawnSquare = "A2";
		String emptySquare = "A3";
		board.placePiece(pawnSquare, pawn);
		assertFalse(board.isPieceOn(emptySquare));
	}
	
	@Test
	public void testBoardCanCheckForWhichPieceIsAtPosition() {
		Piece pawn = new Pawn();
		String square = "A2";
		board.placePiece(square, pawn);
		assertTrue(pawn.equals(board.pieceOn(square)));
	}
	
	@Test
	public void testBoardKeepsOnlyTheLastPiecePlacedAtAPosition() {
		Piece pawn1 = new Pawn();
		Piece pawn2 = new Pawn();
		String square = "A2";
		board.placePiece(square, pawn1);
		board.placePiece(square, pawn2);
		assertTrue(board.isPieceOn(square) && pawn2.equals(board.pieceOn(square)));
	}
	
	@Test
	public void testBoardMoveRemovesAPieceFromASquare() {
		Piece pawn = new Pawn();
		String startSquare = "A2";
		String endSquare = "A3";
		board.placePiece(startSquare, pawn);
		board.movePiece(pawn, startSquare, endSquare);
		assertFalse(board.isPieceOn(startSquare));
	}
	
	@Test
	public void testBoardMoveOccupiesASquare() {
		Piece pawn = new Pawn();
		String startSquare = "A2";
		String endSquare = "A3";
		board.placePiece(startSquare, pawn);
		board.movePiece(pawn, startSquare, endSquare);
		assertTrue(board.isPieceOn(endSquare));
	}
	
	@Test
	public void testBoardMoveMovesAPieceToASquare() {
		Piece pawn = new Pawn();
		String startSquare = "A2";
		String endSquare = "A3";
		board.placePiece(startSquare, pawn);
		board.movePiece(pawn, startSquare, endSquare);
		assertTrue(pawn.equals(board.pieceOn(endSquare)));
	}
	
}
