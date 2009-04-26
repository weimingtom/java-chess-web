package com.brasee.chess.pieces;

import org.junit.Test;
import static org.junit.Assert.*;

public class AbstractPieceTest {

	@Test
	public void testWhiteIsAValidColor() {
		try {
			new Pawn(Piece.WHITE);
		}
		catch (InvalidPieceColorException e) {
			fail("No exception should be thrown, Piece.WHITE is a valid color");
		}
	}
	
	@Test
	public void testBlackIsAValidColor() {
		try {
			new Pawn(Piece.BLACK);
		}
		catch (InvalidPieceColorException e) {
			fail("No exception should be thrown, Piece.BLACK is a valid color");
		}
	}
	
	@Test
	public void testExceptionThrownForInvalidColor() {
		try {
			new Pawn(2);
		}
		catch (InvalidPieceColorException e) { 
			return;
		}
		fail("InvalidPieceColorException should have been thrown");
	}
	
}
