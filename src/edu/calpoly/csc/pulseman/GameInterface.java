package edu.calpoly.csc.pulseman;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public interface GameInterface {
	public void render(GameContainer gc, Graphics g);

	public void init(GameContainer gc);

	public void update(GameContainer gc, int dt);
}
