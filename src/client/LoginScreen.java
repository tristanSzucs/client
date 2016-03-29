package client;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



public class LoginScreen 
{

	
	private JTextField usernameField;
	private JButton loginButton;
	private JLabel usernameLabel, passwordLabel, errorLabel;
	private JPasswordField passwordField;
	public JPanel loginPanel = new JPanel(), fieldPanel = new JPanel(), buttonPanel = new JPanel();
	
	//2variable constructor for LoginScreen object
	public LoginScreen(Client client, ObjectOutputStream out)
	{
		
		
		//create and add GUI elements
		loginButton = new JButton("Login");
		
		usernameField = new JTextField(15);
		passwordField = new JPasswordField(15);
		
		usernameLabel = new JLabel("Username : ");
		passwordLabel = new JLabel("Password : ");
		errorLabel = new JLabel("Incorrect Login - Please try again");
		
		fieldPanel.add(usernameLabel);
		fieldPanel.add(usernameField);
		fieldPanel.add(passwordLabel);
		fieldPanel.add(passwordField);
		
		buttonPanel.add(loginButton);
		buttonPanel.add(errorLabel, BorderLayout.CENTER);
		errorLabel.setForeground(Color.RED);
		errorLabel.setVisible(false);
		
		loginPanel.setLayout(new BorderLayout());
		
		loginPanel.add(fieldPanel, BorderLayout.NORTH);
		loginPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		//create and define ActionListener object
		ActionListener myListener = new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent currentAction) 
					{
						//if loginButton is pressed
						if(currentAction.getSource() == loginButton)
						{
							
							errorLabel.setVisible(false);
							
							String user = usernameField.getText(); //retrieve entered user name
							char[] pass = passwordField.getPassword(); //retrieve entered password
							String passString = "";
							
							//convert password to a String
							for(int i = 0; i < pass.length; i++)
							{
								passString += pass[i];
							}
							
							String loginMessage = user + "\t" + passString;
							
							
							try 
							{
								out.writeObject(loginMessage); //send loginMessage (user and pass) to server
							} 
							catch (IOException e) 
							{
								e.printStackTrace();
							}
						}
						else
						{
							System.err.println("Unknown Action");
						}
					}
			
				};
		
		//associate myListener to appropriate button
		loginButton.addActionListener(myListener);
		
		//create and define KeyListener object
		KeyListener myKeyListener = (new KeyListener()
		
		{
			@Override
			public void keyPressed(KeyEvent myEvent) 
			{
				//if user presses <ENTER> on keyboard
				if(myEvent.getKeyCode() == KeyEvent.VK_ENTER)
				{
					{
						
						errorLabel.setVisible(false);
						
						String user = usernameField.getText();
						char[] pass = passwordField.getPassword();
						String passString = "";
						
						//convert password to a String
						for(int i = 0; i < pass.length; i++)
						{
							passString += pass[i];
						}
						
						String loginMessage = user + "\t" + passString;
						
						try 
						{
							out.writeObject(loginMessage);  //send loginMessage (user and pass) to server
						} 
						catch (IOException e) 
						{
							e.printStackTrace();
						}
					}
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
		
		//add KeyListener to desired components
		usernameField.addKeyListener(myKeyListener);
		passwordField.addKeyListener(myKeyListener);
		
	}// end of constructor

	//display login error message
	public void displayError()
	{
		errorLabel.setVisible(true);
		
	}
	

	

}//end of LoginScreen
