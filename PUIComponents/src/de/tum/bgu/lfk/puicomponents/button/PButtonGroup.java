package src.de.tum.bgu.lfk.puicomponents.button;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The PRadioButtonGroup is for logical structuring of the PRadioButtons.
 * only one memeber of the group can be marked at a time. 
 * @author Mathias Jahnke, Technische Universit&auml;t M&uuml;nchen, <a href="http://www.lfk.bgu.tum.de">Chair of Cartography</a>
 * @version 0.0.1
 * @since 23.02.2015
 */
public class PButtonGroup implements MouseListener{

	private ArrayList<PIButton> components;
	
	private String nameOfMarked;
	
	/**
	 * initializes the PRadioButtonGroup
	 */
	public PButtonGroup(){
		this.components = new ArrayList<PIButton>();
		//indexOfMarked = 0;
		nameOfMarked = "";
	}
	
	/**
	 * adds components to the PRadioButtonGroup.
	 * @param component the PRadioButton to add
	 */
	public void add(PIButton component){
		if(component.isChecked()){
			this.nameOfMarked = component.getText();
		}
		this.components.add(component);
	}
	
	/**
	 * Wrapping the ArrayList.remove().
	 * remove the PRadioButton with the specified index.
	 * @param index the index of the component to remove
	 */
	public void remove(int index){
		this.components.remove(index);
	}
	
	/**
	 * Wrapping the ArrayList.remove()
	 * removes the PRadiobutton with the specified name.
	 * @param name the name of the component to remove
	 */
	public void remove(String name){
		Iterator<PIButton> iter = this.components.iterator();
		while(iter.hasNext()){
			PIButton rb = (PIButton) iter.next();
			if(rb.getText() == name){
				this.components.remove(rb);
			}
		}
	}
	
	/**
	 * Wrapping the ArrayList.remove().
	 * removes the specified PRadioButton
	 * @param component the PRadiobutton to remove
	 */
	public void remove(PIButton component){
		this.components.remove(component);
	}
	
	/**
	 * not implemented
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * not implemented
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Listens which PRadiobutton is marked by a mouse release
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		Iterator<PIButton> iter = components.iterator();
		while(iter.hasNext()){
			PIButton rb = (PIButton) iter.next();
			if(rb.isInside(e.getX(), e.getY())){
				nameOfMarked = rb.getText();
			}
		}
		updateComponents();
		
	}

	/**
	 * not implemented
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * not implemented
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * updates the components only one per PRadioButtonGroup can be marked
	 */
	private void updateComponents(){
		Iterator<PIButton> iter = this.components.iterator();
		while(iter.hasNext()){
			PIButton rb = (PIButton) iter.next();
			if(rb.getText() == this.nameOfMarked){
				rb.setChecked(true);
			}else{
				rb.setChecked(false);
			}
		}
	}
}
