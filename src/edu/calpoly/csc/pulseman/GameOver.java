package edu.calpoly.csc.pulseman;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class GameOver implements GameInterface {
	Image gameoverImage;
	@Override
	public void render(GameContainer gc, Graphics g) {
		g.drawImage(gameoverImage, 0, 200);		
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		 gameoverImage = new Image("res/gameover.png");
	}

	@Override
	public void update(GameContainer gc, int dt) throws SlickException {
		
		if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			World.getWorld().loadLastLevel();
			Main.setState(Main.GameState.GAME);;
		}
		
	}

}
