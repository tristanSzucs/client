package client;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;




/*
 * This class contains the following private subclasses
 * RoomList
 * RoomLine
 */

public class RoomScreen extends JPanel
{
	private RoomList roomList = new RoomList();
	private RoomLine first = null;
	private JButton newButton;
	
	public RoomScreen() {
		roomList = new RoomList();
		newButton = new JButton("New Chat Room");
		add(newButton, BorderLayout.SOUTH);
		
		
	}
	
	private class RoomList extends JPanel 
	{
		
		//the constructor
		public RoomList() {
			this.setLayout(new GridLayout(10,1));
		}
		
		/*
		 * Adds a room based on Init pop and name
		 */
		public void addRoom(String name, int pop) {
			//creates the room to add
			RoomLine toAdd = new RoomLine(name, pop);
			//adds it to the GUI
			add(toAdd  );
			//if the list is empty make it the first
			if(first == null) {
				first = toAdd;
				return;
			}
			
			//otherwise go through and add it to the end
			RoomLine cur = first;
			//go to the end
			while(cur.next != null) cur = cur.next;
			//add it
			cur.next = toAdd;
		}
		
		/*
		 * This method removes the room based on the name
		 */
		public void RemoveRoom(String name) {
			//if there is none then nothing happens
			if(first == null) {
				return;
			}
			//if it is the first element
			if(first.getName() == name) {
				remove(first);	//removes from GUI
				first = first.next; //removes from list
				reDo();	//redraws the GUI
			}
			
			//start at top
			RoomLine cur = first;
			//while there is more and the next is not the one we need then keep moving
			while(cur.next != null && cur.next.getName() != name) cur = cur.next;
			if (cur.next == null) return; //if the case was that there is no more then end
			//otherwise the next is to be removed
			remove( cur.next );	//removes the element from GUI
			reDo();	//redraw
			cur.next = cur.next.next;	//removes from list
		} //end of method
		
		/*
		 * THis method takes in a string name and adds a pop to it
		 */
		public void AddPopRoom(String name) {
			//if it is empty then return
			if(first == null) {
				return;
			}
		
			//go through the rest of the list
			RoomLine cur = first;  //set the curent to the first
			
			//while we are not at null and this is not the one we are looking for
			while(cur != null && cur.getName() != name)	cur = cur.next;  
			//if case was there is no more 
			if (cur == null) return;
			cur.addPop();
		} //end of method
		
		
		/*
		 * This method removes this line if a room by the name is found
		 * If not found it is not removed.
		 */
		public void RemovePopRoom(String name) {
			//if it is empty then return
			if(first == null) {
				return;
			}
		
			//go through the rest of the list
			RoomLine cur = first;  //set the curent to the first
			
			//while we are not at null and this is not the one we are looking for
			while(cur != null && cur.getName() != name)	cur = cur.next;  
			//if case was there is no more 
			if (cur == null) return;
			cur.decPop();
		} //end of method
		
		/*
		 * This function removes all lines and puts them back on.
		 */
		public void reDo() {
			this.removeAll();
			RoomLine cur = first;
			while(cur != null) {
				add(cur);
				cur = cur.next;
			}
		} //end of reDO
		
	} //end of RoomList
	
	
	/*
	 * This class is used to create and display a line in the rooms box
	 * 
	 */
	private class RoomLine extends JPanel 
	{
		private String name;	//the name of the room to be displayed
		private int pop;		//the population of the room
		private JLabel nameLabel, popLabel;
		private JButton button;
		private ActionListener listener;
		private RoomLine next = null;
		
		/*
		 * The constructor
		 * Parameters:
		 * iName = the name of the room
		 * number = the amount of people in the room
		 */
		public RoomLine (String iName, int number) {
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
			popLabel.setText("" + pop);
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
			popLabel.setText("" + pop);
		} //end of function
		
		
		//returns the name of the room this class is for.
		public String getName() {
			return name;
		} //end of method
	} //end of class RoomLine
	
	
	
	
} //end of class
