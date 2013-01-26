package edu.calpoly.csc.pulseman.gameobject;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public class Enemy extends Entity {

	private static Image image;
	private ObjectBehavior behavior;
	
	public static void init(Image image)
	{
		Enemy.image = image;
	}
	
	public Enemy(int x, int y, ObjectBehavior behavior) {
		super(new Rectangle(x, y, image.getWidth(), image.getHeight()));
		this.behavior = behavior;
	}

	private final static float PLAYER_SPEED = 0.25f;
	
	public void render(GameContainer gc, Graphics g) {
		g.drawImage(image, position.x, position.y);
		System.out.println("There, for grace now go");
	}

	public void update(GameContainer gc, int delta) {
		behavior.update(getHitBox(), delta);
		
		//super.update(delta);
	}

}
