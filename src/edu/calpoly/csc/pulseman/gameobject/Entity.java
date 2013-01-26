package edu.calpoly.csc.pulseman.gameobject;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import edu.calpoly.csc.pulseman.World;

public abstract class Entity extends Collidable
{
	private Vector2f position, velocity, acceleration;

	public Entity()
	{
		position = new Vector2f();
		velocity = new Vector2f();
		acceleration = new Vector2f();
	}

	public void update(int delta)
	{
		// Update velocity
		velocity.x += acceleration.x * delta;
		velocity.y += acceleration.y * delta;
		
		// Update position
		position.x += velocity.x * delta;
		position.y += velocity.y * delta;
		
		World.getWorld().getCollidables();
	}

	public void handleTileCollision(Tile tile)
	{
		Rectangle collision = getCollision(this.bounds, tile.bounds);

		// If it is a vertical collision
		if(collision.getHeight() >= collision.getWidth())
		{
			// If we're falling on the object
			if(velocity.y >= 0)
			{
				position.y -= collision.getHeight();
			}
			else
			// If we're 'hitting our head' on the object
			{
				position.y += collision.getHeight();
			}
		}
		else
		// If it is a horizontal collision
		{
			// If we're hitting an object to our right
			if(velocity.x >= 0)
			{
				position.x -= collision.getWidth();
			}
			else
			// If we're hitting an object to our left
			{
				position.x += collision.getWidth();
			}
		}
	}
}
