package com.troy.junejourney.game;

import com.troy.junejourney.game.launch.*;
import com.troy.junejourney.game.settings.*;

public class Main {
	
	public static Game game;
	
	public static void main(String[] args){
		@SuppressWarnings("unused")
		Assets assets = new Assets();
		
		//start the game
		SettingsWindow.initialize();
		TitleWindow.initialize();
	}

	public static Game getGame() {
		return game;
	}

	public static void restart() {
		game.stop();
		game = new Game();
	}
}
