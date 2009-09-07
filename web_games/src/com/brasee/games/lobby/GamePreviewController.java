package com.brasee.games.lobby;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class GamePreviewController extends AbstractController {

	private MultipleClientGameManager gameManager;
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		String gameIdParameter = request.getParameter("gameId");
		if (gameIdParameter != null && StringUtils.isNumeric(gameIdParameter)) {
			Integer gameId = Integer.parseInt(gameIdParameter);
			MultipleClientGame multipleClientGame = gameManager.getGame(gameId);
			if (multipleClientGame != null) {
				modelAndView = new ModelAndView(new GamePreviewView(multipleClientGame));
			}
		}
		return modelAndView;
	}

	public void setGameManager(MultipleClientGameManager gameManager) {
		this.gameManager = gameManager;
	}

}
