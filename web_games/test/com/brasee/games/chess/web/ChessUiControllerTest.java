package com.brasee.games.chess.web;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

public class ChessUiControllerTest {

	@Test
	public void testControllerReturnsCorrectModelAndView() {
		ChessUiController controller = new ChessUiController();
		ModelAndView modelAndView = null;
		try {
			modelAndView = controller.handleRequest(new MockHttpServletRequest("GET", null), 
					new MockHttpServletResponse());
		}
		catch (Exception e) {
			fail("Should not throw an exception");
		}
		assertNotNull(modelAndView);
		assertTrue(modelAndView.getModel().isEmpty());
		assertEquals("chess", modelAndView.getViewName());
	}
}
