package com.troy.junejourney.util;

import java.awt.*;
import java.util.*;

import com.troy.junejourney.game.*;

public class ExplosionManager implements IGame{

	@Override
	public void tick(int tickCount) {
		Iterator<Explosion> i = Explosion.explosionsTick.iterator();
		while(i.hasNext()){
			i.next().tick(tickCount);
		}
	}

	@Override
	public void render(Graphics g) {
		Explosion.explosionsRender = new ArrayList<Explosion>(Explosion.explosionsTick);
		Iterator<Explosion> i = Explosion.explosionsRender.iterator();

		
		while(i.hasNext()){
			i.next().render(g);
		}
	}

}
