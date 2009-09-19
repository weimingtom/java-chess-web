package com.brasee.games.chess.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.brasee.chess.pieces.Piece.Color;
import com.brasee.games.GamesUser;
import com.brasee.games.lobby.LobbyUiController;
import com.brasee.games.lobby.MultipleClientGame;
import com.brasee.games.lobby.MultipleClientGameManager;

public class ChessMultipleClientUiController extends AbstractController {

	private MultipleClientGameManager gameManager;
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		
		MultipleClientGame game = gameManager.retrieveGame(request.getParameter("gameId"));
		GamesUser user = (GamesUser) request.getSession().getAttribute(LobbyUiController.GAMES_USER_SESSION_VARIABLE);
		if (game != null && user != null) {
			modelAndView = new ModelAndView("chessMultipleClient");
			String actionParameter = request.getParameter("action");
			String colorParameter = request.getParameter("color");
			if ("join".equals(actionParameter) && ("white".equals(colorParameter) || "black".equals(colorParameter))) {
				Color color = "white".equals(colorParameter) ? Color.WHITE : Color.BLACK;
				game.addPlayerIfColorIsAvailable(color, user);
			}
		}
		
		return modelAndView;
	}

	public void setGameManager(MultipleClientGameManager gameManager) {
		this.gameManager = gameManager;
	}
	
}
