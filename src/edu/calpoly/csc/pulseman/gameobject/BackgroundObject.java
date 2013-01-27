package edu.calpoly.csc.pulseman.gameobject;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class BackgroundObject implements GameObject {
	Animation anim;
	int x, y;
	public BackgroundObject(Animation anim, int x, int y) {
		this.anim = anim;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) {
		g.drawImage(anim.getCurrentFrame(), x, y);
		
	}

	@Override
	public void update(GameContainer gc, int delta) {
		anim.update(delta);
		
	}

	@Override
	public boolean isAffectedByPulse() {
		return true;
	}

}
