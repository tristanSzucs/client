package client;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Client 
{

	public static void main(String[] args) 
	{
		//initialize frame title, size, visibility, and close operation
		JFrame clientFrame = new JFrame("The Best Chat Room Ever");
		clientFrame.setSize(800, 800);
		clientFrame.setVisible(true);
		clientFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//create an object of LoginScreen
		LoginScreen myLoginScreen = new LoginScreen();
		
		//add loginPanel to the frame
		
		clientFrame.add(myLoginScreen.loginPanel);
		myLoginScreen.loginPanel.setVisible(true);
		//set frame visibility to true
		clientFrame.setVisible(true);
	}

}
