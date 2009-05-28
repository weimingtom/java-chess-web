package com.brasee.games.chess.web.commands;

import javax.servlet.http.HttpServletRequest;

import com.brasee.chess.Game;
import com.brasee.games.chess.web.JsonView;

public class InvalidCommand implements ChessCommand {

	@Override
	public JsonView processCommand(HttpServletRequest request, Game game) {
		// TODO Auto-generated method stub
		return null;
	}

}
