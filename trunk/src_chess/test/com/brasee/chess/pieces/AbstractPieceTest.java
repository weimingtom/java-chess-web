package com.brasee.chess.pieces;

import org.junit.Test;

import com.brasee.chess.pieces.Piece.Color;

import static org.junit.Assert.*;

public class AbstractPieceTest {

	@Test
	public void testWhiteIsAValidColor() {
		try {
			new Pawn(Color.WHITE);
		}
		catch (InvalidPieceColorException e) {
			fail("No exception should be thrown, Color.WHITE is a valid color");
		}
	}
	
	@Test
	public void testBlackIsAValidColor() {
		try {
			new Pawn(Color.BLACK);
		}
		catch (InvalidPieceColorException e) {
			fail("No exception should be thrown, Color.BLACK is a valid color");
		}
	}
	
	@Test
	public void testExceptionThrownForInvalidColor() {
		try {
			new Pawn(null);
		}
		catch (InvalidPieceColorException e) { 
			return;
		}
		fail("InvalidPieceColorException should have been thrown");
	}
	
}
