package com.troy.junejourney.game;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.troy.junejourney.entity.*;
import com.troy.junejourney.input.*;
import com.troy.junejourney.util.*;
import com.troy.junejourney.world.*;
import com.troy.troyberry.math.vector.*;
import com.troy.troyberry.util.*;

public class Game implements IGame{
	
	public static boolean hasWon = false;
	
	public static boolean inGame = false;
	
	private JFrame jframe;
	
	public static EntityCreator entityCreator;
	EntityManager entityManager;
	BackGround backGround;
	
	GameWindow window;
	RenderLoop renderLoop;
	TickLoop tickLoop;	
	
	World world;
	Camera camera;
	
	public static EntityPlayer player;
	
	

	public Game() {
		hasWon = false;
		window = new GameWindow("June Journey project", 1440, 810, true);
		GameSettings.width = 1440;
		GameSettings.height = 810;
		window.makeWindowFullScreen();
		
		camera = new Camera();
		world = new World();
		
		player = new EntityPlayer(new Vector2f(GameSettings.width / 2 + Assets.getRocket().getWidth() / 2, 1000),
				new Vector2f(0.0f, 5.0f), 1.0f, 100, Assets.getRocket());
		player.centerCameraOnPlayer();
		
		
		
		this.jframe = window.getWindow();
		jframe.addKeyListener(new KeyInput());
		
		StatManager.init();
		entityManager = new EntityManager();
		backGround = new BackGround(this);
		backGround.init();
		
		entityCreator = new EntityCreator();
		entityCreator.init(this);
		entityCreator.createAstroids((int)(GameSettings.groundHeight * 5 / 8), 500);
		
		renderLoop = new RenderLoop();
		renderLoop.setGame(this);
		jframe.add(renderLoop);
		
		//create the tick loop and run its run methoid
		tickLoop = new TickLoop();
		tickLoop.start(this);
		
		jframe.addComponentListener(new ComponentListener() {

		    public void componentResized(ComponentEvent e) {
		        GameSettings.width = jframe.getWidth();  
		        GameSettings.height = jframe.getHeight(); 
		    }

			@Override
			public void componentMoved(ComponentEvent e) {
			}

			@Override
			public void componentShown(ComponentEvent e) {
			}

			@Override
			public void componentHidden(ComponentEvent e) {
			}
		});
		inGame = true;
		jframe.setVisible(true);
		
	}

	public JFrame getJframe() {
		return jframe;
	}
	
	@Override
	public void render(Graphics g) {
		backGround.render(g);
		world.render(g);
		g.setColor(Color.white);
		entityManager.render(g);
		player.render(g);
		StatManager.render(g);
	}

	@Override
	public void tick() {
		player.tick();
		entityManager.tick();
		world.tick();
		StatManager.tick();
		backGround.tick();
		camera.tick();
	}

	public void stop(){
		EntityAstroid.entities.clear();
		tickLoop.stop();
		renderLoop.stop();
		getJframe().setEnabled(false);
		getJframe().setVisible(false);
	}

	public GameWindow getWindow() {
		return window;
	}

	public void pauseThreads() {
		try{
		tickLoop.tickRunning = false;
		tickLoop.stop();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}	
	
	public void resumeThreads() {
		tickLoop.start(this);
		
	}
}
