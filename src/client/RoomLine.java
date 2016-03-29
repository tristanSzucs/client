package client;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * This class is used to create and display a line in the rooms box
 * 
 */
public class RoomLine extends JPanel 
{
	private String name;	//the name of the room to be displayed
	private int pop;		//the population of the room
	private JLabel nameLabel, popLabel;
	private JButton button;
	private ActionListener listener;
	public RoomLine next = null;
	
	/*
	 * The constructor
	 * Parameters:
	 * iName = the name of the room
	 * number = the amount of people in the room
	 */
	public RoomLine (String iName, int number, Client clientFrame) 
	{
		this.setLayout(new GridLayout(1,3));	//set a grid layout
		
		//set up the private variables
		name = iName;
		pop = number;
		
		//make the JLabel
		nameLabel = new JLabel(iName);
		popLabel = new JLabel("");
		popLabel.setText("" + pop);	
		add(nameLabel);
		add(popLabel);
		listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clientFrame.changeToChatRoom(name);
			}
		};
		//set up the button
		button = new JButton("JOIN");
		button.addActionListener(listener);
		add(button);
	} //end of constructor
	
	
	
	/*
	 * addPop
	 * This method increments the population in the room
	 * This method takes no parameters
	 * This method does not return anything
	 */
	public void addPop() 
	{
		pop++;
		EventQueue.invokeLater(new Runnable() {
		    public void run() {
		      popLabel.setText("" + pop);
		    }
		  });
	} //end of function
	
	/*
	 * decPop
	 * This method decrements the population in the room
	 * This method takes no parameters
	 * This method does not return anything
	 */
	public void decPop() 
	{
		pop--;
		EventQueue.invokeLater(new Runnable() {
		    public void run() {
		      popLabel.setText("" + pop);
		    }
		  });
	} //end of function
	
	
	//returns the name of the room this class is for.
	public String getName() {
		return name;
	} //end of method
	
	//returns the next line
	public RoomLine getNext() {
		return next;
	} //end of method
	
	//a method to set the next room
	public void setNext(RoomLine n) {
		next = n;
	}
	
	//a method to get the pop
	public int getPop() {
		return pop;
	}
} //end of class RoomLine

