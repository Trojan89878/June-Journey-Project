package com.troy.junejourney.game;

import java.awt.Image;
import java.awt.image.BufferedImage;

import com.troy.junejourney.util.Loader;

public class Assets {
	private static BufferedImage rocket, astroid, spaceSky, flame, fireParticle, ground, ufo, brokenAstroid;
	public static Image not_found = Loader.loadImage("notFound.png");
	public Assets() {
		/**
		 * load in all images using the load methoid in the loadBufferedImage methoid in the loader class
		 * within the Troy Berry Libary (the libary that I made to assist in making games)
		 */
		not_found = Loader.loadBufferedImage("notFound.png");
		rocket = Loader.loadBufferedImage("rocket.png");
		astroid = Loader.loadBufferedImage("astroid.png");
		spaceSky = Loader.loadBufferedImage("spaceSky.png");
		flame = Loader.loadBufferedImage("flame.png");
		fireParticle = Loader.loadBufferedImage("fireParticle.png");
		ground = Loader.loadBufferedImage("ground.png");
		ufo = Loader.loadBufferedImage("ufo.png");
		brokenAstroid = Loader.loadBufferedImage("brokenAstroid.png");
	}
	public static BufferedImage getRocket() {
		return rocket;
	}
	public static BufferedImage getAstroid() {
		return astroid;
	}
	public static BufferedImage getSpacesky() {
		return spaceSky;
	}
	public static BufferedImage getFlame() {
		return flame;
	}
	public static BufferedImage getFireparticle() {
		return fireParticle;
	}
	public static Image getNot_found() {
		return not_found;
	}
	public static BufferedImage getGround() {
		return ground;
	}
	public static BufferedImage getUfo() {
		return ufo;
	}
	public static BufferedImage getBrokenAstroid() {
		return brokenAstroid;
	}
}
