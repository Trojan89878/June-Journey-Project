package com.troy.junejourney.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.troy.junejourney.game.*;
import com.troy.junejourney.game.settings.*;

public class KeyInput implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {
		KeyHandler.updateKeys(e, true);
		if(e.getKeyCode() == KeyEvent.VK_R){
			Main.restart();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			Main.getGame().stop();
			System.exit(0);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_O){
			SettingsWindow.show();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_P){
			SettingsWindow.hide();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		KeyHandler.updateKeys(e, false);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
