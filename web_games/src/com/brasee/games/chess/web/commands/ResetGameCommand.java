package com.brasee.games.chess.web.commands;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.brasee.chess.Game;
import com.brasee.games.chess.web.ChessJsonController;
import com.brasee.games.chess.web.JsonView;

public class ResetGameCommand extends AbstractChessCommand {

	@Override
	public JsonView processCommand(HttpServletRequest request, Game game) {
		Game newGame = resetGame(request);
		Map<String, Object> responseMap = createGameStateResponseMap(newGame);
		addFullGameInfoToResponse(newGame, responseMap);
		return new JsonView(responseMap);
	}

	private Game resetGame(HttpServletRequest request) {
		request.getSession().removeAttribute(ChessJsonController.CHESS_GAME_SESSION_VARIABLE);
		
		Game newGame = new Game();
		newGame.initializeBoard();
		request.getSession().setAttribute(ChessJsonController.CHESS_GAME_SESSION_VARIABLE, newGame);
		return newGame;
	}

}
