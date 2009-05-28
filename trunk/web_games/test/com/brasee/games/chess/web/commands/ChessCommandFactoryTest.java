package com.brasee.games.chess.web.commands;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import static org.junit.Assert.*;

public class ChessCommandFactoryTest {

	@Test
	public void testFactoryReturnsRetrieveGameCommand() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("command", "retrieve_game");
		ChessCommand command = ChessCommandFactory.createCommand(request);
		assertTrue(command instanceof RetrieveGameCommand);
	}
	
	@Test
	public void testFactoryReturnsResetGameCommand() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("command", "reset_game");
		ChessCommand command = ChessCommandFactory.createCommand(request);
		assertTrue(command instanceof ResetGameCommand);
	}
	
	@Test
	public void testFactoryReturnsMoveCommand() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("command", "move");
		ChessCommand command = ChessCommandFactory.createCommand(request);
		assertTrue(command instanceof MoveCommand);
	}
	
	@Test
	public void testFactoryReturnsPromoteCommand() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("command", "promote");
		ChessCommand command = ChessCommandFactory.createCommand(request);
		assertTrue(command instanceof PromoteCommand);
	}
	
	@Test
	public void testFactoryReturnsInvalidCommandForEmptyString() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("command", "");
		ChessCommand command = ChessCommandFactory.createCommand(request);
		assertTrue(command instanceof InvalidCommand);
	}
	
	@Test
	public void testFactoryReturnsInvalidCommandForNonsenseString() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("command", "nonsense!");
		ChessCommand command = ChessCommandFactory.createCommand(request);
		assertTrue(command instanceof InvalidCommand);
	}
	
	@Test
	public void testFactoryReturnsInvalidCommandWhenNoCommandInRequest() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		ChessCommand command = ChessCommandFactory.createCommand(request);
		assertTrue(command instanceof InvalidCommand);
	}
}
