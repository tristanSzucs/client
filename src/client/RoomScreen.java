package client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;




/*
 * This class contains the following private subclasses
 * RoomList
 * RoomLine
 */

public class RoomScreen extends JPanel
{
	private RoomList roomList;
	private JButton newButton;
	private Client parent;
	
	//RoomScreen constructor
	public RoomScreen(Client client, RoomList list) 
	{
		roomList = list;
		parent = client;
		
		setLayout(new BorderLayout());
		
		//add list of rooms to the screen
		add(roomList, BorderLayout.CENTER);
		
		//create and add button to the screen
		newButton = new JButton("New Chat Room");
		add(newButton, BorderLayout.SOUTH);
		
		
		//create and define ActionListener object
		ActionListener myActionListener = new ActionListener()
		
		{
			@Override
			public void actionPerformed(ActionEvent buttonClick) 
			{
				//if newButton is clicked
				if(buttonClick.getSource() == newButton)
				{
					//display option pane for user input
					String newRoomName = (String) JOptionPane.showInputDialog(parent, "type room name", null, JOptionPane.QUESTION_MESSAGE, null, null, "");
					
					//if the new room name is not null, and is not an empty String
					if(newRoomName != null && !newRoomName.matches(""))
					{
						parent.changeToChatRoom(newRoomName); //call the client function to change to that chat room
					}
					
				}
				else
				{
					System.err.println("Unknown action performed");
				}
			}
			
		};
		
		//add the ActionListener to desired component
		newButton.addActionListener(myActionListener);
	}
	
	
	
	
} //end of class
