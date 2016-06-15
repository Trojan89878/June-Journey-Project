package com.troy.junejourney.util;

import java.awt.Font;
import java.awt.Graphics;
import java.text.DecimalFormat;

import com.troy.junejourney.game.Camera;
import com.troy.junejourney.game.Game;
import com.troy.junejourney.game.GameSettings;
import com.troy.troyberry.util.MiscUtil;

public class StatManager {
	static long FPSStartTime, TPSStartTime;
	static int currentFPS = 0, currentTPS = 0, FPScounter = 0, TPScounter = 0;
	public static final Font STAT_FONT = new Font("Verdana", Font.PLAIN, 15);
	public static final Font DEAD_FONT = new Font("Verdana", Font.BOLD, 105);
	public static final Font HP_FONT = new Font("Verdana", Font.BOLD, 45);
	public static DecimalFormat df = new DecimalFormat("##.##");

	public static void render(Graphics g) {
		FPScounter++;
		if ((System.nanoTime() - FPSStartTime) >= 1000000000L) {
			FPSStartTime += 1000000000L;
			currentFPS = FPScounter;
			FPScounter = 0;
		}
		g.setFont(STAT_FONT);
		g.drawString(currentFPS + " FPS", GameSettings.width - 100, 17);
		g.drawString(currentTPS + " TPS", GameSettings.width - 100, 40);

		if (Game.player.getHealth() > 0) {
			MiscUtil.drawCenteredString(g,
					"Velocity " + df.format(Math.abs(Game.player.getVelocity().length()) * 10)
							+ " m/s",
					Math.round(Game.player.getPosition().getX() + Game.player.getWidth() / 2),
					Math.round(Game.player.getPosition().getY() - Camera.yOffset - 5));
		}

		g.drawString("Altitude "
				+ df.format(Math.abs(
						(float) GameSettings.groundHeight - Game.player.getPosition().getY()) / 1000)
				+ " Miles", GameSettings.width - 250, 123);

		g.drawString("Fuel Remaining " + df.format(Game.player.getFuel()) + " Liters",
				GameSettings.width - 250, 146);

		float tempHealth = Game.player.getHealth();
		if (tempHealth < 0) {
			tempHealth = 0;
		}
		g.setFont(HP_FONT);
		g.drawString(((int) tempHealth) + "", 5, GameSettings.height - 50);
		g.setFont(DEAD_FONT);
		if (Game.player.getHealth() <= 0) {
			MiscUtil.drawCenteredString(g, "You Died", GameSettings.width / 2,
					GameSettings.height / 2);
			Camera.yOffset -= 0.75f;
		}
		if (Game.hasWon && Game.player.isAlive()) {
			Camera.yOffset -= 0.5f;
			MiscUtil.drawCenteredString(g, "You Won!", GameSettings.width / 2,
					GameSettings.height / 2);
		}
		g.setFont(STAT_FONT);

	}

	public static void tick(int tickCount) {
		TPScounter++;
		if ((System.nanoTime() - TPSStartTime) >= 1000000000L) {
			TPSStartTime += 1000000000L;
			currentTPS = TPScounter;
			TPScounter = 0;
		}
	}

	public static void init() {
		FPSStartTime = System.nanoTime();
		TPSStartTime = System.nanoTime();
	}

}
