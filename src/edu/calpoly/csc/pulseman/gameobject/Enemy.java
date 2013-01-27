package edu.calpoly.csc.pulseman.gameobject;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import edu.calpoly.csc.pulseman.World;

public class Enemy extends Entity implements Murderer
{
	private static Image image;
	private Direction direction;

	public static void init(Image image)
	{
		Enemy.image = image;
	}

	public Enemy(int x, int y)
	{
		super(new Rectangle(x, y, image.getWidth(), image.getHeight()));
		direction = Direction.LEFT;
	}

	public void render(GameContainer gc, Graphics g)
	{
		g.drawImage(image, position.x, position.y);
	}

	public void update(GameContainer gc, int delta)
	{
		if(direction == Direction.LEFT)
		{
			boolean leftIntersect = false, bottomLeftIntersect = false;
			Rectangle left = new Rectangle(bounds.getX() - (bounds.getWidth() / 6), bounds.getY() - (bounds.getHeight() / 4), bounds.getWidth(), bounds.getHeight());
			Rectangle bottomLeft = new Rectangle(left.getX(), left.getY() + left.getHeight(), left.getWidth(), left.getHeight());

			List<Collidable> collidables = World.getWorld().getCollidables();
			for(int i = 0; i < collidables.size(); ++i)
			{
				if(left.intersects(collidables.get(i).bounds))
				{
					leftIntersect = true;
					break;
				}

				if(bottomLeft.intersects(collidables.get(i).bounds))
				{
					bottomLeftIntersect = true;
				}
			}

			if(leftIntersect || (!bottomLeftIntersect && floor != null))
			{
				direction = Direction.RIGHT;
			}
		}
		else
		{
			boolean rightIntersect = false, bottomRightIntersect = false;
			Rectangle right = new Rectangle(bounds.getX() + (bounds.getWidth() / 6), bounds.getY() - (bounds.getHeight() / 4), bounds.getWidth(), bounds.getHeight());
			Rectangle bottomRight = new Rectangle(right.getX(), right.getY() + right.getHeight(), right.getWidth(), right.getHeight());

			List<Collidable> collidables = World.getWorld().getCollidables();
			for(int i = 0; i < collidables.size(); ++i)
			{
				if(right.intersects(collidables.get(i).bounds))
				{
					rightIntersect = true;
					break;
				}

				if(bottomRight.intersects(collidables.get(i).bounds))
				{
					bottomRightIntersect = true;
				}
			}

			if(rightIntersect || (!bottomRightIntersect && floor != null))
			{
				direction = Direction.LEFT;
			}
		}

		if(direction == Direction.LEFT)
		{
			position.x -= Player.PLAYER_SPEED * delta;
		}
		else
		{
			position.x += Player.PLAYER_SPEED * delta;
		}

		super.update(gc, delta);
	}
}
