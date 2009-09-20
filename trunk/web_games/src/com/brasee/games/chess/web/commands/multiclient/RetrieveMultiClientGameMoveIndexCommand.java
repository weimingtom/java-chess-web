package com.brasee.games.chess.web.commands.multiclient;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.brasee.games.chess.web.JsonView;
import com.brasee.games.lobby.MultiClientGame;

public class RetrieveMultiClientGameMoveIndexCommand extends AbstractMultiClientChessCommand {

	@Override
	public JsonView processCommand(HttpServletRequest request, MultiClientGame game) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("move_index", Integer.toString(game.moves().size()));
		return new JsonView(responseMap);
	}

}
