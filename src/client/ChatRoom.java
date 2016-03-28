package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ChatRoom extends JPanel
{
	private String roomName;
	private JTextField typeTextBox;
	private JTextArea chatRoomText;
	private JButton submitButton = new JButton("Submit"), leaveRoomButton = new JButton("Leave Room");
	
	public ChatRoom(String roomName, Client theClient, ObjectOutputStream output)
	{
		super();
		this.roomName = roomName;
		
		setLayout(null);
		setVisible(true);
		
		chatRoomText = new JTextArea(10, 10);
		chatRoomText.setEditable(false);
		typeTextBox = new JTextField(30);
		
		submitButton.setBounds(600, 700, 100, 20);
		add(submitButton);
		
		chatRoomText.setBounds(0, 50, 800, 500);
		add(chatRoomText);
		
		typeTextBox.setBounds(0, 700, 600, 20);
		add(typeTextBox);
		
		leaveRoomButton.setBounds(0, 0, 150, 20);
		add(leaveRoomButton);
		
		JLabel roomLabel = new JLabel(roomName);
		roomLabel.setBounds(350, 0, 100, 50);
		add(roomLabel);
		roomLabel.setVisible(true);
		
		
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
								try {
									output.writeObject("0\t" + typeTextBox.getText());
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
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
					
					try {
						output.writeObject("0\t" + typeTextBox.getText());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
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
	
	//update chatRoomText with newly entered text
	public void update(String newText)
	{
		SwingUtilities.invokeLater(new Runnable() 
		{
			public void run()
			{
				chatRoomText.append(newText + "\n");
			}
		});
	}
	
}
