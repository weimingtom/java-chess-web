package com.brasee.games.lobby;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

public class LobbyUiControllerTest {
	
	@Test
	public void testControllerReturnsCorrectModelAndView() {
		LobbyUiController controller = new LobbyUiController();
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
		assertEquals("lobby", modelAndView.getViewName());
	}
	
}
