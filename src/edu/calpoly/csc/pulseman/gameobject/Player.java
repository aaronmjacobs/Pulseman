package edu.calpoly.csc.pulseman.gameobject;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

public class Player extends Entity
{
	private static Image image;
	private final static float PLAYER_SPEED = 2.0f;

	private boolean jumping;

	public static void init(Image image)
	{
		Player.image = image;
	}

	public Player(int x, int y)
	{
		super(new Rectangle(x, y, image.getWidth(), image.getHeight()));
		jumping = true;
	}

	@Override
	public void render(GameContainer gc, Graphics g)
	{
		g.drawImage(image, position.x, position.y);
	}

	@Override
	public void update(GameContainer gc, int delta)
	{
		Input input = gc.getInput();

		if(input.isKeyDown(Input.KEY_A))
		{
			position.x -= PLAYER_SPEED;
		}
		if(input.isKeyDown(Input.KEY_D))
		{
			position.x += PLAYER_SPEED;
		}

		if(input.isKeyDown(Input.KEY_SPACE) && !jumping)
		{
			velocity.y -= 0.02f * delta;
			jumping = true;
		}

		acceleration.y = 0.00002f * delta;

		super.update(delta);
	}

	@Override
	protected int handleCollision(Collidable collidable)
	{
		int ret = super.handleCollision(collidable);

		if(ret == BOTTOM)
		{
			jumping = false;
		}

		return ret;
	}
}
