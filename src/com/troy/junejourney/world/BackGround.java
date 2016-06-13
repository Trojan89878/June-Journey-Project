package com.troy.junejourney.world;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.troy.junejourney.game.Assets;
import com.troy.junejourney.game.Game;
import com.troy.junejourney.game.GameSettings;
import com.troy.junejourney.game.IGame;

public class BackGround implements IGame{
	Game game;
	Color worldColor;
	BufferedImage skyImage;
	

	public BackGround(Game game) {
		this.game = game;
		worldColor = new Color(0, 0, 0);
	}
	
	public void init(){
		skyImage = Assets.getSpacesky();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(worldColor);
		g.fillRect(0, 0, GameSettings.width, GameSettings.height);
		g.setColor(Color.WHITE);
	}

	@Override
	public void tick() {
		float percentFinished = Game.player.getPosition().getY() / (float)(GameSettings.groundHeight);
		percentFinished = Math.min(percentFinished, 1);
		percentFinished = Math.max(percentFinished, 0);
		worldColor = new Color(skyImage.getRGB(0, Math.round((skyImage.getHeight() - 1) * percentFinished)));
		
		
		
		
	}
	
	

}
