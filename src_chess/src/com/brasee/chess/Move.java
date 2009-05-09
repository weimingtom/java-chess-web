package com.brasee.chess;

import com.brasee.chess.pieces.Piece;

public class Move {

	private MoveType moveType;
	private Piece piece;
	private Square startSquare;
	private Square endSquare;
	
	public enum MoveType {
		INVALID, NORMAL, CAPTURE, CASTLING, EN_PASSANT
	}
	
	public Move(MoveType moveType) {
		this.moveType = moveType;
	}
	
	public Move(MoveType moveType, Piece piece, Square startSquare, Square endSquare) {
		this.moveType = moveType;
		this.piece = piece;
		this.startSquare = startSquare;
		this.endSquare = endSquare;
	}
	
	public Piece piece() {
		return piece;
	}

	public Square startSquare() {
		return startSquare;
	}

	public Square endSquare() {
		return endSquare;
	}
	
	public MoveType moveType() {
		return moveType;
	}

}


