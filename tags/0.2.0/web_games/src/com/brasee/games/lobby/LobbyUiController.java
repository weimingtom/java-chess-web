package com.brasee.games.lobby;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.brasee.games.GamesUser;

public class LobbyUiController extends AbstractController {

	public static String GAMES_USER_SESSION_VARIABLE = "gamesUser";
	
	private static String DEFAULT_USERNAME = "Anonymous";
	private static String USERNAME_INPUT = "usernameInput";
	
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
		}
		else {
			GamesUser user = (GamesUser) request.getSession().getAttribute(GAMES_USER_SESSION_VARIABLE);
			if (user != null) {
				modelAndView = new ModelAndView("lobby");
				modelAndView.addObject("user", user);
			}
		}
		
		return modelAndView;
	}

}
