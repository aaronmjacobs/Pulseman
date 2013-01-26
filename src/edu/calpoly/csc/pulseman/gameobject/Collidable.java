package edu.calpoly.csc.pulseman.gameobject;

import org.newdawn.slick.geom.Rectangle;

public abstract class Collidable implements GameObject
{
	protected Rectangle bounds;

	public Rectangle getHitBox()
	{
		return bounds;
	}
	
	protected static Rectangle getCollision(Rectangle r1, Rectangle r2)
	{
		float x = Math.max(r1.getMinX(), r2.getMinX());
		float y = Math.min(r1.getMaxY(), r2.getMaxY());
		float width = Math.min(r1.getMaxX(), r2.getMaxX()) - x;
		float height = Math.min(r1.getMaxY(), r2.getMaxY()) - y;
		
		return new Rectangle(x, y, width, height);
	}
}
