package com.brasee.games.lobby.commands;

import javax.servlet.http.HttpServletRequest;

public class LobbyCommandFactory {

	public static LobbyCommand createCommand(HttpServletRequest request) {
		LobbyCommand command = new InvalidCommand();
		
		String commandName = request.getParameter("command");
		
		if ("add_message".equals(commandName)) {
			command = new AddMessageCommand();
		}
		else if ("retrieve_messages".equals(commandName)) {
			command = new RetrieveMessagesCommand();
		}
		else if ("retrieve_message_index".equals(commandName)) {
			command = new RetrieveMessageIndexCommand();
		}
		else if ("refresh_user".equals(commandName)) {
			command = new RefreshUserCommand();
		}
		else if ("retrieve_users".equals(commandName)) {
			command = new RetrieveUsersCommand();
		}
		
		return command;
	}

}
