package com.troy.junejourney.game;

public class TickLoop implements Runnable {
	public Thread tickThread;
	public boolean tickRunning;
	
	public static long beforeTime, afterTime, diff, sleepTime, overSleepTime;
	public static volatile boolean ticking = false;
	public static int delays;
	public static long tickTime = 0;
	int tickCounter = 0;
	Game game;
	
	public void start(Game game){
		this.game = game;
		tickRunning = true;
		tickThread = new Thread(this, "Tick-Thread");
		tickThread.setPriority(Thread.MAX_PRIORITY);
		tickThread.start();
	}
	
	public void stop() {
		tickRunning = false;
		try {
			tickThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}
	
	public void run() {
		try{
			
			int tps = 200;
			double timePerTick = 1000000000 / tps;
			double delta = 0;
			long now, lastTime = System.nanoTime(), timer = 0, 
					  lastTick = System.nanoTime(), thisTick = System.nanoTime();
			tickCounter = 0;
			
			//loop so all entityes get updated
			while(tickRunning){
				//some math to calculate when the next update should be
				now = System.nanoTime();
				delta += (now - lastTime) / timePerTick;
				timer += now - lastTime;
				lastTime = now;
				
				//delta is >= 1 when it is time to update
				if(delta >= 1){
					ticking = true;
					thisTick = System.nanoTime();
					//call the tick methoid in tick whitch updates everything
					game.tick();
					tickCounter++;
					delta--;
					lastTick = thisTick;
					tickTime = (thisTick - lastTick);
				}
				
				
				if(timer >= 1000000000){
					timer = 0;
				}
				ticking = false;
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
			
	}
}
