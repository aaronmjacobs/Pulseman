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
		public static final int MAIN = 0,
				STORY_1 = 1,
				STORY_2 = 2;
	}
	
	private int state = MenuState.MAIN;
	
	private Image menuButton;
	private Image menuBackground;
	private Image connectButton, connectingButton, connectedButton;
	private volatile int countdown = 0;
	private String ip = null;

	private final float[] buttonLoc =
	{ 50, 500 }, connectLoc =
	{ 75, 200 };

	@Override
	public void render(GameContainer gc, Graphics g)
	{
		if(state == MenuState.MAIN)
		{
			g.drawImage(menuBackground, 0, 0);
			g.drawImage(menuButton, buttonLoc[0], buttonLoc[1]);

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
				g.drawString("Your IP address is: " + ip, 100, 100);
			}
		}
		else if(state == MenuState.STORY_1)
		{
			//TODO
		}
		else if(state == MenuState.STORY_2)
		{
			//TODO
		}
	}

	@Override
	public void init(GameContainer gc) throws SlickException
	{
		menuButton = new Image("res/subtitle.png");
		connectButton = new Image("res/connect.png");
		connectingButton = new Image("res/connecting.png");
		connectedButton = new Image("res/connected.png");
		menuBackground = new Image("res/mainscreen.png");
		
		ip = Main.getIPAddress();
	}

	@Override
	public void update(GameContainer gc, int dt)
	{
		Input input = gc.getInput();
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
				state = MenuState.STORY_1;

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
