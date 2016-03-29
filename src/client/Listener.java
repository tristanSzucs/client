package client;

import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.SwingUtilities;

public class Listener implements Runnable
{
	
	private RoomList roomList;
	private Client client;
	private ObjectInputStream input;
	
	// 3variable constructor for Listener object
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
		try
		{
			//while the server message is not "passed"
		while(!message.equals("passed"))
		{
			
				//read server message
				message = (String) input.readObject();
				System.out.println(message);
			
			//if the server message is "failed"
			if(message.equals("failed"))
			{
				
				SwingUtilities.invokeLater(new Runnable() 
				{
					
					public void run()
					{
						client.errorMessage();	//display login error message
					}
					
				});
				message = "";
			}
			
		}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
			return;
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			return;
		}
		client.changeToRoomScreen();	//call changeToRoomScreen on the client (login passed)
		
		String[] parts; 
		int messageType;
		
		try{
		
		while(true)
		{
			message = (String) input.readObject();
			parts = message.split("\t");
			messageType = Integer.parseInt(parts[0]);
			
			//handle text message sent from the server
			if(messageType == 0)
			{
				client.updateChatRoom(parts[1]);
			}
			//handle population increase message from the server
			else if(messageType == 1)
			{
				roomList.AddPopRoom(parts[1]);
			}
			//handle population decrease message from the server
			else if(messageType == 2)
			{
				roomList.RemovePopRoom(parts[1]);
			}
		}
		}
		catch (ClassNotFoundException | IOException e) 
		{
			e.printStackTrace();
		}
	}//end of run() method
	
}//end of Listener
