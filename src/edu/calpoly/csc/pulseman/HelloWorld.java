package edu.calpoly.csc.pulseman;

import java.io.File;
import java.net.InetAddress;
import java.util.LinkedList;

import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.*;

import edu.calpoly.csc.pulseman.MessageHandler.MessageReceiver;

public class HelloWorld extends BasicGame
{
	private volatile static LinkedList<String> messageQueue = new LinkedList<String>();

	public HelloWorld()
	{
		super("Hello World");
	}

	@Override
	public void init(GameContainer gc) throws SlickException
	{

	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException
	{

	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		g.drawString("Hello World", 100, 100);
		synchronized(messageQueue)
		{
			for(int i = 0; i < messageQueue.size(); ++i)
			{
				g.drawString(messageQueue.get(i), 100, 150 + (i * 50));
			}
		}
	}

	public static void main(String[] args) throws SlickException
	{
		listenForConnection();

		MessageHandler.addmessageReceiver(new MessageReceiver()
		{
			@Override
			public void onMessageReceived(String message)
			{
				synchronized(messageQueue)
				{
					messageQueue.offer(message);
				}
			}

			@Override
			public void onConnectionEstablished(InetAddress client)
			{
				System.out.println("Connection established: " + client.getHostAddress());
			}

			@Override
			public void onConnectionLost(InetAddress client)
			{
				System.out.println("Connection lost: " + client.getHostAddress());
				listenForConnection();
			}
		});

		System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "native"), LWJGLUtil.getPlatformName()).getAbsolutePath());
		System.setProperty("net.java.games.input.librarypath", System.getProperty("org.lwjgl.librarypath"));
		AppGameContainer app = new AppGameContainer(new HelloWorld());

		app.setDisplayMode(800, 600, false);
		app.start();
	}

	public static void listenForConnection()
	{
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				MessageHandler.listenForConnection();
			}
		}).start();
	}
}
