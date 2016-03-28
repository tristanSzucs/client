package client;

import java.awt.BorderLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Client extends JFrame
{

	private ObjectOutputStream out;
	private ObjectInputStream in;
	private RoomList listOfRooms = new RoomList(this);
	private RoomScreen myRoomScreen = new RoomScreen(this, listOfRooms);
	private LoginScreen myLoginScreen;
	private ChatRoom myChatRoom;
	private Socket mySocket;
	
	public Client() 
	{
		super("The Best Chat Room Ever");
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	
		
		try
		{
			Socket mySocket = new Socket("192.168.2.122", 4001);
			if(mySocket == null)
			{
				System.err.println("I am null");
			}
			
			out = new ObjectOutputStream(mySocket.getOutputStream());
			System.out.println("outputStream");
			out.flush();
			
			in = new ObjectInputStream(mySocket.getInputStream());
			System.out.println("inputStream");
			
			
			Listener theListener = new Listener(listOfRooms, this, in);
			
			ExecutorService executor = Executors.newCachedThreadPool();
			executor.execute(theListener);
			
			myLoginScreen = new LoginScreen(this, out);
			System.out.println("loginpanel created");
			add(myLoginScreen.loginPanel);
			System.out.println("Added loginpanel");
			repaint();
		}
		catch( UnknownHostException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		repaint();
		setVisible(true);
		
	}
	
	public static void main(String[] args) 
	{
		Client theClient = new Client();
	}
	
	//change current screen to room select screen
	public void changeToRoomScreen()
	{
		
		SwingUtilities.invokeLater(new Runnable() 
		{
			
			public void run()
			{
				System.out.println("Changed to room screen");
				myLoginScreen.loginPanel.setVisible(false);
				remove(myLoginScreen.loginPanel);
				
				if(myChatRoom != null)
				{
					remove(myChatRoom);
					repaint();
				}
				
				
				add(myRoomScreen);
			}
			
		});
		
	}
	
	//change current screen to selected chat room screen
	public void changeToChatRoom(String roomName)
	{
		System.out.println("Changed to " + roomName);
		myChatRoom = new ChatRoom(roomName, this, out);
		remove(myRoomScreen);
		add(myChatRoom);
		validate();
	}
	
	public void errorMessage()
	{
		myLoginScreen.displayError();
	}
	
	public void updateChatRoom(String newText)
	{
		myChatRoom.update(newText);
	}

}
