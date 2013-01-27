package edu.calpoly.csc.pulseman.gameobject;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleEmitter;
import org.newdawn.slick.particles.ParticleSystem;
import org.newdawn.slick.particles.effects.FireEmitter;

public class Goal extends Collidable {
	public static Image portalImage;
	
	public Goal(int x, int y) throws SlickException {
		super(new Rectangle(x, y, portalImage.getWidth(), portalImage.getHeight()), true);
	}

	public static void init(Image image) {
		Goal.portalImage = image;
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) {
		super.render(gc, g);
		g.drawImage(portalImage, getHitBox().getX(), getHitBox().getY());
	}

	@Override
	public void update(GameContainer gc, int delta) {
		super.update(gc, delta);
	}

	@Override
	public boolean isAffectedByPulse() {
		return true;
	}

}
