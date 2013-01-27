package edu.calpoly.csc.pulseman.gameobject;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ConfigurableEmitter.ColorRecord;
import org.newdawn.slick.particles.ParticleSystem;

public class MovingTile extends Tile
{
	private ObjectBehavior behavior;
	private boolean timeAffected;

	public MovingTile(int x, int y, ObjectBehavior behavior, boolean timeAffected)
	{
		super(x, y, timeAffected);
		this.timeAffected = timeAffected;
		this.behavior = behavior;
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) {
		super.render(gc, g);
	}
	

	public void update(GameContainer gc, int delta)
	{
		super.update(gc, delta);
		behavior.update(getHitBox(), delta);
	}

	public void updateOther(Rectangle rect)
	{
		behavior.updateOther(rect);
	}
	
	@Override
	public boolean isAffectedByPulse() {
		return timeAffected;
	}
}
