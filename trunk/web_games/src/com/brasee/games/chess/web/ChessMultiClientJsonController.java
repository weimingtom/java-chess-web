package com.brasee.games.chess.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.brasee.games.chess.web.commands.multiclient.MultiClientChessCommand;
import com.brasee.games.chess.web.commands.multiclient.MultiClientChessCommandFactory;
import com.brasee.games.lobby.MultiClientGame;
import com.brasee.games.lobby.MultiClientGameManager;

public class ChessMultiClientJsonController extends AbstractController {

	private MultiClientGameManager gameManager;
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception { 
		MultiClientGame game = gameManager.retrieveGame(request.getParameter("gameId"));
		MultiClientChessCommand command = MultiClientChessCommandFactory.createCommand(request);
		JsonView jsonView = command.processCommand(request, game);
		return new ModelAndView(jsonView);
	}

	public void setGameManager(MultiClientGameManager gameManager) {
		this.gameManager = gameManager;
	}
	
}
