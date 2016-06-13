package com.troy.junejourney.entity;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;

import com.troy.junejourney.game.*;
import com.troy.junejourney.game.settings.*;
import com.troy.junejourney.input.*;
import com.troy.junejourney.world.*;
import com.troy.troyberry.math.vector.*;
import com.troy.troyberry.shape.Rectangle;

import static java.lang.Math.round;

public class EntityPlayer extends Entity {
	private float health;
	private Random random = new Random();
	private float fuel;
	
	SettingsWindow window;

	public EntityPlayer(Vector2f position, Vector2f velocity, float scale, float health,
			BufferedImage image) {
		super(position, velocity, scale, image, false);
		this.setHealth(health);
		this.setFuel(413.342f);
	}

	@Override
	public void tick() {

		if (!isAlive()) {
			this.setFuel(0f);
			this.health = 0;
			return;
		}
		if (getHealth() <= 0) {
			this.setFuel(0f);
			this.setAlive(false);
			return;
		}
		super.tick();
		// read key inpts and move the rocket accordingly
		if (this.getFuel() >= 0) {
			if (KeyHandler.getKey(KeyEvent.VK_SPACE)) {
				Vector2f.add(getVelocity(), World.THRUSTERDOWN, getVelocity());
				useFuel(0.05f);
			}
			if (KeyHandler.getKey(KeyEvent.VK_A)) {
				Vector2f.add(getVelocity(), World.THRUSTERLEFT, getVelocity());
				useFuel(0.01f);
			}
			if (KeyHandler.getKey(KeyEvent.VK_D)) {
				Vector2f.add(getVelocity(), World.THRUSTERRIGHT, getVelocity());
				useFuel(0.01f);
			}
		}

	}

	private void useFuel(float f) {
		this.fuel = this.fuel - (f + (random.nextFloat() / 100));
	}

	@Override
	public void render(Graphics g) {
		if (!isAlive()){
			this.setHealth(0);
			return;
		}
		if (getHealth() <= 0){
			this.setAlive(false);
			return;
		}
		if (GameSettings.showHitBoxes) {
			Rectangle rect = this.getBoundingBox();
			g.drawRect(round(rect.getPosition().getX()),
					round (rect.getPosition().getY() - Camera.yOffset), round (this.getWidth()),
					round (this.getHeight()));
		}

		if (this.getFuel() >= 0) {
			if (KeyHandler.getKey(KeyEvent.VK_SPACE)) {
				float randomAddition;

				if (getVelocity().getY() > 0.5f) {
					randomAddition = random.nextFloat() * Game.player.getVelocity().getY();
					randomAddition = Math.min(randomAddition, 6.0f);
				} else {
					randomAddition = random.nextFloat() * 0.75f;
				}

				g.drawImage(Assets.getFlame(),
						// X position
						round(getPosition().getX() + Game.player.getWidth() / 2
								- Assets.getFlame().getWidth() / 4.2f + randomAddition),
						// Y position
						round(getPosition().getY() + Game.player.getHeight() / 2
								- Assets.getFlame().getHeight() / 8 - Camera.yOffset),
						// width and height
						round(Assets.getFlame().getWidth() * 0.5f),
						round(Assets.getFlame().getHeight() * 0.5f), null);
			}
		}
		super.render(g);
	}

	public void damage(float amount) {
		this.health -= amount;
	}

	public void centerCameraOnPlayer() {
		Camera.yOffset = Game.player.getPosition().getY()
				- (GameSettings.height / 2 - Game.player.getHealth() / 2);
	}

	public void kill() {
		setHealth(0);
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public float getFuel() {
		return fuel;
	}

	public void setFuel(float fuel) {
		this.fuel = fuel;
	}
}
