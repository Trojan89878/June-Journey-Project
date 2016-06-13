package com.troy.junejourney.game;
/**
 * A simple calss the stores values needed for the game.
 * @author Troy
 *
 */
public class GameSettings {
	//limit FPS is weather or not we want to limit the FPS to a certain number
	public static boolean limitFPS = true, showPSInfo;
	//limitFPSto is how many FPS we want if we are limiting them to a certain value
	public static int limitFPSto = 60;
	/**
	 * width and height corrorspond to the size of the window
	 * ground height is how far the player must travel in order to reach the ground
	 */
	public static int width, height, groundHeight = 20000;
	
	/**
	 * This number is mutiplyed to the damage calculated when the player collides with an astroid
	 * The higher this number the more damage the player will take and thus makes the game hrader
	 */
	public static float astroidDamageBuffer = 50;
	
	/**
	 * says how many points to use for calculating a player astroid collision.
	 * The higher the number the slower the game but better collision detection
	 * is more accurate
	 */
	public static int circlePointCount = 50;
	
	/**
	 * weather or not to show the boxes around astroids player ect
	 */
	public static boolean showHitBoxes = false;

}
