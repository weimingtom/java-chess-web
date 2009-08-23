package com.brasee.games.lobby;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class MultipleGameClientTest {

	private static final int DEFAULT_NUMBER_OF_GAMES = 6;
	
	private MultipleClientGameManager manager;
	
	@Before
	public void setUp() {
		manager = new MultipleClientGameManager(DEFAULT_NUMBER_OF_GAMES);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNumberOfGamesMustBeGreaterThan0() {
		new MultipleClientGameManager(0);
	}
	
	@Test
	public void testVerifyAllGamesInstantiated() {
		for (int i = 1; i <= 6; i++) {
			assertNotNull(manager.getGame(i));
		}
		assertNull(manager.getGame(0));
		assertNull(manager.getGame(-1));
		assertNull(manager.getGame(50));
	}
	
}
