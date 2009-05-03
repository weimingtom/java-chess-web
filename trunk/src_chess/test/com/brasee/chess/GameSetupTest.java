package com.brasee.chess;

import org.junit.Before;
import org.junit.Test;

import com.brasee.chess.pieces.Bishop;
import com.brasee.chess.pieces.King;
import com.brasee.chess.pieces.Knight;
import com.brasee.chess.pieces.Pawn;
import com.brasee.chess.pieces.Piece;
import com.brasee.chess.pieces.Queen;
import com.brasee.chess.pieces.Rook;
import com.brasee.chess.pieces.Piece.Color;

import static org.junit.Assert.*;

public class GameSetupTest {

	private Game game;
	
	@Before
	public void setUp() {
		game = new Game();
	}
	
	@Test
	public void testInitializeSetsUpCorrectBoard() {
		game.initializeBoard();
		
		// test white setup
		Piece whiteLeftRook = game.board().pieceOn(new Square("a1"));
		assertTrue(whiteLeftRook != null && whiteLeftRook.color().equals(Color.WHITE) && whiteLeftRook instanceof Rook);
		Piece whiteLeftKnight = game.board().pieceOn(new Square("b1"));
		assertTrue(whiteLeftKnight != null && whiteLeftKnight.color().equals(Color.WHITE) && whiteLeftKnight instanceof Knight);
		Piece whiteLeftBishop = game.board().pieceOn(new Square("c1"));
		assertTrue(whiteLeftBishop != null && whiteLeftBishop.color().equals(Color.WHITE) && whiteLeftBishop instanceof Bishop);
		Piece whiteQueen = game.board().pieceOn(new Square("d1"));
		assertTrue(whiteQueen != null && whiteQueen.color().equals(Color.WHITE) && whiteQueen instanceof Queen);		
		Piece whiteKing = game.board().pieceOn(new Square("e1"));
		assertTrue(whiteKing != null && whiteKing.color().equals(Color.WHITE) && whiteKing instanceof King);
		Piece whiteRightBishop = game.board().pieceOn(new Square("f1"));
		assertTrue(whiteRightBishop != null && whiteRightBishop.color().equals(Color.WHITE) && whiteRightBishop instanceof Bishop);
		Piece whiteRightKnight = game.board().pieceOn(new Square("g1"));
		assertTrue(whiteRightKnight != null && whiteRightKnight.color().equals(Color.WHITE) && whiteRightKnight instanceof Knight);
		Piece whiteRightRook = game.board().pieceOn(new Square("h1"));
		assertTrue(whiteRightRook != null && whiteRightRook.color().equals(Color.WHITE) && whiteRightRook instanceof Rook);
		for (char row : new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'}) {
			Piece pawn = game.board().pieceOn(new Square(row + "2"));
			assertTrue(pawn != null && pawn.color().equals(Color.WHITE) && pawn instanceof Pawn);
		}
		
		// test black setup
		Piece blackLeftRook = game.board().pieceOn(new Square("a8"));
		assertTrue(blackLeftRook != null && blackLeftRook.color().equals(Color.BLACK) && blackLeftRook instanceof Rook);
		Piece blackLeftKnight = game.board().pieceOn(new Square("b8"));
		assertTrue(blackLeftKnight != null && blackLeftKnight.color().equals(Color.BLACK) && blackLeftKnight instanceof Knight);
		Piece blackLeftBishop = game.board().pieceOn(new Square("c8"));
		assertTrue(blackLeftBishop != null && blackLeftBishop.color().equals(Color.BLACK) && blackLeftBishop instanceof Bishop);
		Piece blackKing = game.board().pieceOn(new Square("d8"));
		assertTrue(blackKing != null && blackKing.color().equals(Color.BLACK) && blackKing instanceof King);
		Piece blackQueen = game.board().pieceOn(new Square("e8"));
		assertTrue(blackQueen != null && blackQueen.color().equals(Color.BLACK) && blackQueen instanceof Queen);		
		Piece blackRightBishop = game.board().pieceOn(new Square("f8"));
		assertTrue(blackRightBishop != null && blackRightBishop.color().equals(Color.BLACK) && blackRightBishop instanceof Bishop);
		Piece blackRightKnight = game.board().pieceOn(new Square("g8"));
		assertTrue(blackRightKnight != null && blackRightKnight.color().equals(Color.BLACK) && blackRightKnight instanceof Knight);
		Piece blackRightRook = game.board().pieceOn(new Square("h8"));
		assertTrue(blackRightRook != null && blackRightRook.color().equals(Color.BLACK) && blackRightRook instanceof Rook);
		for (char row : new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'}) {
			Piece pawn = game.board().pieceOn(new Square(row + "7"));
			assertTrue(pawn != null && pawn.color().equals(Color.BLACK) && pawn instanceof Pawn);
		}
	}

}
