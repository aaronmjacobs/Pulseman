package edu.calpoly.csc.pulseman.gameobject;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public abstract class Entity extends Collidable
{
	private Vector2f position, velocity, acceleration;

	public void handleTileCollision(Tile tile)
	{
		Rectangle collision = getCollision(this.bounds, tile.bounds);
	}
}
