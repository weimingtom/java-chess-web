package com.brasee.games.lobby.commands;

import javax.servlet.http.HttpServletRequest;

import com.brasee.games.chess.web.JsonView;
import com.brasee.games.lobby.ChatManager;

public interface LobbyCommand {

	public JsonView processCommand(HttpServletRequest request, ChatManager chatManager);
	
}
