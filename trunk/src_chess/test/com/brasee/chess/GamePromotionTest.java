package com.brasee.chess;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.brasee.chess.moves.Move;
import com.brasee.chess.moves.StartPromotionMove;
import com.brasee.chess.moves.Move.MoveType;
import com.brasee.chess.pieces.King;
import com.brasee.chess.pieces.Pawn;
import com.brasee.chess.pieces.Piece;
import com.brasee.chess.pieces.Queen;
import com.brasee.chess.pieces.Rook;
import com.brasee.chess.pieces.Piece.Color;
import com.brasee.chess.pieces.Piece.PieceType;

public class GamePromotionTest {

	private Game game;

	@Before
	public void setUp() {
		game = new Game();
	}
	
	@Test
	public void testPromotionStartedWhenPawnMovesToPromotionSquare() {
		Piece whitePawn = new Pawn(Color.WHITE);
		Piece blackKing = new King(Color.BLACK);
		game.board().placePiece(new Square("a7"), whitePawn);
		game.board().placePiece(new Square("a1"), blackKing);
		
		Move move = game.move(new Square("a7"), new Square("a8"));
		
		assertEquals(MoveType.START_PROMOTION, move.moveType());
		assertEquals(Color.WHITE, game.playersTurn());
		assertSame(whitePawn, game.board().pieceOn(new Square("a8")));
		assertFalse(game.board().hasPieceOn(new Square("a7")));
		assertEquals(1, game.moves().size());
		assertTrue(game.moves().contains(move));
	}

	@Test
	public void testPromotionStartedWhenPawnCapturesToPromotionSquare() {
		Piece whitePawn = new Pawn(Color.WHITE);
		Piece blackRook = new Rook(Color.BLACK);
		game.board().placePiece(new Square("a7"), whitePawn);
		game.board().placePiece(new Square("b8"), blackRook);
		
		Move move = game.move(new Square("a7"), new Square("b8"));
		
		assertEquals(MoveType.START_PROMOTION, move.moveType());
		assertEquals(Color.WHITE, game.playersTurn());
		assertSame(whitePawn, game.board().pieceOn(new Square("b8")));
		assertFalse(game.board().hasPieceOn(new Square("a7")));
		assertSame(blackRook, ((StartPromotionMove)move).opposingPiece());
		assertEquals(1, game.capturedPieces(Color.BLACK).size());
		assertEquals(1, game.moves().size());
		assertTrue(game.moves().contains(move));
	}

	
	@Test
	public void testPawnCannotPerformPromotionMoveWhenBlocked() {
		Piece whitePawn = new Pawn(Color.WHITE);
		Piece blackKing = new King(Color.BLACK);
		game.board().placePiece(new Square("a7"), whitePawn);
		game.board().placePiece(new Square("a8"), blackKing);
		
		Move move = game.move(new Square("a7"), new Square("a8"));
		
		assertEquals(MoveType.INVALID, move.moveType());
		assertEquals(Color.WHITE, game.playersTurn());
		assertSame(whitePawn, game.board().pieceOn(new Square("a7")));
		assertSame(blackKing, game.board().pieceOn(new Square("a8")));
		assertEquals(0, game.moves().size());
	}
	
	@Test
	public void testAnotherMoveCannotBePlayedWhenPromotionIsStarted() {
		Piece whitePawn = new Pawn(Color.WHITE);
		Piece whiteKing = new King(Color.WHITE);
		Piece blackKing = new King(Color.BLACK);
		game.board().placePiece(new Square("a7"), whitePawn);
		game.board().placePiece(new Square("d5"), whiteKing);
		game.board().placePiece(new Square("a1"), blackKing);
		
		game.move(new Square("a7"), new Square("a8"));
		Move move = game.move(new Square("d5"), new Square("d6"));
		assertEquals(MoveType.INVALID, move.moveType());
	}
	
	@Test
	public void testCompletedPromotionChangesTurn() {
		Piece whitePawn = new Pawn(Color.WHITE);
		Piece blackKing = new King(Color.BLACK);
		game.board().placePiece(new Square("a7"), whitePawn);
		game.board().placePiece(new Square("a1"), blackKing);
		
		game.move(new Square("a7"), new Square("a8"));
		game.promote(PieceType.QUEEN, new Square("a8"));
		assertEquals(Color.BLACK, game.playersTurn());
	}
	
	@Test
	public void testCompletedPromotionReturnsPromotionMove() {
		Piece whitePawn = new Pawn(Color.WHITE);
		Piece blackKing = new King(Color.BLACK);
		game.board().placePiece(new Square("a7"), whitePawn);
		game.board().placePiece(new Square("a1"), blackKing);
		
		game.move(new Square("a7"), new Square("a8"));
		Move move = game.promote(PieceType.QUEEN, new Square("a8"));
		assertEquals(MoveType.PROMOTION, move.moveType());
	}
	
	@Test
	public void testCompletedPromotionAddsPieceToBoard() {
		Piece whitePawn = new Pawn(Color.WHITE);
		Piece blackKing = new King(Color.BLACK);
		game.board().placePiece(new Square("a7"), whitePawn);
		game.board().placePiece(new Square("a1"), blackKing);
		
		game.move(new Square("a7"), new Square("a8"));
		game.promote(PieceType.QUEEN, new Square("a8"));
		assertEquals(PieceType.QUEEN, game.board().pieceOn(new Square("a8")).pieceType());
	}
}
