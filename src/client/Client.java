package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	
	public Client() 
	{
		//create and initialize our JFrame
		super("The Best Chat Room Ever");
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try
		{
			Socket mySocket = new Socket("127.0.0.1", 4001); //create a socket for I/O
			
			out = new ObjectOutputStream(mySocket.getOutputStream()); //get output stream
			out.flush();
			
			in = new ObjectInputStream(mySocket.getInputStream()); //get input stream
			
			//create object of type Listener for client/server communication
			Listener theListener = new Listener(listOfRooms, this, in); 
			
			ExecutorService executor = Executors.newCachedThreadPool();
			executor.execute(theListener); //execute Listener as Runnable
			
			myLoginScreen = new LoginScreen(this, out);
			add(myLoginScreen.loginPanel);
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
				//if on the login screen
				if(myLoginScreen.loginPanel != null)
				{
					myLoginScreen.loginPanel.setVisible(false);
					remove(myLoginScreen.loginPanel); //remove loginPanel
				}
				
				//if in a chat room
				if(myChatRoom != null)
				{
					remove(myChatRoom); //remove myChatRoom
					repaint();
				}
				
				
				add(myRoomScreen); //open myRoomScreen
				listOfRooms.setActive(true); //display the list of rooms
			}
			
		});
		
	}
	
	//change current screen to selected chat room screen
	public void changeToChatRoom(String roomName)
	{
		myChatRoom = new ChatRoom(roomName, this, out); //change to selected chat room
		remove(myRoomScreen);  //remove the list of rooms screen
		add(myChatRoom); //add the new chat room to the frame
		validate();
		
		try 
		{
			out.writeObject("7\t" + roomName); //output to server that we have entered a room
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	//will display login error on login screen 
	public void errorMessage()
	{
		myLoginScreen.displayError();
	}
	
	//updates the chat room with new text
	public void updateChatRoom(String newText)
	{
		if(myChatRoom != null)
		{
			myChatRoom.update(newText);  //will update and append the new text to the chat room
		}
	}

}
