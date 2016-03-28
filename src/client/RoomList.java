package client;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;


public class RoomList extends JPanel 
{
	private Client client;
	private RoomLine first;
	private Boolean active = true;
	private Timer reDraw = new Timer(2000, new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
	         reDo();
	      }
	});
	
	
	//the constructor
	public RoomList(Client clientFrame) {
		client = clientFrame;
		this.setLayout(new GridLayout(10,1));
		this.setVisible(true);
		reDraw.start();
		reDo();
		
	}
	
	/*
	 * Adds a room based on Init pop and name
	 */
	public synchronized void addRoom(String name, int pop) {
		//creates the room to add
		RoomLine toAdd = new RoomLine(name, pop, client);
		//adds it to the GUI
		
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
	public synchronized void RemoveRoom(String name) {
		//if there is none then nothing happens
		if(first == null) {
			return;
		}
		//if it is the first element
		if(first.getName() == name) {
			first = first.next; //removes from list
		}
		
		//start at top
		RoomLine cur = first;
		//while there is more and the next is not the one we need then keep moving
		while(cur.next != null && cur.next.getName() != name) cur = cur.next;
		if (cur.next == null) return; //if the case was that there is no more then end
		//otherwise the next is to be removed
		
		
		cur.next = cur.next.next;	//removes from list
	} //end of method
	
	/*
	 * THis method takes in a string name and adds a pop to it
	 */
	public synchronized void AddPopRoom(String name) {
		//if it is empty then return
		if(first == null) {
			addRoom(name,1);
			return;
		}
	
		//go through the rest of the list
		RoomLine cur = first;  //set the curent to the first
		
		//while we are not at null and this is not the one we are looking for
		while(cur.next != null && !cur.getName().equals( name ))	cur = cur.next;  
		//if we are on the right one
		if (cur.getName().equals(name)) cur.addPop();
		else cur.next = new RoomLine(name,1, client);
		
	} //end of method
	
	
	/*
	 * This method removes this line if a room by the name is found
	 * If not found it is not removed.
	 */
	public synchronized void RemovePopRoom(String name) {
		//if it is empty then return
		if(first == null) {
			return;
		}
		if (first.getName().equals(name) ) {
			first.decPop();
			if (first.getPop() == 0) {
				first = first.next;
			}
			return;
		}
		//go through the rest of the list
		RoomLine cur = first;  //set the curent to the first
		
		
		
		
		//while we are not at null and this is not the one we are looking for
		while(cur.next != null && !cur.next.getName().equals( name ))	cur = cur.next; 
		
		if( cur.next != null && cur.next.getName().equals( name ) ) {
			cur.next.decPop();
			if (cur.next.getPop() == 0) {
				cur.next = cur.next.next;
			}
		}			
		
		
	} //end of method
	
	/*
	 * This function removes all lines and puts them back on.
	 */
	private synchronized void reDo() {
		removeAll();
		RoomLine cur = first;
		while(cur != null) {
			add(cur);
			cur = cur.next;
		}
		validate();
	} //end of reDO
	
	public synchronized void setActive(Boolean n) {
		if (n && !active) {
			reDraw.start();
		} else {
			reDraw.stop();
		}
	}
	
} //end of RoomList
