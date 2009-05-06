package com.brasee.chess;

import org.junit.Before;
import org.junit.Test;

import com.brasee.chess.Game.MoveType;
import com.brasee.chess.pieces.Bishop;
import com.brasee.chess.pieces.King;
import com.brasee.chess.pieces.Knight;
import com.brasee.chess.pieces.Pawn;
import com.brasee.chess.pieces.Piece;
import com.brasee.chess.pieces.Queen;
import com.brasee.chess.pieces.Rook;
import com.brasee.chess.pieces.Piece.Color;

import static org.junit.Assert.*; 

public class GameMoveTest {

	private Game game;
	
	@Before
	public void setUp() {
		this.game = new Game();
	}
	
	@Test
	public void testPawnCanMoveToValidSquare() {
		Square currentSquare = new Square("a2");
		Square emptySquare = new Square("a3");
		Piece pawn = new Pawn(Color.WHITE);
		assertMoveSucceeds(pawn, currentSquare, emptySquare);
	}
	
	@Test
	public void testRookCanMoveToValidSquare() {
		Square currentSquare = new Square("a2");
		Square emptySquare = new Square("a6");
		Piece rook = new Rook(Color.WHITE);
		assertMoveSucceeds(rook, currentSquare, emptySquare);
	}
	
	@Test
	public void testBishopCanMoveToValidSquare() {
		Square currentSquare = new Square("a2");
		Square emptySquare = new Square("d5");
		Piece bishop = new Bishop(Color.WHITE);
		assertMoveSucceeds(bishop, currentSquare, emptySquare);
	}
	
	@Test
	public void testQueenCanMoveToValidSquare() {
		Square currentSquare = new Square("a2");
		Square emptySquare = new Square("a6");
		Piece queen = new Queen(Color.WHITE);
		assertMoveSucceeds(queen, currentSquare, emptySquare);
	}
	
	@Test
	public void testKingCanMoveToValidSquare() {
		Square currentSquare = new Square("a2");
		Square emptySquare = new Square("a3");
		Piece king = new King(Color.WHITE);
		assertMoveSucceeds(king, currentSquare, emptySquare);
	}
	
	@Test
	public void testKnightCanMoveToValidSquare() {
		Square currentSquare = new Square("a2");
		Square emptySquare = new Square("b4");
		Piece knight = new Knight(Color.WHITE);
		assertMoveSucceeds(knight, currentSquare, emptySquare);
	}
	
	@Test
	public void testPawnCannotMoveToInvalidSquare() {
		Square currentSquare = new Square("a2");
		Square emptySquare = new Square("h1");
		Piece pawn = new Pawn(Color.WHITE);
		assertMoveFails(pawn, currentSquare, emptySquare);
	}
	
	@Test
	public void testRookCannotMoveToInvalidSquare() {
		Square currentSquare = new Square("a2");
		Square emptySquare = new Square("h1");
		Piece rook = new Rook(Color.WHITE);
		assertMoveFails(rook, currentSquare, emptySquare);
	}
	
	@Test
	public void testBishopCannotMoveToInvalidSquare() {
		Square currentSquare = new Square("a2");
		Square emptySquare = new Square("h1");
		Piece bishop = new Bishop(Color.WHITE);
		assertMoveFails(bishop, currentSquare, emptySquare);
	}
	
	@Test
	public void testQueenCannotMoveToInvalidSquare() {
		Square currentSquare = new Square("a2");
		Square emptySquare = new Square("h1");
		Piece queen = new Queen(Color.WHITE);
		assertMoveFails(queen, currentSquare, emptySquare);
	}
	
	@Test
	public void testKingCannotMoveToInvalidSquare() {
		Square currentSquare = new Square("a2");
		Square emptySquare = new Square("h1");
		Piece king = new King(Color.WHITE);
		assertMoveFails(king, currentSquare, emptySquare);
	}
	
	@Test
	public void testKnightCannotMoveToInvalidSquare() {
		Square currentSquare = new Square("a2");
		Square emptySquare = new Square("h1");
		Piece knight = new Knight(Color.WHITE);
		assertMoveFails(knight, currentSquare, emptySquare);
	}
	
	@Test
	public void testIsFirstMoveIsFalseAfterPieceMoves() {
		Square currentSquare = new Square("a2");
		Square emptySquare = new Square("a3");
		Piece pawn = new Pawn(Color.WHITE);
		game.board().placePiece(currentSquare, pawn);
		game.move(currentSquare, emptySquare);
		assertFalse(pawn.isFirstMove());
	}
	
	private void assertMoveSucceeds(Piece piece, Square currentSquare, Square emptySquare) {
		game.board().placePiece(currentSquare, piece);
		MoveType moveType = game.move(currentSquare, emptySquare);
		assertEquals(MoveType.NORMAL, moveType);
		assertTrue(!game.board().hasPieceOn(currentSquare));
		assertTrue(piece.equals(game.board().pieceOn(emptySquare)));
	}
	
	private void assertMoveFails(Piece piece, Square currentSquare, Square emptySquare) {
		game.board().placePiece(currentSquare, piece);
		MoveType moveType = game.move(currentSquare, emptySquare);
		assertEquals(MoveType.INVALID, moveType);
		assertTrue(piece.equals(game.board().pieceOn(currentSquare)));
		assertTrue(!game.board().hasPieceOn(emptySquare));
	}
}
