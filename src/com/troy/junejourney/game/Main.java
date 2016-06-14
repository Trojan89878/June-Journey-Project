package com.troy.junejourney.game;

import com.troy.junejourney.game.launch.*;
import com.troy.junejourney.game.settings.*;

public class Main {
	/**
	 * The instance of the game 
	 */
	public static Game game;
	/**
	 * This main methoid is the start of the progam
	 * it is run whenever you doubleclick on the .jar file
	 */
	public static void main(String[] args){
		@SuppressWarnings("unused")
		
		/**
		 * This will run the constructor in the assets class
		 * whitch will load in all the images used by the game
		 */
		Assets assets = new Assets();
		
		/**
		 * This creates the settings window whitch will allow users to open it when they press o
		 */
		SettingsWindow.initialize();
		
		HelpInfoWindow.initialize();
		
		/**
		 * This opens the main window that contains the play button settings button ect.
		 */
		
		TitleWindow.initialize();
	}
	
	/**little getter for the current game object*/
	public static Game getGame() {
		return game;
	}
	/**
	 * This little method stops the game and then creates a new game object, effectively restarting the game.
	 */
	public static void restart() {
		game.stop();
		game = new Game();
	}
}
