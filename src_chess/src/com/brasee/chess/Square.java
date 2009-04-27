package com.brasee.chess;

public class Square {

	private static char MIN_RANK = '1';
	private static char MAX_RANK = '8';
	private static char MIN_FILE = 'a';
	private static char MAX_FILE = 'h';
	
	private static int FILE_INDEX = 0;
	private static int RANK_INDEX = 1;
	
	public char[] fileAndRank = new char[2];
	
		
	public Square(String squareString) {
		throwExceptionForInvalidSquareString(squareString);
		fileAndRank[FILE_INDEX] = squareString.charAt(FILE_INDEX);
		fileAndRank[RANK_INDEX] = squareString.charAt(RANK_INDEX);
	}

	public int distanceBetweenRank(Square otherSquare) {
		return this.fileAndRank[RANK_INDEX] - otherSquare.fileAndRank[RANK_INDEX];
	}
	
	public int distanceBetweenFile(Square otherSquare) {
		return this.fileAndRank[FILE_INDEX] - otherSquare.fileAndRank[FILE_INDEX];
	}

	public boolean inSameRankAs(Square otherSquare) {
		return distanceBetweenRank(otherSquare) == 0;
	}
	
	public boolean inSameFileAs(Square otherSquare) {
		return distanceBetweenFile(otherSquare) == 0;
	}
	
	public boolean inDiagonalPathWith(Square otherSquare) {
		return Math.abs(distanceBetweenFile(otherSquare)) == 
			   Math.abs(distanceBetweenRank(otherSquare));
	}
	
	private void throwExceptionForInvalidSquareString(String squareString) {
		if (isNullOrIncorrectLength(squareString)) {
			throw new InvalidSquareException("Square String must be length 2");
		}
		else if (isInvalidFile(squareString)) {
			throw new InvalidSquareException("Invalid Square file");
		}
		else if (isInvalidRank(squareString)) {
			throw new InvalidSquareException("Invalid Square rank");
		}
	}
	
	private boolean isInvalidRank(String squareString) {
		return (squareString.charAt(RANK_INDEX)	< MIN_RANK || 
				squareString.charAt(RANK_INDEX) > MAX_RANK);
	}

	private boolean isInvalidFile(String squareString) {
		return (squareString.charAt(FILE_INDEX) < MIN_FILE || 
				squareString.charAt(FILE_INDEX) > MAX_FILE);
	}

	private boolean isNullOrIncorrectLength(String squareString) {
		return (squareString == null || squareString.length() != fileAndRank.length);
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
	
	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}
	
}
