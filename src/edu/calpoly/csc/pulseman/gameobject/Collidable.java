package edu.calpoly.csc.pulseman.gameobject;

import org.newdawn.slick.geom.Rectangle;

public abstract class Collidable implements GameObject
{
	protected Rectangle bounds;
	
	public Collidable(Rectangle rect)
	{
		bounds = new Rectangle(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
	}

	public Rectangle getHitBox()
	{
		return bounds;
	}

	/**
	 * Generates a Rectangle representing the collision area of two rectangles
	 * 
	 * @param r1 The first rectangle
	 * @param r2 The second rectangle
	 * @return The collision Rectangle of the rectangles
	 */
	protected static Rectangle getCollision(Rectangle r1, Rectangle r2)
	{
		float x = Math.max(r1.getMinX(), r2.getMinX());
		float y = Math.max(r1.getMinY(), r2.getMinY());
		float width = Math.min(r1.getMaxX(), r2.getMaxX()) - x;
		float height = Math.min(r1.getMaxY(), r2.getMaxY()) - y;

		return new Rectangle(x, y, width, height);
	}
}
