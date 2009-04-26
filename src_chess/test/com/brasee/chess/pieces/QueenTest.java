package com.brasee.chess.pieces;

import org.junit.Before;
import org.junit.Test;

import com.brasee.chess.Square;

import static org.junit.Assert.*;

public class QueenTest {

	private Piece queen;
	
	@Before
	public void setUp() {
		queen = new Queen(Piece.Color.WHITE); 
	}
	
	@Test
	public void testCanMoveStraightUp() {
		Square currentSquare = new Square("d5");
		Square emptySquare = new Square("d8");
		assertTrue(queen.canMove(currentSquare, emptySquare));
	}
	
	@Test
	public void testCanMoveStraightDown() {
		Square currentSquare = new Square("d5");
		Square emptySquare = new Square("d1");
		assertTrue(queen.canMove(currentSquare, emptySquare));
	}
	
	@Test
	public void testCanMoveStraightLeft() {
		Square currentSquare = new Square("d5");
		Square emptySquare = new Square("a5");
		assertTrue(queen.canMove(currentSquare, emptySquare));
	}
	
	@Test
	public void testCanMoveStraightRight() {
		Square currentSquare = new Square("d5");
		Square emptySquare = new Square("h5");
		assertTrue(queen.canMove(currentSquare, emptySquare));
	}
	
	@Test
	public void testCanMoveDiagonallyUpAndLeft() {
		Square currentSquare = new Square("d5");
		Square emptySquare = new Square("b7");
		assertTrue(queen.canMove(currentSquare, emptySquare));
	}
	
	@Test
	public void testCanMoveDiagonallyUpAndRight() {
		Square currentSquare = new Square("d5");
		Square emptySquare = new Square("f7");
		assertTrue(queen.canMove(currentSquare, emptySquare));
	}
	
	@Test
	public void testCanMoveDiagonallyDownAndLeft() {
		Square currentSquare = new Square("d5");
		Square emptySquare = new Square("b3");
		assertTrue(queen.canMove(currentSquare, emptySquare));
	}
	
	@Test
	public void testCanMoveDiagonallyDownAndRight() {
		Square currentSquare = new Square("d5");
		Square emptySquare = new Square("f3");
		assertTrue(queen.canMove(currentSquare, emptySquare));
	}
	
	@Test
	public void testCanAttackStraightUp() {
		Square currentSquare = new Square("d5");
		Square occupiedSquare = new Square("d8");
		assertTrue(queen.canAttack(currentSquare, occupiedSquare));
	}
	
	@Test
	public void testCanAttackStraightDown() {
		Square currentSquare = new Square("d5");
		Square occupiedSquare = new Square("d1");
		assertTrue(queen.canAttack(currentSquare, occupiedSquare));
	}
	
	@Test
	public void testCanAttackStraightLeft() {
		Square currentSquare = new Square("d5");
		Square occupiedSquare = new Square("a5");
		assertTrue(queen.canAttack(currentSquare, occupiedSquare));
	}
	
	@Test
	public void testCanAttackStraightRight() {
		Square currentSquare = new Square("d5");
		Square occupiedSquare = new Square("h5");
		assertTrue(queen.canAttack(currentSquare, occupiedSquare));
	}
	
	@Test
	public void testCanAttackDiagonallyUpAndLeft() {
		Square currentSquare = new Square("d5");
		Square occupiedSquare = new Square("b7");
		assertTrue(queen.canAttack(currentSquare, occupiedSquare));
	}
	
	@Test
	public void testCanAttackDiagonallyUpAndRight() {
		Square currentSquare = new Square("d5");
		Square occupiedSquare = new Square("f7");
		assertTrue(queen.canAttack(currentSquare, occupiedSquare));
	}
	
	@Test
	public void testCanAttackDiagonallyDownAndLeft() {
		Square currentSquare = new Square("d5");
		Square occupiedSquare = new Square("b3");
		assertTrue(queen.canAttack(currentSquare, occupiedSquare));
	}
	
	@Test
	public void testCanAttackDiagonallyDownAndRight() {
		Square currentSquare = new Square("d5");
		Square occupiedSquare = new Square("f3");
		assertTrue(queen.canAttack(currentSquare, occupiedSquare));
	}
	
	@Test
	public void testCannotMoveToNonStraightOrDiagonalSquare() {
		Square currentSquare = new Square("a1");
		Square emptySquare = new Square("b8");
		assertFalse(queen.canMove(currentSquare, emptySquare));
	}
	
	@Test
	public void testCannotAttackNonStraightOrDiagonalSquare() {
		Square currentSquare = new Square("a1");
		Square occupiedSquare = new Square("b8");
		assertFalse(queen.canMove(currentSquare, occupiedSquare));
	}
	
	@Test
	public void testCannotMoveToSameSquare() {
		Square currentSquare = new Square("a1");
		Square emptySquare = new Square("a1");
		assertFalse(queen.canMove(currentSquare, emptySquare));
	}
	
	@Test
	public void testCannotAttackSameSquare() {
		Square currentSquare = new Square("a1");
		Square occupiedSquare = new Square("a1");
		assertFalse(queen.canAttack(currentSquare, occupiedSquare));
	}
}
