package com.brasee.games.lobby;

import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.brasee.chess.pieces.Piece.Color;
import com.brasee.games.GamesUser;

public class LobbyUiController extends AbstractController {

	public static String GAMES_USER_SESSION_VARIABLE = "gamesUser";
	
	private static String DEFAULT_USERNAME = "Anonymous";
	private static String USERNAME_INPUT = "usernameInput";
	
	private MultipleClientGameManager gameManager;
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("lobbyLogin");
		
		if (METHOD_POST.equals(request.getMethod())) {
			String username = request.getParameter(USERNAME_INPUT);
			if (username == null || username.isEmpty() || username.length() > 25) {
				username = DEFAULT_USERNAME;
			}
			GamesUser user = new GamesUser();
			user.setName(ChatStringUtils.escapeMarkupChars(username));
			request.getSession().setAttribute(GAMES_USER_SESSION_VARIABLE, user);
			modelAndView = new ModelAndView("lobby");
			modelAndView.addObject("user", user);
			modelAndView.addObject("gameMap", generateGameMap());
		}
		else {
			GamesUser user = (GamesUser) request.getSession().getAttribute(GAMES_USER_SESSION_VARIABLE);
			if (user != null) {
				modelAndView = new ModelAndView("lobby");
				modelAndView.addObject("user", user);
				modelAndView.addObject("gameMap", generateGameMap());
			}
		}
		
		return modelAndView;
	}
	
	private Map<Integer, String> generateGameMap() {
		Map<Integer, String> gameMap = new TreeMap<Integer, String>();
		
		for (int i = 1; i <= gameManager.getNumberOfGames(); i++) {
			gameMap.put(i, generateGameDescription(gameManager.getGame(i)));
		}
		
		return gameMap;
	}

	private String generateGameDescription(MultipleClientGame game) {
		String gameDescription = "";

		if (game.getPlayerName(Color.WHITE) == null && game.getPlayerName(Color.BLACK) == null) {
			gameDescription = "Empty game";
		}
		else if (game.getPlayerName(Color.WHITE) != null && game.getPlayerName(Color.BLACK) != null) {
			gameDescription = game.getPlayerName(Color.WHITE) + " vs. " + game.getPlayerName(Color.BLACK);
		}
		else {
			String currentPlayerName = game.getPlayerName(Color.WHITE) != null ? game.getPlayerName(Color.WHITE) : game.getPlayerName(Color.BLACK);
			gameDescription = currentPlayerName + " waiting for opponent";
		}

		return gameDescription;
	}

	public void setGameManager(MultipleClientGameManager gameManager) {
		this.gameManager = gameManager;
	}

}
