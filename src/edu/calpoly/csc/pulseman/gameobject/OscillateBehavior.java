package edu.calpoly.csc.pulseman.gameobject;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class OscillateBehavior implements ObjectBehavior
{
	private Vector2f leftRange, rightRange, positionChange;
	private float speed;
	private float param;

	public OscillateBehavior(int x, int y, float speed, Vector2f range)
	{
		leftRange = new Vector2f(x, y);
		rightRange = new Vector2f(x + range.x, y + range.y);
		positionChange = new Vector2f();
		this.speed = speed;
		param = 0.0f;
	}

	@Override
	public void update(Rectangle box, int dt)
	{
		Vector2f oldPos = leftRange.copy().scale(1.0f - param).add(rightRange.copy().scale(param));

		param += speed * (float)dt / 1000.0f;
		if(param > 1.0f)
		{
			speed = -speed;
			param = 1.0f;
		}
		else if(param < 0.0f)
		{
			speed = -speed;
			param = 0.0f;
		}
		
		Vector2f newPos = leftRange.copy().scale(1.0f - param).add(rightRange.copy().scale(param));
		box.setLocation(newPos);

		positionChange = newPos.sub(oldPos);
	}

	public void updateOther(Rectangle box)
	{
		box.setX(box.getX() + positionChange.x);
		box.setY(box.getY() + positionChange.y);
		System.out.println(positionChange.y);
	}

}
