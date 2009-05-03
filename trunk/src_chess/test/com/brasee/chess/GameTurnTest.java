package com.brasee.chess;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*; 

public class GameTurnTest {

	private Game game;
	
	@Before
	public void setUp() {
		game = new Game();
	}
	
	@Test
	public void testWhitePlayerCanMoveFirst() {
		game.initializeBoard();
		// try to move white pawn
		assertTrue(Game.MoveType.NORMAL.equals(game.move(new Square("a2"), new Square("a3"))));
	}
	
	@Test
	public void testBlackPlayerCannotMoveFirst() {
		game.initializeBoard();
		// try to move black pawn
		assertTrue(Game.MoveType.INVALID.equals(game.move(new Square("a7"), new Square("a6"))));
	}
	
	@Test
	public void testPlayersCanMakeTwoAlternatingMoves() {
		game.initializeBoard();
		// move white pawn
		game.move(new Square("a2"), new Square("a3"));
		// try to move black pawn
		assertTrue(Game.MoveType.NORMAL.equals(game.move(new Square("a7"), new Square("a6"))));
	}
	
	@Test
	public void testPlayersCanMakeThreeAlternatingMoves() {
		game.initializeBoard();
		// move white pawn
		game.move(new Square("a2"), new Square("a3"));
		// move black pawn
		game.move(new Square("a7"), new Square("a6"));
		// try to move another white pawn
		assertTrue(Game.MoveType.NORMAL.equals(game.move(new Square("h2"), new Square("h3"))));		
	}
	
	@Test
	public void testPlayersCannotMakeSequentialMoves() {
		game.initializeBoard();
		// move white pawn
		game.move(new Square("a2"), new Square("a3"));
		// try to move white pawn again
		assertTrue(Game.MoveType.INVALID.equals(game.move(new Square("a3"), new Square("a4"))));
	}
		
}
