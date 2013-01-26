package edu.calpoly.csc.pulseman.gameobject;

import org.newdawn.slick.geom.Rectangle;

public interface Collidable extends GameObject {
	public Rectangle getHitBox();
}
