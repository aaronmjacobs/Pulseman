package edu.calpoly.csc.pulseman;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class StartMenu implements GameInterface {
	Image menuButton;
	Image menuTitle;
	
	@Override
	public void render(GameContainer gc, Graphics g) {
		g.drawImage(menuTitle, 200, 0);
		g.drawImage(menuButton, 200, 400);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		menuButton = new Image("res/subtitle.png");
		menuTitle = new Image("res/title.png");
		
	}

	@Override
	public void update(GameContainer gc, int dt) {
		if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON))
			Main.setState(Main.GameState.GAME);
	}

}
