package com.brasee.games.chess.web.commands.singleplayer;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.brasee.chess.Game;
import com.brasee.games.chess.web.JsonView;

public class RetrieveGameCommand extends AbstractChessCommand {

	@Override
	public JsonView processCommand(HttpServletRequest request, Game game) {
		Map<String, Object> responseMap = createGameStateResponseMap(game);
		addFullGameInfoToResponse(game, responseMap);
		return new JsonView(responseMap);
	}

}
