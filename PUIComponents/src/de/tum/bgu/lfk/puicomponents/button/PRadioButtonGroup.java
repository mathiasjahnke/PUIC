package de.tum.bgu.lfk.puicomponents.button;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * The PRadioButtonGroup is for logical structuring of the PRadioButtons.
 * only one memeber of the group can be marked at a time. 
 * @author Mathias Jahnke, Technische Universit&auml;t M&uuml;nchen, <a href="http://www.lfk.bgu.tum.de">Chair of Cartography</a>
 * @version 0.0.1
 * @since 23.02.2015
 *
 */
public class PRadioButtonGroup implements MouseListener{

	private ArrayList<PRadioButton> components;
	
	private int indexOfMarked;
	private String nameOfMarked;
	
	/**
	 * initializes the PRadioButtonGroup
	 */
	public PRadioButtonGroup(){
		components = new ArrayList<PRadioButton>();
		indexOfMarked = 0;
		nameOfMarked = "";
	}
	
	/**
	 * adds components to the PRadioButtonGroup.
	 * @param radioButton the PRadioButton to add
	 */
	public void add(PRadioButton radioButton){
		components.add(radioButton);
	}
	
	/**
	 * remove the PRadioButton with the specified index.
	 * @param index the index of the component to remove
	 */
	public void remove(int index){
		components.remove(index);
	}
	
	/**
	 * removes the PRadiobutton with the specified name.
	 * @param name the name of the component to remove
	 */
	public void remove(String name){
		
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
