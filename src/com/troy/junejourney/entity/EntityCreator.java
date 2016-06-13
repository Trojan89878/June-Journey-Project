package com.troy.junejourney.entity;

import java.util.Random;

import com.troy.junejourney.game.Assets;
import com.troy.junejourney.game.Game;
import com.troy.junejourney.game.GameSettings;
import com.troy.troyberry.math.vector.Vector2f;
/**
 * This class is a simple class the creates bunches of different kinds of entities quickly
 * I use this clsss to create all the astroids when the game starts up and little astroids
 * then you hit an astroid
 * 
 * @author Troy
 */
public class EntityCreator {
	/**
	 * create a rendom object so that we can use all of the cool stuff in thr random class
	 */
	Random random = new Random();

	Game game;
	
	public void init(Game game) {
		this.game = game;
	}
	/**
	 * this creates the astroids when you hit any astroid
	 * @param amount, a integer that says how many astroids to create
	 * @param lifelength, how long these little astroids should live
	 * @param location, where to create the astroids
	 * @param scale how, large should the astoids be big? small?
	 * @author Troy
	 */
	public void createAstroidParticles(int amount, int lifelength, Vector2f location, float scale) {
		//loop so that I can create a lot of astroids quickly
		for (int i = 0; i < amount; i++) {
			
			/**
			 * this creates a new astroid particle (dont worry you cant collide with praticles)
			 * 	at the location specified, traveling in a random direction with the scale specified
			 * with the astroid texture, true because we eant to add it to the list
			 * so that it is ticked and rendered
			 * with the lifelength specified
			 * @author Troy
			 */
			new EntityParticle (new Vector2f(location.getX() + random.nextFloat() * 50, 
					location.getY() + random.nextFloat() * 50), Vector2f.randomVector(1), scale,
					Assets.getBrokenAstroid(), true, lifelength).setEffectedByGravity(false);
		}
	}
	/**
	 * This methoid creates all of the astroids when the game first starts up
	 * @param heightOfGround how far is it to the ground
	 * @param spacing how many pixels should the astroids layers be apart
	 * @author Troy
	 */
	public void createAstroids(int heightOfGround, int spacing) {
		int runs = Math.round(heightOfGround / spacing);
		/**
		 * there are 4 loops in here because I want the first layer to be on the left of the screen
		 * the second to be on the right
		 * and the third layer to be on the right again
		 * this it so that the player can see the astroids at the begining, but not be in danger
		 * of hitting them since the player spawns in the middle
		 * 
		 * The last loop creates as many astroid layers as it can until it reacses the height limit
		 * 
		 * 
		 * @author Troy
		 */
		for (int i1 = 0; i1 < 1 + random.nextInt(2); i1++) {
			EntityAstroid e = new EntityAstroid(
					new Vector2f(random.nextFloat() * 400, 2500 + 0 * spacing),
					new Vector2f((0.5f - random.nextFloat()) / 3, (0.5f - random.nextFloat()) / 7),
					random.nextFloat() * 1.2f, Assets.getAstroid(), true);
			e.setEffectedByGravity(false);
		}

		for (int i2 = 0; i2 < 1 + random.nextInt(2); i2++) {
			EntityAstroid e = new EntityAstroid(
					new Vector2f(GameSettings.width - (random.nextFloat() * 400),
							2500 + 1 * spacing),
					new Vector2f((0.5f - random.nextFloat()) / 3, (0.5f - random.nextFloat()) / 7),
					random.nextFloat() * 1.2f, Assets.getAstroid(), true);
			e.setEffectedByGravity(false);
		}

		for (int i3 = 0; i3 < 1 + random.nextInt(2); i3++) {
			EntityAstroid e = new EntityAstroid(
					new Vector2f(random.nextFloat() * 400, 2500 + 2 * spacing),
					new Vector2f((0.5f - random.nextFloat()) / 3, (0.5f - random.nextFloat()) / 7),
					random.nextFloat() * 1.2f, Assets.getAstroid(), true);
			e.setEffectedByGravity(false);
		}

		for (int realloopi = 3; realloopi < runs; realloopi++) {
			for (int realrow = 0; realrow < Math.round(1 + random.nextInt(4) + (GameSettings.width / 1000f)); realrow++) {
				EntityAstroid e = new EntityAstroid(
						new Vector2f(random.nextFloat() * GameSettings.width,
								realloopi * spacing + 2500),
						new Vector2f((0.5f - random.nextFloat()) / 2,
								(0.5f - random.nextFloat()) / 7),
						random.nextFloat() * 1.3f + 0.5f, Assets.getAstroid(), true);
				e.setEffectedByGravity(false);
			}
		}
	}

}
