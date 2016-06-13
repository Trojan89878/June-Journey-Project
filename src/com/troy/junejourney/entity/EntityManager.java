package com.troy.junejourney.entity;

import java.awt.Graphics;

import com.troy.junejourney.game.IGame;

public class EntityManager implements IGame {

	@Override
	public void render(Graphics g) {
		for(int i = 0; i < Entity.entities.size(); i++){
			Entity currentEntity = Entity.entities.get(i);
			currentEntity.render(g);
		}
	}

	@Override
	public void tick() {
		for(int i = 0; i < Entity.entities.size(); i++){
			Entity currentEntity = Entity.entities.get(i);
			currentEntity.tick();

		}
	}

}
