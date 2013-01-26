package edu.calpoly.csc.pulseman.gameobject;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;

public class MovingTile extends Tile
{
	private ObjectBehavior behavior;

	public MovingTile(int x, int y, ObjectBehavior behavior)
	{
		super(x, y);
		this.behavior = behavior;
	}

	public void update(GameContainer gc, int delta)
	{
		behavior.update(getHitBox(), delta);
	}

	public void updateOther(Rectangle rect)
	{
		behavior.updateOther(rect);
	}
}
