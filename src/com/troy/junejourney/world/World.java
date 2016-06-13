package com.troy.junejourney.world;

import java.awt.Color;
import java.awt.Graphics;

import com.troy.junejourney.game.*;
import com.troy.troyberry.math.vector.Vector2f;
import com.troy.troyberry.shape.*;

public class World implements IGame{
	
	private final static Rectangle ground = new Rectangle(new Vector2f(0f, (float)GameSettings.groundHeight), 10000f, 1000f);
	
	public static final Vector2f GRAVITY = new Vector2f(0, 0.007f), THRUSTERDOWN = new Vector2f(0, -0.014f),
			THRUSTERRIGHT = new Vector2f(0.004f, 0),
			THRUSTERLEFT = Vector2f.negate(THRUSTERRIGHT);
	
	@Override
	public void render(Graphics g) {
		g.setColor(new Color(Assets.getGround().getRGB(0, 0))); 
		g.fillRect(0, (int) (GameSettings.groundHeight - Camera.yOffset), GameSettings.width, GameSettings.height);
		g.setColor(Color.white);
	}
	
	@Override
	public void tick() {
		if(ground.intersectsWithRectangle(Game.player.getBoundingBox())){
			Game.player.setVelocity(new Vector2f(0, 0));
			Game.player.setEffectedByGravity(false);
			//Game.player.damage(100f);
		}
	}

}
  