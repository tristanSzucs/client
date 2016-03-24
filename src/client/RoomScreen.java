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
	private RoomList roomList = new RoomList();
	private JButton newButton;
	
	public RoomScreen() {
		roomList = new RoomList();
		setLayout(new BorderLayout());
		add(roomList, BorderLayout.CENTER);
		newButton = new JButton("New Chat Room");
		add(newButton, BorderLayout.SOUTH);
	}
	
	public static void main(String args[]) {
		JFrame x = new JFrame("TEST");
		x.add(new RoomScreen());
		x.setVisible(true);
		x.setSize(1000,1000);
		x.repaint();
		x.validate();
	}
	
	
	
	
	
	
	
	
} //end of class
