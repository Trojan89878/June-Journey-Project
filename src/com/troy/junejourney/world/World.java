package com.troy.junejourney.world;

import java.awt.Color;
import java.awt.Graphics;

import com.troy.junejourney.game.*;
import com.troy.troyberry.math.vector.Vector2f;
import com.troy.troyberry.shape.*;

public class World implements IGame {
	private int currentAstroidHeight = 0;
	private boolean playerIntersectsWithGround = false;
	private final static Rectangle ground = new Rectangle(
			new Vector2f(0f, (float) GameSettings.groundHeight), 10000f, 1000f);

	public static final Vector2f GRAVITY = new Vector2f(0, 0.006f),
			THRUSTERDOWN = new Vector2f(0, -0.015f), THRUSTERRIGHT = new Vector2f(0.004f, 0),
			THRUSTERLEFT = Vector2f.negate(THRUSTERRIGHT);

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(Assets.getGround().getRGB(0, 0)));
		g.fillRect(0, (int) (GameSettings.groundHeight - Camera.yOffset), GameSettings.width,
				GameSettings.height);
		g.setColor(Color.white);
	}

	@Override
	public void tick() {
		lookForPlayerIntersection();
		createNewAstroids();
	}

	private void createNewAstroids() {
		float distanceBelow = 1200f;
		int playerHeight = Math.round(Game.player.getPosition().getY() / (500f));
		if(playerHeight > currentAstroidHeight){
			currentAstroidHeight = playerHeight;
			if(playerHeight >= 7){
			
			Game.entityCreator.createAstroids(Math.round(Game.player.getPosition().getY() + distanceBelow), 
					((float)GameSettings.astroidDamageBuffer) / 40f, astroidSpawnLocation.CENTER);
			}else{
				if(Math.random() < 0.5d){
					Game.entityCreator.createAstroids(Math.round(Game.player.getPosition().getY() + distanceBelow), 
							(float)GameSettings.astroidDamageBuffer / 80f, astroidSpawnLocation.LEFT);
				}else{
					Game.entityCreator.createAstroids(Math.round(Game.player.getPosition().getY() + distanceBelow), 
							(float)GameSettings.astroidDamageBuffer / 80f, astroidSpawnLocation.RIGHT);
				}
			}
		}
	}

	private void lookForPlayerIntersection() {
		if(Game.player.getFuel() < 0.0f){
			Game.player.setFuel(0.0f);
		}
		if (!playerIntersectsWithGround) {
			if (ground.intersectsWithRectangle(Game.player.getBoundingBox())) {
				playerIntersectsWithGround = true;
				
				Game.player.setFuel(0.0f);
				Game.player.setEffectedByGravity(false);
				float damage = ((GameSettings.astroidDamageBuffer / 2) * Game.player.getVelocity().lengthSquared());
				Game.player.damage(damage);
			}
		}else{
			Game.player.setVelocity(new Vector2f());
			Game.player.setPosition(new Vector2f(Game.player.getPosition().getX(), GameSettings.groundHeight - Game.player.getHeight()));
		}
	}
	public enum astroidSpawnLocation{
		CENTER,
		LEFT,
		RIGHT
	}

}
