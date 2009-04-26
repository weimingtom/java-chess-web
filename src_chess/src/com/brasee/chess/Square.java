package com.brasee.chess;

public class Square {

	public static char MIN_RANK = '1';
	public static char MAX_RANK = '8';
	public static char MIN_FILE = 'a';
	public static char MAX_FILE = 'h';
	
	private static int FILE_INDEX = 0;
	private static int RANK_INDEX = 1;
	public char[] fileAndRank = new char[2];
	
		
	public Square(String squareString) {
		validateSquareString(squareString);
		fileAndRank[FILE_INDEX] = squareString.charAt(FILE_INDEX);
		fileAndRank[RANK_INDEX] = squareString.charAt(RANK_INDEX);
	}

	private void validateSquareString(String squareString) {
		if (squareString == null || squareString.length() != fileAndRank.length) {
			throw new InvalidSquareException("Square String must be length 2");
		}
		char squareFile = squareString.charAt(FILE_INDEX);
		char squareRank = squareString.charAt(RANK_INDEX);
		if (squareFile < MIN_FILE || squareFile > MAX_FILE) {
			throw new InvalidSquareException("Invalid Square file");
		}
		if (squareRank < MIN_RANK || squareRank > MAX_RANK) {
			throw new InvalidSquareException("Invalid Square rank");
		}
	}
	
	@Override
	public String toString() {
		return new String(fileAndRank);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Square)) {
			return false;
		}
		
		Square that = (Square)obj;
		if (this.toString().equals(that.toString())) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public int distanceBetweenRank(Square otherSquare) {
		return this.fileAndRank[RANK_INDEX] - otherSquare.fileAndRank[RANK_INDEX];
	}
	
	public int distanceBetweenFile(Square otherSquare) {
		return this.fileAndRank[FILE_INDEX] - otherSquare.fileAndRank[FILE_INDEX];
	}

}
