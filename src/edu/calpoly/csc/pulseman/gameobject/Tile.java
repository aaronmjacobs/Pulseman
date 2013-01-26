package edu.calpoly.csc.pulseman.gameobject;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

<<<<<<< HEAD
public class Tile implements Collidable {
	private static Image image;
	private Rectangle hitBox;
	private Vector2f position;
	
	
	static public void init(Image image) {
		Tile.image = image;
	}
	
=======
public class Tile extends Collidable {
>>>>>>> Began implementation of Entity class
	public Tile(int x, int y) {
		hitBox = new Rectangle(x, y, image.getWidth(), image.getHeight());
		position = new Vector2f(x, y);
	}
	
	
	
	@Override
	public void render(GameContainer gc, Graphics g) {
		g.drawImage(image, position.x, position.y);
		
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
