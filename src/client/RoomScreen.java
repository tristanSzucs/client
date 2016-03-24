package client;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	
	public RoomScreen(Client client, RoomList list) {
		roomList = list;
		parent = client;
		setLayout(new BorderLayout());
		add(roomList, BorderLayout.CENTER);
		newButton = new JButton("New Chat Room");
		add(newButton, BorderLayout.SOUTH);
	}
	
	
	
	
} //end of class
