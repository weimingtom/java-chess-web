package com.brasee.chess;

public class SquareNotOccupiedException extends RuntimeException {

	private static final long serialVersionUID = -8166489509646771399L;
	
	public SquareNotOccupiedException(Square square) {
		super(square.toString() + " is not occupied");
	}

}
