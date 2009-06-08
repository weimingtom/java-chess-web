package com.brasee.games.lobby.commands;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.brasee.games.chess.web.JsonView;
import com.brasee.games.lobby.ChatManager;

public abstract class AbstractLobbyCommandTest {

	@SuppressWarnings("unchecked")
	public String processRequest(MockHttpServletRequest request, Class expectedCommandClass) {
		String jsonStringResult = null;

		LobbyCommand lobbyCommand = LobbyCommandFactory.createCommand(request);
		assertTrue(lobbyCommand.getClass().equals(expectedCommandClass));

		JsonView view = lobbyCommand.processCommand(request, new ChatManager(100, 10));
		
		MockHttpServletResponse response = new MockHttpServletResponse();
		try {
			view.render(null, request, response);
			jsonStringResult = response.getContentAsString();
		}
		catch (Exception e) {
			fail("Should not have thrown an exception");
		}
		
		return jsonStringResult;
	}
	
}
