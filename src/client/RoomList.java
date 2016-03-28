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
	         reDraw.restart();
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
	 * THis method takes in a string name and adds a pop to it
	 */
	public synchronized void AddPopRoom(String name) {
		//if it is empty then add the room then return
		if(first == null) {
			first = new RoomLine(name, 1, client);
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
		repaint();
	} //end of reDO
	
	
	//determines if the timer continues to run and is called when exiting and leaving the screen
	public synchronized void setActive(Boolean n) {
		if (n && !active) {
			reDraw.start();
		} else if(n == false) {
			reDraw.stop();
		}
		active = n;
	}
	
} //end of RoomList
