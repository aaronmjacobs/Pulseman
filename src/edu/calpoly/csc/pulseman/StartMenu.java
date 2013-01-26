package edu.calpoly.csc.pulseman;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class StartMenu implements GameInterface {

	@Override
	public void render(GameContainer gc, Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GameContainer gc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer gc, int dt) {
		if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON))
			Main.setState(Main.GameState.GAME);
	}

}
