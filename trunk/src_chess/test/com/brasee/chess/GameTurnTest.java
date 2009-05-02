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
	public void testPlayersCanMakeAlternatingMoves() {
		game.initializeBoard();
		// move white pawn
		game.move(new Square("a2"), new Square("a3"));
		// try to move black pawn
		assertTrue(Game.MoveType.NORMAL.equals(game.move(new Square("a7"), new Square("a6"))));		
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
