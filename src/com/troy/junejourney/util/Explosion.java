package com.troy.junejourney.util;

import java.awt.*;
import java.awt.image.*;
import java.util.*;

import com.troy.junejourney.game.*;
import com.troy.troyberry.math.vector.*;

public class Explosion implements IGame{
	
	private static final BufferedImage explosionSpriteSheet = Loader.loadBufferedImage("explosion.png");
	
	public static ArrayList<Explosion> explosionsTick = new ArrayList<Explosion>();
	public static ArrayList<Explosion> explosionsRender = new ArrayList<Explosion>();
	
	private boolean alive;
	
	private Vector2f position;
	
	private int index, tickStart, elapseSpeed;
	
	
	public Explosion(Vector2f position) {
		this.position = position;
		this.index = 0;
		this.tickStart = TickLoop.tickCounter;
		this.elapseSpeed = 16;
		this.alive = true;
		
		TickLoop.toAdd(this);
		
	}

	@Override
	public void tick(int tickCount) {//5 0 -> 17
		if(!this.alive) return;
		
		if(tickCount - tickStart > elapseSpeed * index){
			index++;
		}
		
		if(this.index > Math.ceil((float)explosionSpriteSheet.getWidth() / 300f)){
			this.index--;
		}
		if(index == 20){
			alive = false;
		}
	}

	@Override
	public void render(Graphics g) {
		if(!this.alive) return;
		g.drawImage(getImage(), Math.round(position.getX() - 150 + Game.player.getWidth() / 2f),
				Math.round(position.getY() - Camera.yOffset - 240 / 2 + Game.player.getWidth() / 2), null);
	}
	
	private BufferedImage getImage(){
		return explosionSpriteSheet.getSubimage(300 * this.index, 0, 300, 240);
	}
	
	@Override
	public String toString(){
		return ("Explosion:  position: " + position);
	}

}
