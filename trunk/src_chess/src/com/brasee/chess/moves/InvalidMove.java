package com.brasee.chess.moves;

import com.brasee.chess.Board;

public class InvalidMove extends AbstractMove {

	private InvalidMove() {}
	
	public static boolean canBeExecuted() {
		return false;
	}

	public static InvalidMove execute() {
		return new InvalidMove();
	}

	@Override
	public MoveType moveType() {
		return MoveType.INVALID;
	}

	@Override
	public void undo(Board board) {
		// nothing to undo! 		
	}
	

}
