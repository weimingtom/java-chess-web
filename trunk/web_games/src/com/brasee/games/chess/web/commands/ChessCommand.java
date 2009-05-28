package com.brasee.games.chess.web.commands;

import javax.servlet.http.HttpServletRequest;

import com.brasee.chess.Game;
import com.brasee.games.chess.web.JsonView;

public interface ChessCommand {

	public JsonView processCommand(HttpServletRequest request, Game game);
	
}
