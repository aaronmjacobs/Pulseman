package edu.calpoly.csc.pulseman.gameobject;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class KillingObstacle extends Collidable implements Murderer
{
	static Map<String, Image> imageMap = new HashMap<String, Image>();
	ObjectBehavior behavior;
	boolean timeAffectable;
	private Image image;

	public static void init(String imageName) throws SlickException
	{
		imageMap.put(imageName, new Image(imageName));
	}

	public KillingObstacle(String imgName, int x, int y, ObjectBehavior behavior, 
			boolean timeAffectable)
	{
		super(new Rectangle(x, y, imageMap.get(imgName).getWidth(), imageMap.get(imgName).getHeight()));
		this.timeAffectable = timeAffectable;
		image = imageMap.get(imgName);
		this.behavior = behavior;
	}

	@Override
	public void render(GameContainer gc, Graphics g)
	{
		g.drawImage(image, getHitBox().getX(), getHitBox().getY());
	}

	@Override
	public void update(GameContainer gc, int delta)
	{
		behavior.update(getHitBox(), delta);
	}

	@Override
	public boolean isAffectedByPulse() {
		return timeAffectable;
	}
}
