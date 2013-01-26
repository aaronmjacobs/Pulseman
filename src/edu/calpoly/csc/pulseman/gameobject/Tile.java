package edu.calpoly.csc.pulseman.gameobject;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public class Tile implements Collidable {
	private static Image image;
	private Rectangle hitBox;
	
	
	static public void init(Image image) {
		Tile.image = image;
	}
	
	public Tile(int x, int y) {
		hitBox = new Rectangle(x, y, image.getWidth(), image.getHeight());
	}
	
	
	
	@Override
	public void render(GameContainer gc, Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer gc, int delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle getHitBox() {
		return hitBox;
	}

}
