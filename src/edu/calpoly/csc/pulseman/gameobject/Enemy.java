package edu.calpoly.csc.pulseman.gameobject;

import java.util.List;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import edu.calpoly.csc.pulseman.World;

public class Enemy extends Entity implements Murderer
{
	public static final float ENEMY_SPEED = Player.PLAYER_SPEED * 1.25f;

	private boolean affectByPulse;
	private static Image image;
	private static Animation anim;
	private Direction direction;

	public static void init(Animation animation)
	{
		Enemy.anim = animation;
		//Enemy.image = image;
	}

	public Enemy(int x, int y, boolean affectByPulse)
	{
		super(new Rectangle(x, y, anim.getWidth(), anim.getHeight()));
		this.affectByPulse = affectByPulse;
		direction = Direction.LEFT;
	}

	public void render(GameContainer gc, Graphics g)
	{
		g.drawImage(anim.getCurrentFrame(), position.x, position.y);
		//g.drawImage(image, position.x, position.y);
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
			position.x -= ENEMY_SPEED * delta;
		}
		else
		{
			position.x += ENEMY_SPEED * delta;
		}

		super.update(gc, delta);
	}

	@Override
	public boolean isAffectedByPulse() {
		return affectByPulse;
	}
}
