package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ChatRoom extends JPanel
{
	private JTextField typeTextBox;
	private JTextArea chatRoomText;
	private JButton submitButton = new JButton("Submit"), leaveRoomButton = new JButton("Leave Room");
	
	//3variable constructor for a ChatRoom object
	public ChatRoom(String roomName, Client theClient, ObjectOutputStream output)
	{
		super();
		
		setLayout(null);
		setVisible(true);
		
		//create and add GUI components to the screen
		chatRoomText = new JTextArea(10, 10);
		chatRoomText.setEditable(false);
		chatRoomText.setBounds(0, 50, 800, 500);
		add(chatRoomText);
		
		submitButton.setBounds(600, 700, 100, 20);
		add(submitButton);
		
		typeTextBox = new JTextField(30);
		typeTextBox.setBounds(0, 700, 600, 20);
		add(typeTextBox);
		
		leaveRoomButton.setBounds(0, 0, 150, 20);
		add(leaveRoomButton);
		
		JLabel roomLabel = new JLabel(roomName);
		roomLabel.setBounds(350, 0, 100, 50);
		add(roomLabel);
		roomLabel.setVisible(true);
		
		//create and define an ActionListener for buttons
		ActionListener myActionListener = new ActionListener()
				
				{
					@Override
					public void actionPerformed(ActionEvent buttonClick) 
					{
						//if submit button is clicked
						if(buttonClick.getSource() == submitButton)
						{
							//if there is text typed
							if(!typeTextBox.getText().isEmpty())
							{
								try 
								{
									//send text as output to server
									output.writeObject("0\t" + typeTextBox.getText());
								} 
								catch (IOException e) 
								{
									e.printStackTrace();
								}
								//clear typed text to allow for new text to be typed
								typeTextBox.setText("");
							}
							else
							{
								System.err.println("No text to send");
							}
						}
						//else if leave room button is clicked
						else if(buttonClick.getSource() == leaveRoomButton)
						{
							try 
							{
								output.writeObject("8"); //inform server we have left the chat room
							} catch (IOException e) 
							{
								e.printStackTrace();
							}
							//call changeToRoomScreen and go back to view the list of rooms
							theClient.changeToRoomScreen();
						}
						else
						{
							System.err.println("Unknown action performed");
						}
					}
					
				};
			
		//add our ActionListener to desired buttons		
		submitButton.addActionListener(myActionListener);
		leaveRoomButton.addActionListener(myActionListener);
		
		//create and define a KeyListener for specific keyboard operations
		KeyListener myKeyListener = (new KeyListener()
		
		{
			@Override
			public void keyPressed(KeyEvent myEvent) 
			{
				//if user presses <ENTER> on keyboard and there is text typed
				if(myEvent.getKeyCode() == KeyEvent.VK_ENTER && !typeTextBox.getText().isEmpty())
				{
					//send text as output to server
					try 
					{
						output.writeObject("0\t" + typeTextBox.getText());
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					}
					
					//clear typed text to allow for new text to be typed
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
		
		// add our KeyListener to our typeTextBox
		typeTextBox.addKeyListener(myKeyListener);
		
		
		
	}
	
	//update chatRoomText with newly entered text
	public void update(String newText)
	{
		SwingUtilities.invokeLater(new Runnable() 
		{
			public void run()
			{
				chatRoomText.append(newText + "\n"); //update the chat room by appending newly sent text
			}
		});
	}
	
}
