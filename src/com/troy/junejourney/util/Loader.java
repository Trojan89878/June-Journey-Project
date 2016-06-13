package com.troy.junejourney.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.troy.junejourney.game.Assets;
import com.troy.troyberry.logging.Log;

public class Loader {

	public static BufferedImage loadBufferedImage(String string) {
		try {
			BufferedImage image = ImageIO
					.read(Loader.class.getClassLoader().getResourceAsStream(string));
			if (image != null) {
				return image;
			} else {
				Log.error("cound not load image: " + string);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}catch(IllegalArgumentException e){
			System.err.println("could not load image: " + string);
		}
		return (BufferedImage) Assets.not_found;
	}

	public static Image loadImage(String string) {
		try {
			return ImageIO.read(Loader.class.getClassLoader().getResourceAsStream(string));
		} catch (IOException e) {
			Log.error("cound not load image: " + string);
		}
		return null;
	}

}
