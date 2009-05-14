package com.brasee.chess;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.brasee.chess.moves.Move;
import com.brasee.chess.moves.StartPromotionMove;
import com.brasee.chess.moves.Move.MoveType;
import com.brasee.chess.pieces.King;
import com.brasee.chess.pieces.Pawn;
import com.brasee.chess.pieces.Piece;
import com.brasee.chess.pieces.Rook;
import com.brasee.chess.pieces.Piece.Color;

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
	}
}
