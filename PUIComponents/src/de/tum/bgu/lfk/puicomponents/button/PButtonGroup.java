package src.de.tum.bgu.lfk.puicomponents.button;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

/**
 * The PButtonGroup is for logical structuring of the PRadioButtons or PButtons.
 * Only one member of the group can be marked/checked at a time. 
 * @author Mathias Jahnke, Technische Universit&auml;t M&uuml;nchen, <a href="http://www.lfk.bgu.tum.de">Chair of Cartography</a>
 * @version 0.0.2
 * @since 23.02.2015
 */
public class PButtonGroup implements MouseListener{

	private ArrayList<PIButtonComponent> components;
	
	private UUID nameOfMarkedUUID;
	
	
	/**
	 * initializes the PRadioButtonGroup
	 */
	public PButtonGroup(){
		this.components = new ArrayList<PIButtonComponent>();
		nameOfMarkedUUID = null;
	}
	
	/**
	 * adds components to the PRadioButtonGroup.
	 * @param component the PRadioButton to add
	 */
	public void add(PIButtonComponent component){
		if(component.isChecked()){
			this.nameOfMarkedUUID = component.getComponentId();
			//System.out.println("add: " + nameOfMarkedUUID);
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
	 * @param id the uuid of the component to remove
	 */
	public void remove(UUID id){
		Iterator<PIButtonComponent> iter = this.components.iterator();
		while(iter.hasNext()){
			PIButtonComponent rb = (PIButtonComponent) iter.next();
			if(rb.getComponentId().compareTo(id) == 0){
				this.components.remove(rb);
			}
		}
	}
	
	/**
	 * Wrapping the ArrayList.remove().
	 * removes the specified PRadioButton
	 * @param component the PRadiobutton to remove
	 */
	public void remove(PIButtonComponent component){
		this.components.remove(component);
	}
	
	/**
	 * not implemented
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	/**
	 * not implemented
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	
	/**
	 * Listens which PRadiobutton is marked by a mouse release
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		Iterator<PIButtonComponent> iter = components.iterator();
		while(iter.hasNext()){
			PIButtonComponent rb = (PIButtonComponent) iter.next();
			if(rb.contains(e.getX(), e.getY())){
				this.nameOfMarkedUUID = rb.getComponentId();
			}
		}
		updateComponents();
	}

	/**
	 * not implemented
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	/**
	 * not implemented
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
	/**
	 * updates the components only one per PRadioButtonGroup can be marked
	 */
	private void updateComponents(){
		Iterator<PIButtonComponent> iter = this.components.iterator();
		while(iter.hasNext()){
			PIButtonComponent rb = (PIButtonComponent) iter.next();
			if(rb.getComponentId().equals(nameOfMarkedUUID)){
				rb.setChecked(true);
			}else{
				rb.setChecked(false);
			}
		}
	}
}
