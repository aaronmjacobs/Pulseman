package edu.calpoly.csc.pulseman.gameobject;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ConfigurableEmitter.ColorRecord;
import org.newdawn.slick.particles.ParticleSystem;

public class MovingTile extends Tile
{
	private static Image psImage;
	private ObjectBehavior behavior;
	private ParticleSystem ps;
	private boolean timeAffected;

	public static void init(Image image) {
		psImage = image;
	}
	
	public MovingTile(int x, int y, ObjectBehavior behavior, boolean timeAffected)
	{
		super(x, y);
		this.timeAffected = timeAffected;
		this.behavior = behavior;
		if (timeAffected) {
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
	public void render(GameContainer gc, Graphics g) {
		if (timeAffected)
			ps.render(getHitBox().getCenterX(), getHitBox().getCenterY());
		super.render(gc, g);
	}
	

	public void update(GameContainer gc, int delta)
	{
		behavior.update(getHitBox(), delta);
		if (timeAffected)
			ps.update(delta);
	}

	public void updateOther(Rectangle rect)
	{
		behavior.updateOther(rect);
	}
	
	@Override
	public boolean isAffectedByPulse() {
		return timeAffected;
	}
}
