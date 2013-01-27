package edu.calpoly.csc.pulseman.gameobject;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleSystem;
import org.newdawn.slick.particles.ConfigurableEmitter.ColorRecord;

public abstract class Collidable implements GameObject
{
	protected Rectangle bounds;
	private static Image psImage;
	private ParticleSystem ps;
	
	public static void init(Image image) {
		psImage = image;
	}
	
	public Collidable(Rectangle rect, boolean affectedByPulse)
	{
		bounds = new Rectangle(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
		if (affectedByPulse) {
			ps = new ParticleSystem(psImage);
		
			ConfigurableEmitter emitter = new ConfigurableEmitter("emitter");
			emitter.useAdditive = true;
			emitter.useOriented = true;
			emitter.spawnCount.setMax(8.0f);
			emitter.spawnCount.setMax(10.0f);
			emitter.length.setMax(10.0f);
			emitter.length.setMin(8.0f);
			ArrayList<ColorRecord> colors = new ArrayList<ColorRecord>();
			colors.add(emitter.new ColorRecord(0.0f, new Color(0.2f, 0.2f, 1.0f)));
			colors.add(emitter.new ColorRecord(1.0f, new Color(0.2f, 0.2f, 1.0f)));
			emitter.colors = colors;
			ps.addEmitter(emitter);
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g)
	{
		if (isAffectedByPulse()) {
			ps.render(getHitBox().getCenterX(), getHitBox().getCenterY());
		}
	}
	
	public void update(GameContainer gc, int delta)
	{
		if (isAffectedByPulse())
			ps.update(delta);
	}
		
	public Rectangle getHitBox()
	{
		return bounds;
	}

	/**
	 * Generates a Rectangle representing the collision area of two rectangles
	 * 
	 * @param r1 The first rectangle
	 * @param r2 The second rectangle
	 * @return The collision Rectangle of the rectangles
	 */
	protected static Rectangle getCollision(Rectangle r1, Rectangle r2)
	{
		float x = Math.max(r1.getMinX(), r2.getMinX());
		float y = Math.max(r1.getMinY(), r2.getMinY());
		float width = Math.min(r1.getMaxX(), r2.getMaxX()) - x;
		float height = Math.min(r1.getMaxY(), r2.getMaxY()) - y;

		return new Rectangle(x, y, width, height);
	}
}
