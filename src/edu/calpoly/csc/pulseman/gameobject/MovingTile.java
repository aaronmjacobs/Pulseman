package edu.calpoly.csc.pulseman.gameobject;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

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
	
	public Vector2f getPositionChange()
	{
		return behavior.getPositionChange();
	}
	
	@Override
	public boolean isAffectedByPulse() {
		return timeAffected;
	}
}
