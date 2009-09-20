package com.brasee.games.chess.web.commands.multiclient;

import javax.servlet.http.HttpServletRequest;

public class MultiClientChessCommandFactory {

	public static MultiClientChessCommand createCommand(HttpServletRequest request) {
		MultiClientChessCommand chessCommand = new InvalidMultiClientCommand();
		
		String commandName = request.getParameter("command");
		
		if ("retrieve_game".equals(commandName)) {
			chessCommand = new RetrieveMultiClientGameCommand();
		}
		else if ("retrieve_game_index".equals(commandName)) {
			chessCommand = new RetrieveMultiClientGameMoveIndexCommand();
		}
		else if ("reset_game".equals(commandName)) {
			chessCommand = new ResetMultiClientGameCommand();
		}
		else if ("move".equals(commandName)) {
			chessCommand = new MoveMultiClientCommand();
		}
		else if ("promote".equals(commandName)) {
			chessCommand = new PromoteMultiClientCommand();
		}

		return chessCommand;
	}
	
}
