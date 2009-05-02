package com.brasee.chess;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.brasee.chess.pieces.Piece;

public class Board {

	Map<Square, Piece> pieceLocations = new HashMap<Square, Piece>();
	
	public void placePiece(Square square, Piece piece) {
		if (pieceLocations.get(square) == null) {
			pieceLocations.put(square, piece);
		}
		else {
			throw new SquareAlreadyOccupiedException(square);
		}
	}

	public boolean hasPieceOn(Square square) {
		return pieceOn(square) != null;
	}

	public Piece pieceOn(Square square) {
		return pieceLocations.get(square);
	}

	public void movePiece(Piece piece, Square startSquare, Square endSquare) {
		if (piece != null && piece.equals(pieceOn(startSquare))) {
			pieceLocations.remove(startSquare);
			pieceLocations.put(endSquare, piece);
		}
	}
	
	public boolean clearPathBetween(Square startSquare, Square endSquare) {
		boolean clearPathExists = false;
		
		if (startSquare.equals(endSquare)) {
			clearPathExists = false;
		}
		else if (startSquare.inSameFileAs(endSquare)) {
			clearPathExists = clearPathInFile(startSquare, endSquare);
		}
		else if (startSquare.inSameRankAs(endSquare)) {
			clearPathExists = clearPathInRank(startSquare, endSquare);
		}
		else if (startSquare.inDiagonalPathWith(endSquare)) {
			clearPathExists = clearPathInDiagonal(startSquare, endSquare);
		}
		
		return clearPathExists;
	}

	private boolean clearPathInRank(Square startSquare, Square endSquare) {
		boolean clearPathExists = true;
		
		Set<Square> squaresBetween = new HashSet<Square>();
		for (char file = 'a'; file <= 'h'; file++) {
			if (fileBetween(file, startSquare.file(), endSquare.file())) {
				squaresBetween.add(new Square(Character.toString(file) + Character.toString(startSquare.rank())));
			}
		}
		
		for (Square square : squaresBetween) {
			if (hasPieceOn(square)) {
				clearPathExists = false;
			}
		}
		
		return clearPathExists;
	}

	private boolean clearPathInFile(Square startSquare, Square endSquare) {
		boolean clearPathExists = true;
		
		Set<Square> squaresBetween = new HashSet<Square>();
		for (char rank = '1'; rank <= '8'; rank++) {
			if (rankBetween(rank, startSquare.rank(), endSquare.rank())) {
				squaresBetween.add(new Square(Character.toString(startSquare.file()) + Character.toString(rank)));
			}
		}
		
		for (Square square : squaresBetween) {
			if (hasPieceOn(square)) {
				clearPathExists = false;
			}
		}
		
		return clearPathExists;
	}

	private boolean clearPathInDiagonal(Square startSquare, Square endSquare) {
		boolean clearPathExists = true;
		
		int rankAdjustment = (startSquare.rank() < endSquare.rank() ? 1 : -1);
		int fileAdjustment = (startSquare.file() < endSquare.file() ? 1 : -1);
		char rank = (char) (startSquare.rank() + rankAdjustment);
		char file = (char) (startSquare.file() + fileAdjustment);
		
		while (rank != endSquare.rank()) {
			if (hasPieceOn(new Square(Character.toString(file) + Character.toString(rank)))) {
				clearPathExists = false;
			}
			rank = (char) (rank + rankAdjustment);
			file = (char) (file + fileAdjustment);
		}
		
		return clearPathExists;
	}
	
	private boolean rankBetween(char rank, char startRank, char endRank) {
		if (rank > startRank && rank < endRank) {
			return true;
		}
		else if (rank > endRank && rank < startRank) {
			return true;
		}
		else {
			return false;
		}
	}

	private boolean fileBetween(char file, char startFile, char endFile) {
		if (file > startFile && file < endFile) {
			return true;
		}
		else if (file > endFile && file < startFile) {
			return true;
		}
		else {
			return false;
		}
	}

}
