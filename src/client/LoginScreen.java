package client;

import javax.swing.JButton;
import javax.swing.JTextField;

public class LoginScreen 
{

	private JTextField usernameField, passwordField;
	private JButton createNewUserButton, loginButton;
	
	public LoginScreen()
	{
		createNewUserButton = new JButton("Create New");
		loginButton = new JButton("Login");
	}

}
