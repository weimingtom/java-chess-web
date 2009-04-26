package com.brasee.chess.pieces;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.brasee.chess.Square;

public class RookTest {

	private Piece rook;
	
	@Before
	public void setUp() {
		rook = new Rook(Piece.Color.WHITE);
	}
	
	@Test
	public void testRookCanMoveLeft() {
		Square currentSquare = new Square("d5");
		Square emptySquare = new Square("a5");
		assertTrue(rook.canMove(currentSquare, emptySquare));	
	}
	
	@Test 
	public void testRookCanMoveRight() {
		Square currentSquare = new Square("d5");
		Square emptySquare = new Square("h5");
		assertTrue(rook.canMove(currentSquare, emptySquare));			
	}
	
	@Test
	public void testRookCanMoveUp() {
		Square currentSquare = new Square("d5");
		Square emptySquare = new Square("d8");
		assertTrue(rook.canMove(currentSquare, emptySquare));	
	}
	
	@Test
	public void testRookCanMoveDown() {
		Square currentSquare = new Square("d5");
		Square emptySquare = new Square("d1");
		assertTrue(rook.canMove(currentSquare, emptySquare));	
	}
	
	@Test
	public void testRookCannotMoveDiagonal() {
		Square currentSquare = new Square("d5");
		Square emptySquare = new Square("e6");
		assertFalse(rook.canMove(currentSquare, emptySquare));	
	}
	
	@Test
	public void testRookCanAttackLeft() {
		Square currentSquare = new Square("d5");
		Square emptySquare = new Square("a5");
		assertTrue(rook.canAttack(currentSquare, emptySquare));	
	}
	
	@Test 
	public void testRookCanAttackRight() {
		Square currentSquare = new Square("d5");
		Square emptySquare = new Square("h5");
		assertTrue(rook.canAttack(currentSquare, emptySquare));			
	}
	
	@Test
	public void testRookCanAttackUp() {
		Square currentSquare = new Square("d5");
		Square emptySquare = new Square("d8");
		assertTrue(rook.canAttack(currentSquare, emptySquare));	
	}
	
	@Test
	public void testRookCanAttackDown() {
		Square currentSquare = new Square("d5");
		Square emptySquare = new Square("d1");
		assertTrue(rook.canAttack(currentSquare, emptySquare));	
	}
	
	@Test
	public void testRookCannotAttackDiagonal() {
		Square currentSquare = new Square("d5");
		Square emptySquare = new Square("e6");
		assertFalse(rook.canAttack(currentSquare, emptySquare));	
	}
	
	@Test
	public void testCannotMoveToSameSquare() {
		Square currentSquare = new Square("a1");
		Square emptySquare = new Square("a1");
		assertFalse(rook.canMove(currentSquare, emptySquare));
	}
	
	@Test
	public void testCannotAttackSameSquare() {
		Square currentSquare = new Square("a1");
		Square occupiedSquare = new Square("a1");
		assertFalse(rook.canAttack(currentSquare, occupiedSquare));
	}
	
}
