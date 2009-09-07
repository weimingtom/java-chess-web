package com.brasee.games.chess.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class ChessMultipleClientUiController extends AbstractController {

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		String gameIdParameter = request.getParameter("gameId");
		if (gameIdParameter != null && StringUtils.isNumeric(gameIdParameter)) {
			
		}
		return new ModelAndView("chessMultipleClient");
	}

}
