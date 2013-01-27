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
	private static Image image;
	private ParticleSystem ps;
	
	public Goal(int x, int y) throws SlickException {
		super(new Rectangle(x, y, image.getWidth(), image.getHeight()));
		ps = new ParticleSystem(new Image("res/orb.png"));
		/*ConfigurableEmitter emitter = new ConfigurableEmitter("emitter");
		emitter.useAdditive = true;
		emitter.useOriented = true;
		emitter.spawnCount.setMax(20.0f);
		emitter.spawnCount.setMax(10.0f);
		ps.addEmitter(emitter);*/
		ps.addEmitter(new FireEmitter());
	}

	public static void init(Image image) {
		Goal.image = image;
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) {
		//g.drawImage(image, getHitBox().getX(), getHitBox().getY());
		ps.render(getHitBox().getX() + getHitBox().getWidth() / 2.0f, 
				getHitBox().getY() + getHitBox().getHeight() / 2.0f);
	}

	@Override
	public void update(GameContainer gc, int delta) {
		ps.update(delta);
	}

	@Override
	public boolean isAffectedByPulse() {
		return true;
	}

}
