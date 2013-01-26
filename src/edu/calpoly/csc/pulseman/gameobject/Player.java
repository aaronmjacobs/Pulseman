package edu.calpoly.csc.pulseman.gameobject;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

import edu.calpoly.csc.pulseman.Main;
import edu.calpoly.csc.pulseman.Main.GameState;

public class Player extends Entity
{
	private static Image image;
	private final static float PLAYER_SPEED = 0.25f;

	public static void init(Image image)
	{
		Player.image = image;
	}

	public Player(int x, int y)
	{
		super(new Rectangle(x, y, image.getWidth(), image.getHeight()));
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
			position.x -= PLAYER_SPEED * delta;
		}
		if(input.isKeyDown(Input.KEY_D))
		{
			position.x += PLAYER_SPEED * delta;
		}

		if(input.isKeyDown(Input.KEY_SPACE) && floor != null)
		{
			velocity.y -= 0.3f;
		}

		acceleration.y = 0.00045f;

		super.update(delta);
	}

	@Override
	protected void handleCollision(Collidable collidable, Rectangle oldBounds)
	{
		if(collidable instanceof KillingObstacle)
		{
			Main.setState(GameState.GAMEOVER);
		}
		else
		{
			super.handleCollision(collidable, oldBounds);
		}
	}
}
