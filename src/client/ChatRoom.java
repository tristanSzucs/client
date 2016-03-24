package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatRoom extends JPanel
{
	private String roomName;
	private JTextField typeTextBox;
	private JTextArea chatRoomText;
	private JButton submitButton = new JButton("Submit"), leaveRoomButton = new JButton("Leave Room");
	private JPanel chatPanel = new JPanel(), titlePanel = new JPanel(), typePanel = new JPanel();
	
	public ChatRoom(String roomName, Client theClient)
	{
		super();
		this.roomName = roomName;
		
		add(titlePanel, BorderLayout.NORTH);
		
		add(chatPanel, BorderLayout.CENTER);
		
		add(typePanel, BorderLayout.SOUTH);
		
		JLabel roomLabel = new JLabel(roomName);
		titlePanel.add(roomLabel, BorderLayout.NORTH);
		roomLabel.setVisible(true);
		
		titlePanel.add(leaveRoomButton);
		
		chatRoomText = new JTextArea(10, 10);
		chatRoomText.setEditable(false);
		
		chatPanel.add(new JScrollPane(chatRoomText), BorderLayout.CENTER);
		
		
		typeTextBox = new JTextField(30);
		typePanel.add(typeTextBox, BorderLayout.SOUTH);
		typePanel.add(submitButton, BorderLayout.SOUTH);
		
		ActionListener myActionListener = new ActionListener()
				
				{
					@Override
					public void actionPerformed(ActionEvent buttonClick) 
					{
						if(buttonClick.getSource() == submitButton)
						{
							if(!typeTextBox.getText().isEmpty())
							{
								System.out.println("You typed: " + typeTextBox.getText());
								String previousText = chatRoomText.getText();
								chatRoomText.setText(previousText + "\n" + typeTextBox.getText());
								typeTextBox.setText("");
							}
							else
							{
								System.err.println("No text to send");
							}
						}
						else if(buttonClick.getSource() == leaveRoomButton)
						{
							theClient.changeToRoomScreen();
						}
						else
						{
							System.err.println("Unknown action performed");
						}
					}
					
				};
				
		submitButton.addActionListener(myActionListener);
		leaveRoomButton.addActionListener(myActionListener);
		
		
		KeyListener myKeyListener = (new KeyListener()
		
		{
			@Override
			public void keyPressed(KeyEvent myEvent) 
			{
				if(myEvent.getKeyCode() == KeyEvent.VK_ENTER && !typeTextBox.getText().isEmpty())
				{
					System.out.println("You typed: " + typeTextBox.getText());
					String previousText = chatRoomText.getText();
					chatRoomText.setText(previousText + "\n" + typeTextBox.getText());
					typeTextBox.setText("");
				}
			}

			@Override
			public void keyReleased(KeyEvent myEvent) 
			{
				
			}

			@Override
			public void keyTyped(KeyEvent myEvent) 
			{
				
			}
					
		});		
		
		typeTextBox.addKeyListener(myKeyListener);
		
	}
	
	
}
