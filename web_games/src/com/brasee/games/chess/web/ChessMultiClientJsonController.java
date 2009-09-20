package com.brasee.games.chess.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.brasee.chess.Square;
import com.brasee.games.lobby.MultiClientGame;
import com.brasee.games.lobby.MultiClientGameManager;

public class ChessMultiClientJsonController extends AbstractController {

	private MultiClientGameManager gameManager;
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		MultiClientGame game = gameManager.getGame(1);
		game.move(new Square("a2"), new Square("a4"), null);
		return modelAndView;
	}

	public void setGameManager(MultiClientGameManager gameManager) {
		this.gameManager = gameManager;
	}
	
}
