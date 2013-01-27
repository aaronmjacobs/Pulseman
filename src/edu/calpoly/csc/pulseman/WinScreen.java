package edu.calpoly.csc.pulseman;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleSystem;
import org.newdawn.slick.particles.ConfigurableEmitter.ColorRecord;

import edu.calpoly.csc.pulseman.gameobject.Entity.Direction;

public class WinScreen implements GameInterface {
	public static final int PS_SPEED = 3;
	
	private ParticleSystem ps;
	private Image player;
	private int psX, psY;
	
	
	
	@Override
	public void render(GameContainer gc, Graphics g) {
		ps.render(psX, psY);
		g.drawImage(player, psX, psY + player.getHeight());
		
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		psX = (int) Main.getScreenWidth() / 2;
		psY = (int) Main.getScreenHeight() / 2;
		
		player = new Image("res/Player/MonkJump5.png");
		
		ps = new ParticleSystem(new Image("res/orb.png"));
		ConfigurableEmitter emitter = new ConfigurableEmitter("winEmitter");
		emitter.useAdditive = true;
		emitter.useOriented = true;
		emitter.spawnCount.setMax(30.0f);
		emitter.spawnCount.setMax(40.0f);
		emitter.length.setMax(40.0f);
		emitter.length.setMin(30.0f);
		ArrayList<ColorRecord> colors = new ArrayList<ColorRecord>();
		colors.add(emitter.new ColorRecord(0.0f, new Color(0.2f, 0.2f, 1.0f)));
		colors.add(emitter.new ColorRecord(1.0f, new Color(0.0f, 0.0f, 1.0f)));
		emitter.colors = colors;
		ps.addEmitter(emitter);
		
	}

	@Override
	public void update(GameContainer gc, int dt) throws SlickException {
		ps.update(dt);	
		Input input = gc.getInput();
		if(input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT))
		{
			psX -= PS_SPEED;
		}
		if(input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT))
		{
			psX += PS_SPEED;
		}
		if(input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP))
		{
			psY -= PS_SPEED;
		}
		if(input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_DOWN))
		{
			psY += PS_SPEED;
		}

	}
	
}
