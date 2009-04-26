package com.brasee.chess;

import org.junit.Test;
import static org.junit.Assert.*;

public class SquareTest {

	@Test
	public void testCreateAValidBottomLeftSquare() {
		try {
			new Square("a1");
		}
		catch (Exception e) {
			fail("No Exception should be thrown, a1 is a valid square");
		}
	}
	
	@Test
	public void testCreateAValidTopRightSquare() {
		try {
			new Square("h8");
		}
		catch (Exception e) {
			fail("No Exception should be thrown, h8 is a valid square");
		}
	}
	
	@Test
	public void testThrowExceptionForNullSquareString() {
		try {
			new Square(null);
		}
		catch (InvalidSquareException e) {
			return;
		}
		fail("Should have thrown InvalidSquareException");
	}
	
	@Test
	public void testThrowExceptionForTooShortSquareString() {
		try {
			new Square("a");
		}
		catch (InvalidSquareException e) {
			return;
		}
		fail("Should have thrown InvalidSquareException");
	}
	
	@Test
	public void testThrowExceptionForTooLongSquareString() {
		try {
			new Square("a1 ");
		}
		catch (InvalidSquareException e) {
			return;
		}
		fail("Should have thrown InvalidSquareException");
	}
	
	@Test
	public void testThrowExceptionForInvalidFile() {
		try {
			new Square("i1");
		}
		catch (InvalidSquareException e) {
			return;
		}
		fail("Should have thrown InvalidSquareException");
	}
	
	@Test
	public void testThrowExceptionForInvalidRank() {
		try {
			new Square("a9");
		}
		catch (InvalidSquareException e) {
			return;
		}
		fail("Should have thrown InvalidSquareException");
	}
	
	@Test
	public void testSquareNotEqualToNull() {
		assertFalse(new Square("a1").equals(null));
	}
	
	@Test
	public void testSquareNotEqualToAString() {
		assertFalse(new Square("a1").equals("a1"));
	}
	
	@Test
	public void testSquareNotEqualToADifferentSquare() {
		assertFalse(new Square("a1").equals(new Square("a2")));
	}
	
	@Test
	public void testSquareEqualToTheSameSquare() {
		assertTrue(new Square("a1").equals(new Square("a1")));
	}
	
}
