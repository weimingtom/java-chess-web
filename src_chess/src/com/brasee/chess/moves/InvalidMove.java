package com.brasee.chess.moves;

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

}
