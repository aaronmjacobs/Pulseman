package edu.calpoly.csc.pulseman;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ConfigurableEmitter.Range;
import org.newdawn.slick.particles.ParticleSystem;
import org.newdawn.slick.particles.ConfigurableEmitter.ColorRecord;

import edu.calpoly.csc.pulseman.gameobject.Entity.Direction;

public class WinScreen implements GameInterface {
	public static final int PS_SPEED = 3;
	public static final int PS_PARTICLES = 1500;
	
	private ParticleSystem ps;
	private ParticleSystem ps2;
	private ParticleSystem ps3;
	private Image player;
	private int psX, psY;
	private int playerX, playerY;
	
	
	@Override
	public void render(GameContainer gc, Graphics g) {
		ps.render(psX, psY);
		ps2.render(psX, psY);
		g.drawImage(player, playerX, playerY);
		
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		psX = (int) Main.getScreenWidth() / 2;
		psY = (int) Main.getScreenHeight() / 2;

		player = new Image("res/Player/MonkJump5.png");
		playerX = psX;
		playerY = psY + player.getHeight();
		
		ps = new ParticleSystem(new Image("res/orb.png"), PS_PARTICLES);
		ps2 = new ParticleSystem(new Image("res/orb.png"), PS_PARTICLES);
		ConfigurableEmitter emitter = new ConfigurableEmitter("winEmitter");
		emitter.useAdditive = true;
		emitter.useOriented = true;
		emitter.initialLife.setMin(15000.0f);
		emitter.initialLife.setMax(15000.0f);
		emitter.spawnCount.setMax(30.0f);
		emitter.spawnCount.setMax(40.0f);
		emitter.length.setMax(100.0f);
		emitter.length.setMin(80.0f);
		ArrayList<ColorRecord> colors = new ArrayList<ColorRecord>();
		colors.add(emitter.new ColorRecord(0.0f, new Color(0.0f, 0.0f, 1.0f)));
		colors.add(emitter.new ColorRecord(0.2f, new Color(1.0f, 0.0f, 0.0f)));
		colors.add(emitter.new ColorRecord(0.4f, new Color(1.0f, 1.0f, 1.0f)));
		colors.add(emitter.new ColorRecord(0.6f, new Color(0.0f, 1.0f, 0.0f)));
		colors.add(emitter.new ColorRecord(0.8f, new Color(0.0f, 1.0f, 1.0f)));
		colors.add(emitter.new ColorRecord(1.0f, new Color(0.0f, 0.0f, 1.0f)));
		emitter.colors = colors;
		ps.addEmitter(emitter);
		ps2.addEmitter(emitter);
		// offset second particle system
		ps2.update(2000);
		
	}

	@Override
	public void update(GameContainer gc, int dt) throws SlickException {
		ps.update(dt);	
		ps2.update(dt * 2);
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
