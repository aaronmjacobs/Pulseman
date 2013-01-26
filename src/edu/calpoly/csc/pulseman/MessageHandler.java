package edu.calpoly.csc.pulseman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ProtocolException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MessageHandler
{
	public static final int PORT = 42000;

	private static Socket clientSocket = null;
	private static ServerSocket serverSocket = null;
	private static PrintWriter out = null;
	private static BufferedReader in = null;

	private static Receiver receiver = null;

	private static ArrayList<MessageReceiver> messageReceivers = new ArrayList<MessageReceiver>();

	public static synchronized void listenForConnection()
	{
		if(serverSocket != null && clientSocket != null && clientSocket.isConnected())
		{
			throw new IllegalStateException("Already connected to client");
		}

		try
		{
			serverSocket = new ServerSocket(PORT);
			clientSocket = serverSocket.accept();
			System.out.println("Connected");

			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			receiver = new Receiver();
			new Thread(receiver, "Message Receiver").start();
		}
		catch(IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static synchronized void disconnect()
	{
		if(receiver != null)
		{
			receiver.kill();
		}

		if(out != null)
		{
			out.close();
		}

		if(in != null)
		{
			try
			{
				in.close();
			}
			catch(IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if(clientSocket != null)
		{
			try
			{
				clientSocket.close();
			}
			catch(IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		out = null;
		in = null;
		clientSocket = null;
	}

	public static synchronized boolean isConnected()
	{
		return clientSocket != null && out != null && in != null && clientSocket.isConnected();
	}

	private static class Receiver implements Runnable
	{
		private boolean alive;

		public Receiver()
		{
			alive = true;
		}

		public void kill()
		{
			alive = false;
		}

		@Override
		public void run()
		{
			int counter = 0;
			String inputLine;

			try
			{
				while(alive && (inputLine = in.readLine()) != null)
				{
					// System.out.println(inputLine);
					out.println("received: " + counter++);

					synchronized(messageReceivers)
					{
						for(int i = 0; i < messageReceivers.size(); ++i)
						{
							messageReceivers.get(i).onMessageReceived(inputLine);
						}
					}
				}
			}
			catch(ProtocolException e)
			{
				e.printStackTrace(); // TODO
			}
			catch(IOException e)
			{
				e.printStackTrace(); // TODO
			}
		}
	}

	public static void addmessageReceiver(MessageReceiver receiver)
	{
		synchronized(messageReceivers)
		{
			messageReceivers.add(receiver);
		}
	}

	public static void removemessageReceiver(MessageReceiver receiver)
	{
		synchronized(messageReceivers)
		{
			messageReceivers.remove(receiver);
		}
	}

	/**
	 * Interface used to notify listeners when messages are received from an Android client
	 * 
	 * @author Aaron Jacobs
	 */
	public interface MessageReceiver
	{
		/**
		 * Called when a message is received from an Android client
		 * 
		 * @param message The message received by the client
		 */
		public void onMessageReceived(String message);
	}
}
