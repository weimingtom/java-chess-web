package com.brasee.games.chess.web.commands;

import static org.junit.Assert.assertNotSame;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;

import com.brasee.chess.Game;
import com.brasee.games.chess.web.ChessSingleClientJsonController;

public class ResetGameCommandTest {

	@Test
	public void testResetGameCreatesNewGame() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpSession session = new MockHttpSession();
		Game existingGame = new Game();
		session.setAttribute(ChessSingleClientJsonController.CHESS_SINGLE_CLIENT_GAME_SESSION_VARIABLE, existingGame);
		request.setSession(session);
		request.addParameter("command", "reset_game");
		
		ChessCommand command = ChessCommandFactory.createCommand(request);
		command.processCommand(request, existingGame);
		assertNotSame(existingGame, session.getAttribute(ChessSingleClientJsonController.CHESS_SINGLE_CLIENT_GAME_SESSION_VARIABLE));
	}
	
}
