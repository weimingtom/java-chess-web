package com.brasee.games.lobby.commands;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import static org.junit.Assert.*;

public class RetrieveMessageIndexCommandTest extends AbstractLobbyCommandTest {

	@Test
	public void testRetrieveMessageIndexCommandReturnsSuccessResultForValidInput() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("command", "retrieve_message_index");
		String expectedResult = "{\"result\":\"success\",\"message_index\":0}";
		assertEquals(expectedResult, processRequest(request, RetrieveMessageIndexCommand.class));
	}
	
}
