package client;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class LoginScreen 
{

	
	private JTextField usernameField, passwordField;
	private JButton createNewUserButton, loginButton;
	public JPanel loginPanel = new JPanel();
	private boolean canCreate, canLogin;
	
	//constructor for LoginScreen
	public LoginScreen()
	{
		//instantiate Buttons
		createNewUserButton = new JButton("Create New");
		loginButton = new JButton("Login");
		
		//instantiate TextFields
		usernameField = new JTextField(15);
		passwordField = new JTextField(15);
		
		
		
		//add following components to loginPanel
		loginPanel.add(usernameField);
		loginPanel.add(passwordField);
		loginPanel.add(createNewUserButton);
		loginPanel.add(loginButton);
		
		
		
		//define myListener
		ActionListener myListener = new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent currentAction) 
					{
						if(currentAction.getSource() == createNewUserButton)
						{
							
							//if (server says so)
							canCreate = true;
							
							if(canCreate)
							{
								String user = usernameField.getText();
								String pass = passwordField.getText();
								
								User newUser = new User(user, pass);
								System.out.println("Created...");
								System.out.printf("User: %s \nPass: %s\n", newUser.getUserName(), newUser.getPassword());
							}
							else if(!canCreate)
							{
								System.err.println("Create Error");
								
								usernameField.setText("");
								passwordField.setText("");
							}
							
							
						}
						else if(currentAction.getSource() == loginButton)
						{
							
							String user = usernameField.getText();
							String pass = passwordField.getText();
							//send signal to server with login info
							
							//if (server says so)
							canLogin = true;
							
							if(canLogin)
							{
								System.out.println("Logged in");
							}
							else if(!canLogin)
							{
								System.err.println("Incorrect Password");
								
								passwordField.setText("");
							}
							
							
						}
						else
						{
							System.err.println("Unknown Action");
						}
					}
			
				};
		
		//associate myListener to appropriate buttons
		createNewUserButton.addActionListener(myListener);
		loginButton.addActionListener(myListener);
		
	}
	
	

	

}
