package client;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChatRoom extends JPanel
{
	private String roomName;
	private JTextField typeTextBox, chatRoomText;
	private JButton submitButton = new JButton("Submit");
	private JPanel chatPanel = new JPanel();
	
	public ChatRoom(String roomName)
	{
		super();
		this.roomName = roomName;
		
		add(chatPanel);
		
		JLabel roomLabel = new JLabel(roomName);
		chatPanel.add(roomLabel, BorderLayout.NORTH);
		roomLabel.setVisible(true);
		
		typeTextBox = new JTextField(30);
		chatPanel.add(typeTextBox, BorderLayout.LINE_END);
		
	}
}
