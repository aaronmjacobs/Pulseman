package edu.calpoly.csc.pulseman.gameobject;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class OscillateBehavior implements ObjectBehavior {
	private Vector2f leftRange, rightRange;
	private float speed;
	private float param;
	
	public OscillateBehavior(int x, int y, float speed, Vector2f range) {
		leftRange = new Vector2f(x, y);
		rightRange = new Vector2f(x + range.x, y + range.y);
		this.speed = speed;
		param = 0.0f;
	}
	
	@Override
	public void update(Rectangle box, int dt) {
		param += speed;
		if (param > 1.0f) {
			speed = -speed;
			param = 1.0f;
		} else if (param < 0.0f) {
			speed = -speed;
			param = 0.0f;
		}
		Vector2f newPos = leftRange.copy().scale(1.0f - param).add(rightRange.copy().scale(param));
		box.setLocation(newPos);	
	}

}
