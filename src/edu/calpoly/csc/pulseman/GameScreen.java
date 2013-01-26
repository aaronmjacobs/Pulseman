package edu.calpoly.csc.pulseman;

import java.util.LinkedList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import edu.calpoly.csc.pulseman.gameobject.Player;
import edu.calpoly.csc.pulseman.gameobject.Tile;

public class GameScreen implements GameInterface {
	Player playa;
	
	@Override
	public void render(GameContainer gc, Graphics g) {
		g.drawString("Hello World", 100, 100);
		playa.render(gc, g);
		World.getWorld().render(gc, g);
		
		LinkedList<String> messageQueue = Main.getMessageQueue();
		synchronized(messageQueue)
		{
			for(int i = 0; i < messageQueue.size(); ++i)
			{
				g.drawString(messageQueue.get(i), 100, 150 + (i * 50));
			}
		}
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		Player.init(new Image("res/brick.png"));
		playa = new Player(200, 200);
		Tile.init(new Image("res/brick.png"));
		World.getWorld().loadLevel("res/level001.bmp");
	}

	@Override
	public void update(GameContainer gc, int dt) {
		playa.update(gc, dt);
	}
	
}
