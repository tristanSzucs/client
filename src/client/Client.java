package client;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Client extends JFrame
{

	private RoomList listOfRooms;
	private RoomScreen myRoomScreen = new RoomScreen(this, listOfRooms);
	private LoginScreen myLoginScreen = new LoginScreen(this);
	
	public Client()
	{
		super("The Best Chat Room Ever");
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	
		
		add(myLoginScreen.loginPanel);
	}
	
	public static void main(String[] args) 
	{
		Client theClient = new Client();
		
		
	}
	
	//change current screen to room select screen
	public void changeToRoomScreen()
	{
		System.out.println("Changed to room screen");
		myLoginScreen.loginPanel.setVisible(false);
		remove(myLoginScreen.loginPanel);
		add(myRoomScreen);
	}
	
	//change current screen to selected chat room screen
	public void changeToChatRoom(String roomName)
	{
		
	}

}
