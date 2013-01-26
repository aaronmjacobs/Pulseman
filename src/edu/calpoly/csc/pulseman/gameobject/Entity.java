package edu.calpoly.csc.pulseman.gameobject;

import java.util.List;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import edu.calpoly.csc.pulseman.World;

public abstract class Entity extends Collidable
{
	protected static final int TOP = 0, BOTTOM = 1, LEFT = 2, RIGHT = 3;

	protected Vector2f position, velocity, acceleration;

	public Entity(Rectangle rect)
	{
		super(rect);
		position = new Vector2f(rect.getX(), rect.getY());
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

		List<Collidable> collidables = World.getWorld().getCollidables();
		for(int i = 0; i < collidables.size(); ++i)
		{
			if(bounds.intersects(collidables.get(i).bounds))
			{
				handleCollision(collidables.get(i));
			}
		}

		bounds.setLocation(position.x, position.y);
	}

	protected int handleCollision(Collidable collidable)
	{
		int ret;

		Rectangle collision = getCollision(this.bounds, collidable.bounds);
		// System.out.println(collision);
		float distToTop = Math.abs((this.bounds.getMaxY() - collidable.bounds.getMinY()));
		float distToBottom = Math.abs((this.bounds.getMinY() - collidable.bounds.getMaxY()));
		float distToLeft = Math.abs((this.bounds.getMaxX() - collidable.bounds.getMinX()));
		float distToRight = Math.abs((this.bounds.getMinX() - collidable.bounds.getMaxX()));

		float min = Math.min(Math.min(distToTop, distToBottom), Math.min(distToLeft, distToRight));

		if(min == distToTop)
		{
			position.y -= collision.getHeight();
			velocity.y = 0.0f;
			ret = BOTTOM;
		}
		else if(min == distToBottom)
		{
			position.y += collision.getHeight();
			velocity.y = 0.0f;
			ret = TOP;
		}
		else if(min == distToLeft)
		{
			position.x -= collision.getWidth();
			velocity.x = 0.0f;
			ret = RIGHT;
		}
		else
		{
			position.x += collision.getWidth();
			velocity.x = 0.0f;
			ret = LEFT;
		}

		bounds.setLocation(position.x, position.y);

		return ret;
	}
}
