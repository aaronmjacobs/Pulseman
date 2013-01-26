package edu.calpoly.csc.pulseman.gameobject;

import org.newdawn.slick.GameContainer;

public class MovingTile extends Tile {
	private int leftRange, rightRange;
	private float speed;
	
	public MovingTile(int x, int y, int range, float speed) {
		super(x, y);
		leftRange = x;
		rightRange = x + range;
		this.speed = speed;
	}
	
	public void update(GameContainer gc, int delta) {
		int x = (int)getHitBox().getX();
		if ( x < leftRange) {
			getHitBox().setX(leftRange);
			speed = -speed;
		} else if (x > rightRange) {
			getHitBox().setX(rightRange);
			speed = -speed;
		}
		getHitBox().setX(x + speed);
	}
}
