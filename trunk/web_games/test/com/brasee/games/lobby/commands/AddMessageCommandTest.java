package com.brasee.games.lobby.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;

import com.brasee.games.GamesUser;
import com.brasee.games.lobby.LobbyUiController;

public class AddMessageCommandTest extends AbstractLobbyCommandTest {

	private GamesUser user;
	private MockHttpServletRequest request;
	private MockHttpSession session;
	
	@Before
	public void setUp() {
		user = new GamesUser();
		user.setName("User");
		
		request = new MockHttpServletRequest();
		session = new MockHttpSession();
		session.setAttribute(LobbyUiController.GAMES_USER_SESSION_VARIABLE, user);
		request.setSession(session);
	}
	
	@Test
	public void testAddMessageCommandReturnsSuccessResultForValidInput() {
		request.addParameter("command", "add_message");
		request.addParameter("message", "test message");
		request.addParameter("message_index", "0");
		
		String expectedResult = "{\"result\":\"success\",\"message_index\":1,\"messages\":[\"User: test message\"]}";
		assertEquals(expectedResult, processRequest(request, AddMessageCommand.class));
	}
	
	@Test
	public void testAddMessageCommandReturnsSuccessResultWhenNoMessageSupplied() {
		request.addParameter("command", "add_message");
		request.addParameter("message_index", "0");
		
		String expectedResult = "{\"result\":\"success\",\"message_index\":0,\"messages\":[]}";
		assertEquals(expectedResult, processRequest(request, AddMessageCommand.class));
	}

}
