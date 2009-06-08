package com.brasee.games.lobby.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import com.brasee.games.chess.web.JsonView;
import com.brasee.games.lobby.ChatManager;
import com.brasee.games.lobby.ChatResult;

public class AddMessageCommand implements LobbyCommand {

	@Override
	public JsonView processCommand(HttpServletRequest request, ChatManager chatManager) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		
		try {
			String message = request.getParameter("message");
			int messageIndex = Integer.parseInt(request.getParameter("message_index"));
			ChatResult<String> result = chatManager.addMessage(message, messageIndex);
			responseMap.put("result", "success");
			responseMap.put("message_index", new Integer(result.getNextMessageIndex()));
			responseMap.put("messages", result.getMessages());
		}
		catch (Exception e) {
			Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage());
			responseMap.put("result", "failure");
		}
		
		return new JsonView(responseMap);
	}

}
