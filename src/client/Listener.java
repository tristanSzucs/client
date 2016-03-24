package client;

import java.io.IOException;
import java.io.ObjectInputStream;

public class Listener implements Runnable
{
	
	private RoomList roomList;
	private Client client;
	
	public Listener(RoomList list, Client client, ObjectInputStream in) 
	{
		this.roomList = list;
		this.client = client;
		
		String message = "";
				
		while(message != "passed")
		{
			
			try {
				message = (String) in.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(message == "failed")
			{
				client.errorMessage();
				message = "";
			}
			
		}
		
		client.changeToRoomScreen();
	}

	@Override
	public void run() 
	{
		
	}
}
