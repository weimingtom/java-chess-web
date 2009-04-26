package com.brasee.chess.pieces;

import org.junit.Test;

import com.brasee.chess.Square;

import static org.junit.Assert.*;

public class PawnTest {

	@Test
	public void testCanMoveForwardOneSquare() {
		Piece pawn = new Pawn();
		Square currentSquare = new Square("a2");
		Square emptySquare = new Square("a3");
		assertTrue(pawn.canMove(currentSquare, emptySquare));
	}
	
	@Test
	public void testCanMoveForwardTwoSquaresIfThisIsFirstMove() {
		Piece pawn = new Pawn();
		Square currentSquare = new Square("a2");
		Square emptySquare = new Square("a4");
		assertTrue(pawn.canMove(currentSquare, emptySquare));
	}
	
	@Test
	public void testCannotMoveForwardThreeSquares() {
		Piece pawn = new Pawn();
		Square currentSquare = new Square("a2");
		Square emptySquare = new Square("a5");
		assertFalse(pawn.canMove(currentSquare, emptySquare));
	}
	
	@Test
	public void testCanAttackPieceForwardAndLeftOneSquare() {
		Piece pawn = new Pawn();
		Square currentSquare = new Square("b2");
		Square occupiedSquare = new Square("c3");
		assertTrue(pawn.canAttack(currentSquare, occupiedSquare));
	}
	
	@Test
	public void testCanAttackPieceForwardAndRightOneSquare() {
		Piece pawn = new Pawn();
		Square currentSquare = new Square("b2");
		Square occupiedSquare = new Square("a3");
		assertTrue(pawn.canAttack(currentSquare, occupiedSquare));
	}

}
