package com.troy.junejourney.game;

import java.awt.Graphics;

public class Camera implements IGame {

	public static float yOffset = 0;

	@Override
	public void render(Graphics g) {
	}

	@Override
	public void tick(int tickCount) {
		if(Game.player.isAlive()){
			Game.player.centerCameraOnPlayer();
		}
	}

}
