package com.brasee.games.chess.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.brasee.games.lobby.MultipleClientGame;
import com.brasee.games.lobby.MultipleClientGameManager;

public class ChessMultipleClientUiController extends AbstractController {

	private MultipleClientGameManager gameManager;
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		
		MultipleClientGame game = MultipleClientGameHelper.retrieveGame(request.getParameter("gameId"), gameManager);
		if (game != null) {
			modelAndView = new ModelAndView("chessMultipleClient");
		}
		
		return modelAndView;
	}

	public void setGameManager(MultipleClientGameManager gameManager) {
		this.gameManager = gameManager;
	}
	
}
