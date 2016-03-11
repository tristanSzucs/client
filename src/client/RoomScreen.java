package client;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RoomScreen extends JPanel 
{
	private RoomList roomList = new RoomList();
	
	public RoomScreen() {
		
	}
	
	private class RoomList extends JPanel 
	{
		//the constructor
		public RoomList() {
			
			
			
		}
	}
	
	
	/*
	 * This class is used to create and display a line in the rooms box
	 * 
	 */
	private class RoomLine extends JPanel 
	{
		private String name;	//the name of the room to be displayed
		private int pop;		//the population of the room
		private JLabel label;
		private JButton button;
		
		
		/*
		 * The constructor
		 * Parameters:
		 * iName = the name of the room
		 * number = the amount of people in the room
		 */
		public void RoomLine (String iName, int number) {
			name = iName;
			pop = number;
			
			label = new JLabel("");
			update();				//sets the data into the label
			add(label);
			
			button = new JButton("join");
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
			update();
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
			update();
		} //end of function
		
		/*
		 * update
		 * This method updates the display of this object
		 */
		public void update() {
			label.setText(String.format("%15s %5d", name, pop));
		}
		
		//returns the name of the room this class is for.
		public String getName() {
			return name;
		}
	}
}
