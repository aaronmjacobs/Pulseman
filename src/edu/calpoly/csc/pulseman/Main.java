package edu.calpoly.csc.pulseman;

import java.io.File;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import edu.calpoly.csc.pulseman.MessageHandler.MessageReceiver;

public class Main extends BasicGame {
	private volatile static LinkedList<String> messageQueue = new LinkedList<String>();
	public enum GameState {MENU, GAME, GAMEOVER};
	private static GameState state = GameState.MENU;
	Map<GameState, GameInterface> interfaceMap = new HashMap<GameState, GameInterface>();
	
	
	public Main() {
		super("Pulse of Nature");
	}
	
	public static void setState(GameState state) {
		Main.state = state;
	}
	
	
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		interfaceMap.get(state).render(gc, g);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		StartMenu menu = new StartMenu();
		menu.init(gc);
		interfaceMap.put(GameState.MENU, menu);
		
		GameScreen game = new GameScreen();
		game.init(gc);
		interfaceMap.put(GameState.GAME, game);
	}
	
	public static void main(String[] arg) throws SlickException {
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
		AppGameContainer app = new AppGameContainer(new Main());

		app.setDisplayMode(800, 600, false);
		app.start();
	}

	@Override
	public void update(GameContainer gc, int dt) throws SlickException {
		interfaceMap.get(state).update(gc, dt);
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

	public static LinkedList<String> getMessageQueue() {
		return messageQueue;
	}

}
