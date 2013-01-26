package edu.calpoly.csc.pulseman.gameobject;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Player implements Collidable {
	private static Image image;
	Vector2f position;
	
	public void init(Image image) {
		this.image = image;
		position.x = 200;
		position.y = 200;
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) {
		image.draw(position.x, position.y, 1);
	}

	@Override
	public void update(GameContainer gc, int delta) {
		Input input = gc.getInput();
		 
        if(input.isKeyDown(Input.KEY_A))
        {
        	position.x -= 5;
        }
 
        if(input.isKeyDown(Input.KEY_D))
        {
        	position.x += 5;
        }
 
        if(input.isKeyDown(Input.KEY_W))
        {
            position.y -= 5;
        }
        if(input.isKeyDown(Input.KEY_S))
        {
        	position.y += 5;
        }
	}

	@Override
	public Rectangle getHitBox() {
		// TODO Auto-generated method stub
		return null;
	}

}
