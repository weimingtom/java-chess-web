package com.brasee.chess.pieces;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.brasee.chess.Square;

public class BishopTest {

	private Piece bishop;
	
	@Before
	public void setUp() {
		bishop = new Bishop(Piece.Color.WHITE);
	}
	
	@Test
	public void testCanMoveDiagonallyUpAndLeft() {
		Square currentSquare = new Square("d5");
		Square emptySquare = new Square("c6");
		assertTrue(bishop.canMove(currentSquare, emptySquare));
	}
	
	@Test
	public void testCanMoveDiagonallyUpAndRight() {
		Square currentSquare = new Square("d5");
		Square emptySquare = new Square("e6");
		assertTrue(bishop.canMove(currentSquare, emptySquare));
	}
	
	@Test
	public void testCanMoveDiagonallyDownAndLeft() {
		Square currentSquare = new Square("d5");
		Square emptySquare = new Square("c4");
		assertTrue(bishop.canMove(currentSquare, emptySquare));
	}
	
	@Test
	public void testCanMoveDiagonallyDownAndRight() {
		Square currentSquare = new Square("d5");
		Square emptySquare = new Square("e4");
		assertTrue(bishop.canMove(currentSquare, emptySquare));
	}
	
	@Test
	public void testCanAttackDiagonallyUpAndLeft() {
		Square currentSquare = new Square("d5");
		Square occupiedSquare = new Square("c6");
		assertTrue(bishop.canAttack(currentSquare, occupiedSquare));
	}
	
	@Test
	public void testCanAttackDiagonallyUpAndRight() {
		Square currentSquare = new Square("d5");
		Square occupiedSquare = new Square("e6");
		assertTrue(bishop.canAttack(currentSquare, occupiedSquare));
	}
	
	@Test
	public void testCanAttackDiagonallyDownAndLeft() {
		Square currentSquare = new Square("d5");
		Square occupiedSquare = new Square("c4");
		assertTrue(bishop.canAttack(currentSquare, occupiedSquare));
	}
	
	@Test
	public void testCanAttackDiagonallyDownAndRight() {
		Square currentSquare = new Square("d5");
		Square occupiedSquare = new Square("e4");
		assertTrue(bishop.canAttack(currentSquare, occupiedSquare));
	}
	
	@Test
	public void testCannotMoveHorizontally() {
		Square currentSquare = new Square("a1");
		Square emptySquare = new Square("b1");
		assertFalse(bishop.canMove(currentSquare, emptySquare));		
	}
	
	@Test
	public void testCannotMoveVertically() {
		Square currentSquare = new Square("a1");
		Square emptySquare = new Square("a2");
		assertFalse(bishop.canMove(currentSquare, emptySquare));		
	}
	
	@Test
	public void testCannotMoveToSameSquare() {
		Square currentSquare = new Square("a1");
		Square emptySquare = new Square("a1");
		assertFalse(bishop.canMove(currentSquare, emptySquare));
	}
	
	@Test
	public void testCannotAttackSameSquare() {
		Square currentSquare = new Square("a1");
		Square occupiedSquare = new Square("a1");
		assertFalse(bishop.canAttack(currentSquare, occupiedSquare));
	}
}
