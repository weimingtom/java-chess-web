package com.brasee.games.chess.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.brasee.chess.Square;
import com.brasee.games.lobby.MultipleClientGame;
import com.brasee.games.lobby.MultipleClientGameManager;

public class ChessMultipleClientJsonController extends AbstractController {

	private MultipleClientGameManager gameManager;
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		MultipleClientGame game = gameManager.getGame(1);
		game.move(new Square("a2"), new Square("a4"));
		return modelAndView;
	}

	public void setGameManager(MultipleClientGameManager gameManager) {
		this.gameManager = gameManager;
	}
	
}
