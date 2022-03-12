package cat.proven.gameapp.menus;

import cat.proven.gameapp.menus.Menu;
import cat.proven.gameapp.menus.Option;

/**
 * PlayersMainMenu.java
 * main menu of players application.
 */

public class PlayersMainMenu extends Menu {

	private final String [] labels = {
	 "Exit", 
	 "Next player", 
	 "Previous player",
	 "Show active player",
	 "List all players",
	 "Add new player after the current position",
	 "Remove active player"       
	};

	public PlayersMainMenu(String title) {
		super(title);
     	for (String s: labels)
     		add(new Option(s));
	}

}
