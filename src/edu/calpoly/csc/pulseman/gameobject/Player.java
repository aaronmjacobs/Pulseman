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
		image.draw(position.x, position.y, 1);
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

		acceleration.y = 0.00002f * delta;

		super.update(delta);
	}
}
