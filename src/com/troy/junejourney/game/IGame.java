package com.troy.junejourney.game;

import java.awt.Graphics;
/**This interface defines two methoids render and tick.
 *The methoid render passes a Graphics object for classes to draw their content to.
 *The tick methoid updates positions keybindings ect
 */
public interface IGame {
	public void tick(int tickCount);
	public void render(Graphics g);

}
