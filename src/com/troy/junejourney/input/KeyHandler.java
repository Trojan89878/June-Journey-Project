package com.troy.junejourney.input;

import java.awt.event.KeyEvent;

public class KeyHandler {
	private static boolean[] keys = new boolean[1024];
	
	public static void updateKeys(KeyEvent e, boolean pressed){
		keys[e.getKeyCode()] = pressed;
	}
	
	public static boolean getKey(int keyCode){
		return keys[keyCode];
	}

}
