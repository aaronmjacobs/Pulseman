package edu.calpoly.csc.pulseman.gameobject;

import org.newdawn.slick.geom.Rectangle;

public interface ObjectBehavior {
	void update(Rectangle box, int dt);
	public void updateOther(Rectangle box);
}
