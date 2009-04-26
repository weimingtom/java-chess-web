package com.brasee.chess.pieces;

import org.junit.Before;
import org.junit.Test;

import com.brasee.chess.Square;

import static org.junit.Assert.*;

public class PawnTest {

	private Piece whitePawn;
	private Piece blackPawn;
	
	@Before
	public void setUp() {
		whitePawn = new Pawn(Piece.WHITE);
		blackPawn = new Pawn(Piece.BLACK);
	}
	@Test
	public void testWhitePawnCanMoveUpOneSquare() {
		Square currentSquare = new Square("a2");
		Square emptySquare = new Square("a3");
		assertTrue(whitePawn.canMove(currentSquare, emptySquare));
	}
	
	@Test
	public void testWhitePawnCannotMoveDown() {
		Square currentSquare = new Square("a7");
		Square emptySquare = new Square("a6");
		assertFalse(whitePawn.canMove(currentSquare, emptySquare));
	}
	
	@Test
	public void testWhitePawnCanMoveUpTwoSquaresIfThisIsFirstMove() {
		Square currentSquare = new Square("a2");
		Square emptySquare = new Square("a4");
		assertTrue(whitePawn.canMove(currentSquare, emptySquare));
	}
	
	@Test
	public void testWhitePawnCannotMoveUpThreeSquares() {
		Square currentSquare = new Square("a2");
		Square emptySquare = new Square("a5");
		assertFalse(whitePawn.canMove(currentSquare, emptySquare));
	}
	
	@Test
	public void testWhitePawnCanAttackPieceUpAndLeftOneSquare() {
		Square currentSquare = new Square("b2");
		Square occupiedSquare = new Square("c3");
		assertTrue(whitePawn.canAttack(currentSquare, occupiedSquare));
	}
	
	@Test
	public void testWhitePawnCanAttackPieceUpAndRightOneSquare() {
		Square currentSquare = new Square("b2");
		Square occupiedSquare = new Square("a3");
		assertTrue(whitePawn.canAttack(currentSquare, occupiedSquare));
	}
	
	@Test
	public void testBlackPawnCanMoveDownOneSquare() {
		Square currentSquare = new Square("a7");
		Square emptySquare = new Square("a6");
		assertTrue(blackPawn.canMove(currentSquare, emptySquare));
	}
	
	@Test
	public void testBlackPawnCannotMoveUp() {
		Square currentSquare = new Square("a7");
		Square emptySquare = new Square("a8");
		assertFalse(blackPawn.canMove(currentSquare, emptySquare));
	}
	
	@Test
	public void testBlackPawnCanMoveDownTwoSquaresIfThisIsFirstMove() {
		Square currentSquare = new Square("a7");
		Square emptySquare = new Square("a5");
		assertTrue(blackPawn.canMove(currentSquare, emptySquare));
	}
	
	@Test 
	public void testBlackPawnCannotMoveDownThreeSquares() {
		Square currentSquare = new Square("a7");
		Square emptySquare = new Square("a4");
		assertFalse(blackPawn.canMove(currentSquare, emptySquare));		
	}
	
	@Test
	public void testBlackPawnCanAttackDownAndLeftOneSquare() {
		Square currentSquare = new Square("b7");
		Square occupiedSquare = new Square("a6");
		assertTrue(blackPawn.canAttack(currentSquare, occupiedSquare));
	}
	
	@Test
	public void testBlackPawnCanAttackDownAndRightOneSquare() {
		Square currentSquare = new Square("b7");
		Square occupiedSquare = new Square("c6");
		assertTrue(blackPawn.canAttack(currentSquare, occupiedSquare));
	}
	
	@Test
	public void testPawnCannotMoveTwoSquaresTwice() {
		Square currentSquare = new Square("a4");
		Square emptySquare = new Square("a6");
		whitePawn.updateHasMoved();
		assertFalse(whitePawn.canMove(currentSquare, emptySquare));
	}
	
	@Test
	public void testPawnCannotMoveSideways() {
		Square currentSquare = new Square("a2");
		Square emptySquare = new Square("b2");
		assertFalse(whitePawn.canMove(currentSquare, emptySquare));		
	}
	
	@Test
	public void testPawnCannotMoveDiagonally() {
		Square currentSquare = new Square("a2");
		Square emptySquare = new Square("b3");
		assertFalse(whitePawn.canMove(currentSquare, emptySquare));		
	}
	
	@Test
	public void testPawnCannotAttackForward() {
		Square currentSquare = new Square("a2");
		Square occupiedSquare = new Square("a3");
		assertFalse(whitePawn.canAttack(currentSquare, occupiedSquare));		
	}

	@Test
	public void testPawnCannotAttackSideways() {
		Square currentSquare = new Square("a2");
		Square occupiedSquare = new Square("b2");
		assertFalse(whitePawn.canAttack(currentSquare, occupiedSquare));		
	}

}
