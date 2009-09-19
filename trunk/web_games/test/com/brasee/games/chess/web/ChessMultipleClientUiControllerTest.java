package com.brasee.games.chess.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.servlet.ModelAndView;

import com.brasee.games.GamesUser;
import com.brasee.games.lobby.GamePreviewImageGeneratorFactory;
import com.brasee.games.lobby.LobbyUiController;
import com.brasee.games.lobby.MultipleClientGameManager;

public class ChessMultipleClientUiControllerTest {

	@Test
	public void testControllerReturnsNullViewNameForMissingGameId() {
		ChessMultipleClientUiController controller = new ChessMultipleClientUiController();
		controller.setGameManager(new MultipleClientGameManager(3, new GamePreviewImageGeneratorFactory()));
		
		MockHttpServletRequest request = new MockHttpServletRequest("GET", null);
		MockHttpSession session = new MockHttpSession();
		request.setSession(session);
		GamesUser user = new GamesUser("User", "111111");
		session.setAttribute(LobbyUiController.GAMES_USER_SESSION_VARIABLE, user);
		
		ModelAndView modelAndView = null;
		try {
			modelAndView = controller.handleRequest(request, new MockHttpServletResponse());
		}
		catch (Exception e) {
			fail("Should not throw an exception");
		}
		assertNotNull(modelAndView);
		assertEquals(null, modelAndView.getViewName());
	}
	
	@Test
	public void testControllerReturnsNullViewNameForMissingUser() {
		ChessMultipleClientUiController controller = new ChessMultipleClientUiController();
		controller.setGameManager(new MultipleClientGameManager(3, new GamePreviewImageGeneratorFactory()));
		ModelAndView modelAndView = null;
		
		MockHttpServletRequest request = new MockHttpServletRequest("GET", null);
		request.addParameter("gameId", "1");
		
		try {
			modelAndView = controller.handleRequest(request, new MockHttpServletResponse());
		}
		catch (Exception e) {
			fail("Should not throw an exception");
		}
		assertNotNull(modelAndView);
		assertEquals(null, modelAndView.getViewName());
	}
	
	@Test
	public void testControllerReturnsCorrectViewForLoggedInUserAndGameId() {
		ChessMultipleClientUiController controller = new ChessMultipleClientUiController();
		controller.setGameManager(new MultipleClientGameManager(3, new GamePreviewImageGeneratorFactory()));
		ModelAndView modelAndView = null;

		MockHttpServletRequest request = new MockHttpServletRequest("GET", null);
		MockHttpSession session = new MockHttpSession();
		request.setSession(session);
		GamesUser user = new GamesUser("User", "111111");
		session.setAttribute(LobbyUiController.GAMES_USER_SESSION_VARIABLE, user);
		request.addParameter("gameId", "1");
		
		try {
			modelAndView = controller.handleRequest(request, new MockHttpServletResponse());
		}
		catch (Exception e) {
			fail("Should not throw an exception");
		}
		assertNotNull(modelAndView);
		assertEquals("chessMultipleClient", modelAndView.getViewName());
	}
	
}
