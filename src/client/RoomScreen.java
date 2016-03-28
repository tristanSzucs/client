package client;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;




/*
 * This class contains the following private subclasses
 * RoomList
 * RoomLine
 */

public class RoomScreen extends JPanel
{
	private RoomList roomList;
	private JButton newButton, createButton, cancelButton;
	private Client parent;
	private JTextField roomNameSelect;
	private JDialog customDialog;
	
	public RoomScreen(Client client, RoomList list) 
	{
		roomList = list;
		parent = client;
		setLayout(new BorderLayout());
		add(roomList, BorderLayout.CENTER);
		newButton = new JButton("New Chat Room");
		add(newButton, BorderLayout.SOUTH);
		
		
		
		ActionListener myActionListener = new ActionListener()
		
		{
			@Override
			public void actionPerformed(ActionEvent buttonClick) 
			{
				if(buttonClick.getSource() == newButton)
				{
					System.out.println("creating new room");
					String newRoomName = (String) JOptionPane.showInputDialog(parent, "type room name", null, JOptionPane.QUESTION_MESSAGE, null, null, "");
					
					if(newRoomName != null && !newRoomName.matches(""))
					{
						parent.changeToChatRoom(newRoomName);
					}
					
				}
				else
				{
					System.err.println("Unknown action performed");
				}
			}
			
		};
		
		newButton.addActionListener(myActionListener);
	}
	
	
	
	
} //end of class
