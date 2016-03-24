package client;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



public class LoginScreen 
{

	
	private JTextField usernameField;
	private JButton createNewUserButton, loginButton;
	private JLabel usernameLabel, passwordLabel, errorLabel;
	private JPasswordField passwordField;
	public JPanel loginPanel = new JPanel(), fieldPanel = new JPanel(), buttonPanel = new JPanel();
	private boolean canCreate, canLogin;
	
	//constructor for LoginScreen
	public LoginScreen(Client client, ObjectOutputStream out)
	{
		
		
		//Buttons
		createNewUserButton = new JButton("Create New");
		loginButton = new JButton("Login");
		
		//TextField / PasswordField
		usernameField = new JTextField(15);
		passwordField = new JPasswordField(15);
		
		//JLabels
		usernameLabel = new JLabel("Username : ");
		passwordLabel = new JLabel("Password : ");
		errorLabel = new JLabel("Incorrect Login");
		
		
		
		fieldPanel.add(usernameLabel);
		fieldPanel.add(usernameField);
		fieldPanel.add(passwordLabel);
		fieldPanel.add(passwordField);
		
		buttonPanel.add(createNewUserButton);
		buttonPanel.add(loginButton);
		buttonPanel.add(errorLabel);
		errorLabel.setVisible(false);
		
		
		loginPanel.setLayout(new BorderLayout());
		
		//add following components to loginPanel
		loginPanel.add(fieldPanel, BorderLayout.NORTH);
		loginPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		
		
		
		
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
								char[] pass = passwordField.getPassword();
								
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
							
							errorLabel.setVisible(false);
							
							String user = usernameField.getText();
							char[] pass = passwordField.getPassword();
							String passString = "";
							
							for(int i = 0; i < pass.length; i++)
							{
								passString += pass[i];
							}
							
							String loginMessage = user + "\t" + passString;
							
							System.out.println(loginMessage);
							
							try {
								out.writeObject(loginMessage);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
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
	public void displayError()
	{
		errorLabel.setVisible(true);
	}
	

	

}
