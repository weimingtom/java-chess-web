package com.brasee.games.lobby.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

public class AddMessageCommandTest extends AbstractLobbyCommandTest {

	@Test
	public void testAddMessageCommandReturnsSuccessResultForValidInput() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("command", "add_message");
		request.addParameter("message", "User: test message");
		request.addParameter("message_index", "0");
		
		String expectedResult = "{\"result\":\"success\",\"message_index\":1,\"messages\":[\"User: test message\"]}";
		assertEquals(expectedResult, processRequest(request, AddMessageCommand.class));
	}
	
	@Test
	public void testAddMessageCommandReturnsFailureResultWhenNoMessageSupplied() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("command", "add_message");
		request.addParameter("message_index", "0");
		
		String expectedResult = "{\"result\":\"failure\"}";
		assertEquals(expectedResult, processRequest(request, AddMessageCommand.class));
	}

}
