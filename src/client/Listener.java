package client;

import java.io.IOException;
import java.io.ObjectInputStream;

public class Listener implements Runnable
{
	
	private RoomList roomList;
	private Client client;
	private ObjectInputStream input;
	
	public Listener(RoomList list, Client client, ObjectInputStream in) 
	{
		this.roomList = list;
		this.client = client;
		this.input = in;
		
		
	}

	@Override
	public void run() 
	{
		String message = "";
		
		while(!message.equals("passed"))
		{
			
			try 
			{
				message = (String) input.readObject();
				System.out.println("message from server: " + message);
			} 
			catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			if(message.equals("failed"))
			{
				client.errorMessage();
				message = "";
			}
			
		}
		client.changeToRoomScreen();
		
		String[] parts;
		int messageType;
		
		while(true)
		{
			try 
			{
				message = (String) input.readObject();
			} 
			catch (ClassNotFoundException | IOException e) 
			{
				e.printStackTrace();
			}
			
			parts = message.split("\t");
			
			messageType = Integer.parseInt(parts[0]);
			
			if(messageType == 0)
			{
				client.updateChatRoom(parts[1]);
			}
			else if(messageType == 1)
			{
				
			}
		}
	}
	
}
