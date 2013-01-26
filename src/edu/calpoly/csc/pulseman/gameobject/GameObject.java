package edu.calpoly.csc.pulseman.gameobject;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public interface GameObject {
	public void init(Animation anim);
	public void render(GameContainer gc, Graphics g);
	public void update(GameContainer gc, int delta);
}
