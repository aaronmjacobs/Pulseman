package edu.calpoly.csc.pulseman.gameobject;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public interface ObjectBehavior {
	void update(Rectangle box, int dt);
	public void updateOther(Rectangle box);
	public Vector2f getPositionChange();
}
