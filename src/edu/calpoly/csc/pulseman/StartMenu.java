package edu.calpoly.csc.pulseman;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class StartMenu implements GameInterface {
	Image menuButton;
	Image menuTitle;
	private final float[] buttonLoc = {200, 400};
	
	@Override
	public void render(GameContainer gc, Graphics g) {
		g.drawImage(menuTitle, 200, 0);
		g.drawImage(menuButton, buttonLoc[0], buttonLoc[1]);
		g.drawString("You are a meditating monk. Head towards the light.\n" +
					 "Use the beat to control nature's speed.", 
		 Main.getScreenWidth() / 2, Main.getScreenHeight() / 2);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		menuButton = new Image("res/subtitle.png");
		menuTitle = new Image("res/title.png");
		
	}

	@Override
	public void update(GameContainer gc, int dt) {
		Input input = gc.getInput();
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			int x = input.getMouseX();
			int y = input.getMouseY();
			if (x >= buttonLoc[0] && x <= buttonLoc[0] + menuButton.getWidth()
			 && y >= buttonLoc[1] && y <= buttonLoc[1] + menuButton.getHeight())
				Main.setState(Main.GameState.GAME);
		}
	}

}
