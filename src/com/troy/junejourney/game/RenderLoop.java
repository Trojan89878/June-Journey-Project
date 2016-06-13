package com.troy.junejourney.game;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import com.troy.troyberry.logging.Log;

public class RenderLoop extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;
	public Thread renderThread;
	public volatile boolean renderRunning;
	private Game game;
	private Image dbImage;
	private Graphics dbg;
	public static boolean rendering = true;
	
	/**When this methoid is called, it starts the thread and renderloop
	 */
	public void start() {
		renderThread = new Thread(this, "Render-Thread");
		renderThread.setPriority(Thread.MAX_PRIORITY);
		
		//calls the run methoid
		renderThread.start();

	}
	
	
	/**This methoid stops the Render Thread
	 */
	public void stop() {
		renderRunning = false;
		try {
			renderThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**this methoid is called when the window is created
	 * this methoid automatically calles start, starting the renderloop.
	 */
	@Override
	public void addNotify() {
		super.addNotify();
		start();
	}
	
	
	/**
	 * run methoid is called when the new thread strats
	 */
	@Override
	public void run() {
		/**If we want to limit the FPS to a certain value, it will run this causing the 
		 * game to get rendered as often as we want
		 */
		if (GameSettings.limitFPS) {
			//some more math for making sure that we only render as often as needed
			int fps = GameSettings.limitFPSto;
			double timePerTick = 1000000000 / fps;
			double delta = 0;
			long now;
			long lastTime = System.nanoTime();
			long timer = 0;
			int ticks = 0;
			renderRunning = true;
			
			/**this is the actual loop that keeps updating the display with the latest 
			 * positions for everything
			 */
			while (renderRunning) {
				
				now = System.nanoTime();
				delta += (now - lastTime) / timePerTick;
				timer += now - lastTime;
				lastTime = now;
				if (delta >= 1) {
					if(ticks >= GameSettings.limitFPSto && GameSettings.limitFPS){
					}else{
						rendering = true;
						
						/**
						 * game render creates the second buffer if it hasent been allready, 
						 * then clears the buffer, then calls all of the render methoids
						 * across the game whitch draw their content to the buffer
						 */
						gameRender();
						
						/**
						 * Paint screen draws the finished buffer to the screen
						 */
						paintScreen();
						rendering = false;
						ticks++;
						delta--;
						
					}
					
				}
				
				if (timer >= 1000000000) {
					ticks = 0;
					timer = 0;
				}
			}

		} else {
			/**
			 * If we dont want to have a ceratin FPS just keep running the loop without 
			 * calculating when the next frame should be
			 */
			renderRunning = true;
			while (renderRunning) {

				/**
				 * game render creates the second buffer if it hasent been allready, 
				 * then clears the buffer, then calls all of the render methoids
				 * across the game whitch draw their content to the buffer
				 */
				gameRender();
				
				/**
				 * Paint screen draws the finished buffer to the screen
				 */
				paintScreen();

			}
		}
	}

	public void gameRender() {
		 // Create the Buffer if it is null ie it hasent been created
		if (dbImage == null) {
			DisplayMode displayMode = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
					.getDisplayMode();
			
			//create the buffer to be the size of the screen
			dbImage = createImage(displayMode.getWidth(), displayMode.getHeight());
			
			if (dbImage == null) {
				/**if the buffer is stil null after attampting to be ceated, something went wrong 
				*so log the error
				*/
				Log.error("dbImage is stli null!");
				return;
			} else {
				
				/**
				 * if everything went ok with the buffer, set dgb to
				 * the buffer's image so that it can be drawn to by the game.
				 */
				dbg = dbImage.getGraphics();
			}
		}
		//set the color so that when it cleass the buffer it is all white
		dbg.setColor(Color.WHITE);
		
		// Clear the buffer by painting a white rectangle across the entire thing
		dbg.fillRect(0, 0, GameSettings.width, GameSettings.height);
		/**
		 * call the render methoid in game passing in the buffer's image so that 
		 * the game can draw to it easily
		 */
		game.render(dbg);
	}

	public void paintScreen() {
		Graphics g;
		try {
			//get the graphics of the window so that we can draw to the window
			g = this.getGraphics();
			
			//if the buffer and window have been created
			if (dbg != null && g != null) {
				
				/**
				 * draw the buffer to the screen so that the user can see the new locations for 
				 * the astroids, player, ect.
				 */
				g.drawImage(dbImage, 0, 0, null);
			}
			
			/**
			 * sync the display
			 */
			Toolkit.getDefaultToolkit().sync();
			
			/**
			 * dispose of the graphics because they take up alot of memory
			 * and because they have allready been drawn to the screen 
			 * so they are useless
			 */
			g.dispose();
		} catch (Exception e) {
			/**
			 * if some exception is thrown (ie something went wrong), display a error message
			 */
			Log.error("Error in displaying dbImage: " + e.getMessage());
		}
	}
	
	public void setGame(Game game){
		this.game = game;
	}

}

