package edu.calpoly.csc.pulseman;

import java.util.Timer;
import java.util.TimerTask;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class StartMenu implements GameInterface
{
	private class MenuState
	{
		public static final int MAIN = 0, STORY_1 = 1, STORY_2 = 2, INTRO = -1;
	}

	private int state = MenuState.INTRO, nextState;

	private Image menuButton;
	private Image storyButton, nextButton;
	private Image menuBackground, story1, story2;
	private Image connectButton, connectingButton, connectedButton;
	private volatile int countdown = 0;
	private String ip = null;

	private static final float FADE_INCREMENT = 0.03f;
	private float fadeAmount = 0.0f;
	private boolean fadingOut = false, changingState = false;

	private final float[] buttonLoc =
	{ 50, 500 }, connectLoc =
	{ 75, 200 }, storyLoc =
	{ 800, 350 }, nextLoc =
	{ 900, 600 };

	public void setState(int state, boolean fadeInOnly)
	{
		if(fadeInOnly)
		{
			fadeAmount = 1.0f;
		}
		else
		{
			fadingOut = true;
		}
		nextState = state;
		changingState = true;
	}

	@Override
	public void render(GameContainer gc, Graphics g)
	{
		if(state == MenuState.MAIN)
		{
			g.drawImage(menuBackground, 0, 0);
			g.drawImage(menuButton, buttonLoc[0], buttonLoc[1]);
			g.drawImage(storyButton, storyLoc[0], storyLoc[1]);

			g.setColor(Color.white);
			if(Main.getAndroidState() == Main.AndroidStates.NOT_CONNECTED)
			{
				g.drawImage(connectButton, connectLoc[0], connectLoc[1]);
			}
			else if(Main.getAndroidState() == Main.AndroidStates.CONNECTING)
			{
				g.drawImage(connectingButton, connectLoc[0], connectLoc[1]);

				g.drawString(String.valueOf(countdown), connectLoc[0] + connectingButton.getWidth(), connectLoc[1] + connectingButton.getHeight() / 2);
			}
			else
			{
				g.drawImage(connectedButton, connectLoc[0], connectLoc[1]);
			}

			if(ip != null)
			{
				g.drawString("Your IP address is: " + ip, 100, 170);
			}
		}
		else if(state == MenuState.STORY_1)
		{
			g.drawImage(story1, 0, 0);
			g.drawImage(nextButton, nextLoc[0], nextLoc[1]);
		}
		else if(state == MenuState.STORY_2)
		{
			g.drawImage(story2, 0, 0);
			g.drawImage(nextButton, nextLoc[0], nextLoc[1]);
		}
		
		if(fadeAmount >= 1.0f)
		{
			state = nextState;
			fadingOut = false;
		}
		
		if(fadingOut)
		{
			fadeAmount += FADE_INCREMENT;
			
			if(fadeAmount > 1.0f)
			{
				fadeAmount = 1.0f;
			}
		}
		else if(fadeAmount > 0.0f)
		{
			fadeAmount -= FADE_INCREMENT;
			
			if(fadeAmount < 0.0f)
			{
				fadeAmount = 0.0f;
				changingState = false;
			}
		}
		
		if(fadeAmount > 0.0f)
		{
			g.setColor(new Color(0.0f, 0.0f, 0.0f, fadeAmount));
			g.fillRect(0.0f, 0.0f, gc.getWidth(), gc.getHeight());
		}
	}

	@Override
	public void init(GameContainer gc) throws SlickException
	{
		menuButton = new Image("res/buttons/start.png");
		connectButton = new Image("res/buttons/connect.png");
		connectingButton = new Image("res/buttons/waiting.png");
		connectedButton = new Image("res/buttons/connected.png");
		menuBackground = new Image("res/menu/main.png");
		story1 = new Image("res/menu/story1.png");
		story2 = new Image("res/menu/story2.png");
		storyButton = new Image("res/buttons/story.png");
		nextButton = new Image("res/buttons/next.png");

		ip = Main.getIPAddress();
	}

	@Override
	public void update(GameContainer gc, int dt)
	{
		Input input = gc.getInput();

		if(state == MenuState.INTRO && !changingState)
		{
			setState(MenuState.MAIN, true);
		}
		else if(state == MenuState.MAIN)
		{
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			{
				int x = input.getMouseX();
				int y = input.getMouseY();
				if(x >= buttonLoc[0] && x <= buttonLoc[0] + menuButton.getWidth() && y >= buttonLoc[1] && y <= buttonLoc[1] + menuButton.getHeight())
				{
					Main.setState(Main.GameState.GAME);
				}

				if(Main.getAndroidState() == Main.AndroidStates.NOT_CONNECTED && (x >= connectLoc[0] && x <= connectLoc[0] + connectButton.getWidth() && y >= connectLoc[1] && y <= connectLoc[1] + connectButton.getHeight()))
				{
					listenForConnection();

					countdown = MessageHandler.SOCKET_TIMEOUT / 1000 + 1;
					new Timer("Countdown Timer").schedule(new TimerTask()
					{
						@Override
						public void run()
						{
							if(--countdown < 0)
							{
								cancel();
							}
						}
					}, 0, 1000);
				}
				else if((x >= storyLoc[0] && x <= storyLoc[0] + storyButton.getWidth() && y >= storyLoc[1] && y <= storyLoc[1] + storyButton.getHeight()))
				{
					setState(MenuState.STORY_1, false);
				}
			}
		}
		else if(state == MenuState.STORY_1)
		{
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			{
				int x = input.getMouseX();
				int y = input.getMouseY();
				
				if((x >= nextLoc[0] && x <= nextLoc[0] + nextButton.getWidth() && y >= nextLoc[1] && y <= nextLoc[1] + nextButton.getHeight()))
				{
					setState(MenuState.STORY_2, false);
				}
			}
		}
		else if(state == MenuState.STORY_2)
		{
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			{
				int x = input.getMouseX();
				int y = input.getMouseY();
				
				if((x >= nextLoc[0] && x <= nextLoc[0] + nextButton.getWidth() && y >= nextLoc[1] && y <= nextLoc[1] + nextButton.getHeight()))
				{
					setState(MenuState.MAIN, false);
				}
			}
		}
	}

	public static void listenForConnection()
	{
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				Main.setAndroidConnecting();
				MessageHandler.listenForConnection();
			}
		}).start();
	}
}
