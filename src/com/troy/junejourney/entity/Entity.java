package com.troy.junejourney.entity;

import static java.lang.Math.round;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.troy.junejourney.game.Camera;
import com.troy.junejourney.game.IGame;
import com.troy.junejourney.world.World;
import com.troy.troyberry.math.vector.Vector2f;
import com.troy.troyberry.shape.*;

public class Entity implements IGame {

	private Vector2f position, velocity;
	private int width, height;
	private float scale = 1.0f;
	private Image image;
	private boolean effectedByGravity = true;
	private boolean isAlive = true;

	public static List<Entity> entities = Collections.synchronizedList(new ArrayList<Entity>());

	public Entity(Vector2f position, Vector2f velocity, float scale, BufferedImage image,
			boolean addToList) {
		this.position = position;
		this.velocity = velocity;
		this.width = round(image.getWidth() * scale);
		this.height = round(image.getHeight() * scale);
		this.setScale(scale);
		this.image = image;
		if (addToList) {
			entities.add(this);
		}

	}

	@Override
	public void tick() {
		if(!isAlive()) return;
		// add the velocity onto the position of the entity
		position.add(velocity);
		// add gravity if the entity is effected by gravity
		if (effectedByGravity) velocity.add(World.GRAVITY);
	}

	@Override
	public void render(Graphics g) {
		if(!isAlive()) return;
		// draw the entity at the desired position and scale
		g.drawImage(getImage(), round(getPosition().getX()),
				round(getPosition().getY() - Camera.yOffset), getWidth(), getHeight(), null);
	}

	public void remove() {
		entities.remove(this);
	}

	public Rectangle getBoundingBox() {
		return new Rectangle(new Vector2f(round(position.getX()), round(position.getY())), getWidth(),
				getHeight());
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public Vector2f getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2f velocity) {
		this.velocity = velocity;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public boolean isEffectedByGravity() {
		return effectedByGravity;
	}

	public void setEffectedByGravity(boolean effectedByGravity) {
		this.effectedByGravity = effectedByGravity;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

}
