package com.brasee.games.chess.web;

import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;

import com.brasee.games.lobby.MultipleClientGame;
import com.brasee.games.lobby.MultipleClientGameManager;

public class MultipleClientGameHelper {

	public static MultipleClientGame retrieveGame(String gameIdString, MultipleClientGameManager gameManager) {
		MultipleClientGame multipleClientGame = null;
		
		try {
			if (gameIdString != null && StringUtils.isNumeric(gameIdString)) {
				Integer gameId = Integer.parseInt(gameIdString);
				multipleClientGame = gameManager.getGame(gameId);
			}
		}
		catch (Exception e) {
			Logger.getLogger(MultipleClientGameHelper.class.getName()).warning("Exception while retrieving game with id " 
					+ gameIdString + ", returning null.");
		}
		
		return multipleClientGame;
	}
}
