package com.troy.junejourney.entity;

import java.awt.*;
import java.awt.image.*;

import com.troy.troyberry.math.vector.*;

public class EntityParticle extends Entity {
	
	private final int lifeLength;
	private int age;

	public EntityParticle(Vector2f position, Vector2f velocity, float scale, BufferedImage image,
			boolean addToList, final int lifeLength) {
		super(position, velocity, scale, image, addToList);
		this.lifeLength = lifeLength;
		this.setAge(0);
	}
	
	
	@Override
	public void tick(){
		if(this.age > this.lifeLength) return;
		this.age++;
		super.tick();
		
	}
	
	@Override
	public void render(Graphics g){
		if(this.age > this.lifeLength) return;
		super.render(g);
	}

	public int getLifeLength() {
		return lifeLength;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
