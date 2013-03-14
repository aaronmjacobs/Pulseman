package edu.calpoly.csc.pulseman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ProtocolException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

public class MessageHandler
{
	public static final int PORT = 42000, SOCKET_TIMEOUT = 10000;

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
			serverSocket.setSoTimeout(SOCKET_TIMEOUT);
			System.out.println("Waiting");
			clientSocket = serverSocket.accept();
			System.out.println("Accepted");
			// clientSocket.setSoTimeout(SOCKET_TIMEOUT);

			synchronized(messageReceivers)
			{
				for(int i = 0; i < messageReceivers.size(); ++i)
				{
					messageReceivers.get(i).onConnectionEstablished(clientSocket.getInetAddress());
				}
			}

			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			receiver = new Receiver();
			new Thread(receiver, "Message Receiver").start();

			return;
		}
		catch(SocketTimeoutException e)
		{
			//
		}
		catch(SocketException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		disconnect();

		synchronized(messageReceivers)
		{
			for(int i = 0; i < messageReceivers.size(); ++i)
			{
				messageReceivers.get(i).onConnectionLost(null);
			}
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

		if(serverSocket != null)
		{
			try
			{
				serverSocket.close();
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
		serverSocket = null;
	}

	public static synchronized boolean isConnected()
	{
		return clientSocket != null && out != null && in != null && clientSocket.isConnected();
	}

	public static void send(String message)
	{
		if(isConnected())
		{
			out.write(message);
		}
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
			String inputLine;

			try
			{
				while(alive && (inputLine = in.readLine()) != null)
				{
					synchronized(messageReceivers)
					{
						for(int i = 0; i < messageReceivers.size(); ++i)
						{
							messageReceivers.get(i).onMessageReceived(inputLine);
						}
					}
				}

				// If we are still alive, alert that the connection was lost
				if(alive)
				{
					InetAddress clientAddress = clientSocket.getInetAddress();

					disconnect();

					synchronized(messageReceivers)
					{
						for(int i = 0; i < messageReceivers.size(); ++i)
						{
							messageReceivers.get(i).onConnectionLost(clientAddress);
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

	/**
	 * Adds a MessageReceiver to the list of receivers
	 * 
	 * @param receiver The MessageReceiver to add
	 */
	public static void addmessageReceiver(MessageReceiver receiver)
	{
		synchronized(messageReceivers)
		{
			messageReceivers.add(receiver);
		}
	}

	/**
	 * Removes a MessageReceiver from the list of receivers
	 * 
	 * @param receiver The MessageReceiver to remove
	 */
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

		/**
		 * Called when the connection to the client is established
		 */
		public void onConnectionEstablished(InetAddress client);

		/**
		 * Called when the connection to the client is lost
		 */
		public void onConnectionLost(InetAddress client);
	}
}
