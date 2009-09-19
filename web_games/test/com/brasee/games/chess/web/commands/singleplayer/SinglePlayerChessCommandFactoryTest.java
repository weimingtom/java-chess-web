package com.brasee.games.chess.web.commands.singleplayer;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import com.brasee.games.chess.web.commands.ChessCommand;
import com.brasee.games.chess.web.commands.InvalidCommand;
import com.brasee.games.chess.web.commands.singleplayer.MoveCommand;
import com.brasee.games.chess.web.commands.singleplayer.PromoteCommand;
import com.brasee.games.chess.web.commands.singleplayer.ResetGameCommand;
import com.brasee.games.chess.web.commands.singleplayer.RetrieveGameCommand;

import static org.junit.Assert.*;

public class SinglePlayerChessCommandFactoryTest {

	@Test
	public void testFactoryReturnsRetrieveGameCommand() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("command", "retrieve_game");
		ChessCommand command = SinglePlayerChessCommandFactory.createCommand(request);
		assertTrue(command instanceof RetrieveGameCommand);
	}
	
	@Test
	public void testFactoryReturnsResetGameCommand() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("command", "reset_game");
		ChessCommand command = SinglePlayerChessCommandFactory.createCommand(request);
		assertTrue(command instanceof ResetGameCommand);
	}
	
	@Test
	public void testFactoryReturnsMoveCommand() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("command", "move");
		ChessCommand command = SinglePlayerChessCommandFactory.createCommand(request);
		assertTrue(command instanceof MoveCommand);
	}
	
	@Test
	public void testFactoryReturnsPromoteCommand() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("command", "promote");
		ChessCommand command = SinglePlayerChessCommandFactory.createCommand(request);
		assertTrue(command instanceof PromoteCommand);
	}
	
	@Test
	public void testFactoryReturnsInvalidCommandForEmptyString() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("command", "");
		ChessCommand command = SinglePlayerChessCommandFactory.createCommand(request);
		assertTrue(command instanceof InvalidCommand);
	}
	
	@Test
	public void testFactoryReturnsInvalidCommandForNonsenseString() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("command", "nonsense!");
		ChessCommand command = SinglePlayerChessCommandFactory.createCommand(request);
		assertTrue(command instanceof InvalidCommand);
	}
	
	@Test
	public void testFactoryReturnsInvalidCommandWhenNoCommandInRequest() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		ChessCommand command = SinglePlayerChessCommandFactory.createCommand(request);
		assertTrue(command instanceof InvalidCommand);
	}
}
