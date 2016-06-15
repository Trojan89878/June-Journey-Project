package com.troy.junejourney.entity;

import static java.lang.Math.*;

import java.awt.*;
import java.awt.image.*;

import com.troy.junejourney.game.*;
import com.troy.junejourney.util.*;
import com.troy.troyberry.math.vector.*;
import com.troy.troyberry.shape.*;
import com.troy.troyberry.shape.Rectangle;

public class EntityAstroid extends Entity {

	private boolean isIntersectedWithPlayer = false;

	public EntityAstroid(Vector2f position, Vector2f velocity, float scale, BufferedImage image,
			boolean addToList) {
		super(position, velocity, scale, image, addToList);
	}

	public boolean isIntersectedWithPlayer() {
		return isIntersectedWithPlayer;
	}

	public void setIntersectedWithPlayer(boolean isIntersectedWithPlayer) {
		this.isIntersectedWithPlayer = isIntersectedWithPlayer;
	}

	public Rectangle getRawRectangle() {
		return new Rectangle(new Vector2f(this.getPosition().getX(), this.getPosition().getY()),
				(float) (this.getWidth()), (float) (this.getHeight()));
	}

	@Override
	public void tick(int tickCount) {
		if (!isAlive()) return;
		super.tick(tickCount);

		if (this.getPosition().getX() + this.getWidth() < 0) {
			this.getPosition().setX(GameSettings.width + this.getWidth());
		}

		if (this.getPosition().getX() - this.getWidth() > GameSettings.width) {
			this.getPosition().setX(0 - this.getWidth());
		}
		if (Game.player.isAlive()) {
			if (this.getRawRectangle().intersectsWithRectangle(Game.player.getBoundingBox())) {
				double angle = 0;
				double slice = Math.PI * 2 / GameSettings.circlePointCount;
				double x, y;
				Vector2f center = new Vector2f(this.getPosition().getX() + this.getWidth() / 2,
						this.getPosition().getY() + this.getWidth() / 2);

				for (int i = 0; i < GameSettings.circlePointCount; i++) {
					angle = i * slice;
					x = (center.getX() + Math.cos(angle) * (this.getWidth() / 2));
					y = (center.getY() + Math.sin(angle) * (this.getWidth() / 2));
					if (Game.player.getBoundingBox()
							.intersectsWithPoint(new Vector2f((float) x, (float) y))) {
						astroidCollidedWithPlayer();
						break;
					}
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		if (!isAlive()) return;
		super.render(g);
		if (GameSettings.showHitBoxes) {
			g.drawOval((int) getPosition().getX(), (int) (getPosition().getY() - Camera.yOffset),
					getWidth(), getHeight());
			double angle = 0;
			double slice = Math.PI * 2 / GameSettings.circlePointCount;
			double x, y;
			Vector2f center = new Vector2f(this.getPosition().getX() + this.getWidth() / 2,
					this.getPosition().getY() + this.getWidth() / 2);

			for (int i = 0; i < GameSettings.circlePointCount; i++) {
				angle = i * slice;
				x = (center.getX() + Math.cos(angle) * (this.getWidth() / 2));
				y = (center.getY() + Math.sin(angle) * (this.getWidth() / 2));
				g.fillOval((int) round(x - 2), (int) round(y - Camera.yOffset - 2), 4, 4);
			}
		}
	}

	private void astroidCollidedWithPlayer() {
		if (!isIntersectedWithPlayer) {
			isIntersectedWithPlayer = true;
			{
				// get vectors to be used in calculations
				Vector2f playerVelocity = Game.player.getVelocity();
				Vector2f playerPosition = Game.player.getPosition();
				Vector2f astroidPosition = this.getPosition();
				Vector2f vectorFromPlayerToAstriod = Vector2f
						.getVectorBetweenPoints(astroidPosition, playerPosition);

				/* find out how much damage the player will take with the
				 * following factors: how head on the collision is times the
				 * difficulty of the game (hard is 50) times the overall
				 * velocity of the player times the scale of the astroid (ranges
				 * from 0.5 to 1.8) squared */
				float damage = (Vector2f.dot(Vector2f.normalise(playerPosition),
						Vector2f.normalise(vectorFromPlayerToAstriod))
						* GameSettings.astroidDamageBuffer * playerVelocity.length()
						* this.getScale() * this.getScale());
				// apply the damage to the player
				Game.player.damage(Math.abs(damage));
				// destroy the astroid
				this.setAlive(false);
				// create some little astroids that are "bits" of the recently
				// blown up astroid
				Game.entityCreator.createAstroidParticles(
						Math.round(this.getScale() * this.getScale()) + 1, 200 * 10,
						new Vector2f(this.getPosition()), this.getScale() / 2.5f);

				// "bounce" the player off of the astroid and reduce their
				// velocity
				Game.player
						.setVelocity(vectorFromPlayerToAstriod.normalise().negate().scale(0.25f));
				if (Game.player.getHealth() <= 0.0f) {
					new Explosion(Game.player.getPosition());
					Game.player.centerCameraOnPlayer();
				}
			}
		}
	}

	public boolean intersectsWithAstroid(EntityAstroid other) {
		return new Circle((this.getX() + this.getWidth() / 2f),
				(this.getY() + this.getHeight() / 2f), this.getWidth() / 2f)

						.intersectsWithCircle(new Circle((other.getX() + other.getWidth() / 2f),
								(other.getY() + other.getHeight() / 2f), other.getWidth() / 2f));
	}
}
