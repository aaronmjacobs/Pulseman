package edu.calpoly.csc.pulseman.gameobject;

import java.util.List;

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
		Vector2f oldPosition = new Vector2f(position);
		position.x += velocity.x * delta;
		position.y += velocity.y * delta;

		List<Collidable> collidables = World.getWorld().getCollidables();
		for(int i = 0; i < collidables.size(); ++i)
		{
			if(bounds.intersects(collidables.get(i).bounds))
			{
				handleCollision(collidables.get(i), oldPosition);
			}
		}
	}

	private void handleCollision(Collidable collidable, Vector2f oldPosition)
	{
		Rectangle collision = getCollision(this.bounds, collidable.bounds);

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
