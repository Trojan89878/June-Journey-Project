package com.troy.junejourney.entity;

import java.util.Random;

import com.troy.junejourney.game.Assets;
import com.troy.junejourney.game.Game;
import com.troy.junejourney.game.GameSettings;
import com.troy.junejourney.world.World.*;
import com.troy.troyberry.math.vector.Vector2f;

/**
 * This class is a simple class the creates bunches of different kinds of
 * entities quickly I use this clsss to create all the astroids when the game
 * starts up and little astroids then you hit an astroid
 * 
 * @author Troy
 */
public class EntityCreator {
	/**
	 * create a rendom object so that we can use all of the cool stuff in thr
	 * random class
	 */
	Random random = new Random();

	Game game;

	public void init(Game game) {
		this.game = game;
	}

	/**
	 * this creates the astroids when you hit any astroid
	 * 
	 * @param amount,
	 *            a integer that says how many astroids to create
	 * @param lifelength,
	 *            how long these little astroids should live
	 * @param location,
	 *            where to create the astroids
	 * @param scale
	 *            how, large should the astoids be big? small?
	 * @author Troy
	 */
	public void createAstroidParticles(int amount, int lifelength, Vector2f location, float scale) {
		// loop so that I can create a lot of astroids quickly
		for (int i = 0; i < amount; i++) {

			/**
			 * this creates a new astroid particle (dont worry you cant collide
			 * with praticles) at the location specified, traveling in a random
			 * direction with the scale specified with the astroid texture, true
			 * because we eant to add it to the list so that it is ticked and
			 * rendered with the lifelength specified
			 * 
			 * @author Troy
			 */
			new EntityParticle(
					new Vector2f(location.getX() + random.nextFloat() * 50,
							location.getY() + random.nextFloat() * 50),
					Vector2f.randomVector(1), scale, Assets.getBrokenAstroid(), true, lifelength)
							.setEffectedByGravity(false);
		}
	}

	/**
	 * This methoid creates all of the astroids at a given height with a given
	 * difficulty
	 * 
	 * @param height
	 *            how far to spawn the astroids off the ground
	 * @param spacing
	 *            how many pixels should the astroids layers be apart
	 * @author Troy
	 * @param center
	 */
	public void createAstroids(int height, float difficulty, astroidSpawnLocation position) {
		float difficultyScale = 20f;
		if (position == astroidSpawnLocation.CENTER) {
			if ((GameSettings.groundHeight - (GameSettings.groundHeight * (1f / 6f))) > height) {
				for (int i = 0; i < Math.round(difficulty * 2); i++) {
					EntityAstroid a = new EntityAstroid(
							new Vector2f(GameSettings.width * random.nextFloat(),
									height + (600 - (300 * random.nextFloat()))),

							new Vector2f((0.5f - random.nextFloat()) * difficulty,
									(random.nextFloat() * difficulty) / 4f),
							0.7f + random.nextFloat(), Assets.getAstroid(), true);

					a.setEffectedByGravity(false);
				}
			}
		} else if (position == astroidSpawnLocation.LEFT) {
			EntityAstroid a = new EntityAstroid(new Vector2f(
					((float) GameSettings.width * (1f / 4f)) * random.nextFloat(), height),

					Vector2f.randomVector(((difficulty / (difficultyScale / 2))
							* (difficulty / (difficultyScale / 2)))),
					random.nextFloat(), Assets.getAstroid(), true);

			a.setEffectedByGravity(false);
		}

		else if (position == astroidSpawnLocation.RIGHT) {

			EntityAstroid a = new EntityAstroid(
					new Vector2f(GameSettings.width
							- (((float) GameSettings.width * (1f / 4f)) * random.nextFloat()),
							height),
					Vector2f.randomVector(((difficulty / (difficultyScale / 2))
							* (difficulty / (difficultyScale / 2)))),
					random.nextFloat(), Assets.getAstroid(), true);

			a.setEffectedByGravity(false);

		}

	}
}
