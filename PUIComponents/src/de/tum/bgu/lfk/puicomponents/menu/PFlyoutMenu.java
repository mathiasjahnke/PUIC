package de.tum.bgu.lfk.puicomponents.menu;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;

import de.tum.bgu.lfk.puicomponents.button.PRadioButton;
import de.tum.bgu.lfk.puicomponents.constants.FlyoutMenuOptions;
import processing.core.PApplet;
import processing.core.PConstants;

/**
 * having a button with a icon at the top, left, bottom or right which expands on clicking. 
 * after expanding it give some possibilities to set value or so.</br>  
 * TODO:</br>
 * An {@code add(Object o)} function has to be implemented.</br>
 * flyout at left, bottom and top.</br> 
 * @author Mathias Jahnke, Technische Universit&auml;t M&uuml;nchen, <a href="http://www.lfk.bgu.tum.de">Chair of Cartography</a>
 * @version 0.0.1
 * @since 23.02.2015
 *
 */
public class PFlyoutMenu implements MouseListener{
	
	private PApplet p;
	
	private float x;
	private float y;
	
	private float width;
	private float height;
	private float handleWidth;
	private float handleHeight;
	
	private boolean checked; //menu opened or closed
	
	private FlyoutMenuOptions where;
	
	//Styling
	private Integer stroke;
	private Integer strokeHighlight;
	private Integer fill;
	private Integer fillHighlight;
	private float cornerRadius;
	
	//add other components
	private ArrayList<PRadioButton> components;
	private float componentsMaxWidth;
	private float componentsMaxHeight;
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param p
	 */
	public PFlyoutMenu(float x, float y, float width, float height, PApplet p){
		
		this.p = p;
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		this.where = FlyoutMenuOptions.LEFT;
		this.checked = false;
		
		this.stroke = p.color(150);
		this.fill = p.color(200);
		this.cornerRadius = 6;
		this.handleWidth = 20;
		this.handleHeight = 30;
		
		this.components = new ArrayList<PRadioButton>();
		this.componentsMaxHeight = 0;
		this.componentsMaxWidth = 0;
	}
	
	/**
	 * indicates whether the menu is opened or closed
	 * @param checked true=opened, false closed
	 */
	public void setChecked(boolean checked){
		this.checked = checked;
	}
	
	/**
	 * indicates whether the menu is opened or closed
	 * @return opened menu true otherwise false
	 */
	public boolean isChecked(){
		return this.checked;
	}
	
	/**
	 * Toggles the status of the menu between opened and closed
	 */
	public void toggleChecked(){
		if(checked == true){
			checked = false;
		}else{
			checked = true;
		}
	}
	
	/**
	 * sets the stroke and fill color of the menu.
	 * the {@code color(r,g,b)}-function should be used.
	 * @param stroke the color of the border.
	 * @param strokeHighlight the color of the border on focus.
	 * @param fill the fill color of the opened menu and the handle.
	 * @param fillHighlight the fill color of the opened Menu and the handle on focus.
	 */
	public void setStyling(Integer stroke, Integer strokeHighlight, Integer fill, Integer fillHighlight){
		this.stroke = stroke;
		this.strokeHighlight = strokeHighlight;
		this.fill = fill;
		this.fillHighlight = fillHighlight;
	}
	
	/**
	 * to set the cornerRadius of the handle
	 * @param cornerRadius
	 */
	public void setCornerRadius(float cornerRadius){
		this.cornerRadius = cornerRadius;
	}
	
	/**
	 * to set the width and height of the handle
	 * @param handleWidth width of the handle
	 * @param handleHeight height of the handle
	 */
	public void setHandleSize(float handleWidth, float handleHeight){
		this.handleWidth = handleWidth;
		this.handleHeight = handleHeight;
	}

	/**
	 * draws the menu.
	 */
	public void draw(){
		p.rectMode(PConstants.CENTER);
		if(where == FlyoutMenuOptions.LEFT){ // only y is important
			if(checked == false){
				drawHandle(this.p.width, this.y);
			}else{
				drawHandle(this.p.width - this.width, this.y);
				p.rectMode(PConstants.CENTER);
				p.stroke(this.stroke);
				p.fill(this.fill);
				p.strokeWeight(1);
				p.rect(this.p.width - this.width/2, this.y + this.height/2 - this.handleHeight/2, this.width, this.height);
				Iterator<PRadioButton> iter = components.iterator();
				while(iter.hasNext()){
					PRadioButton prb = (PRadioButton) iter.next();
					prb.draw();
				}
			}
		}
	}
	/**
	private void notifyObservers(){
		Iterator<Observer> i = observers.iterator();
		while(i.hasNext()){
			Observer o = (Observer) i.next();
			o.update(this);
		}
	}**/
	
	private void drawHandle(float x, float y){
		p.stroke(this.stroke);
		p.fill(this.fill);
		p.strokeWeight(1);
		p.rectMode(PConstants.CENTER);
		p.rect(x - handleWidth/2, y, handleWidth, handleHeight, cornerRadius, 0, 0, cornerRadius);
	}
	
	/**
	 * checks if the x and y coordinates are falling inside the handle area
	 * @param x the x coordinate 
	 * @param y the y coordinate
	 * @return true if inside otherwise false
	 */
	public boolean isInside(float x, float y){
		boolean returnValue = false;
		switch(this.where){
		case LEFT:
			if(this.checked){
				//x is inside
				if ((x >= this.p.width - handleWidth - this.width) && (x <= this.p.width - this.width)) {
					//y is inside 
					if ((y >= this.y - this.handleHeight/2) && (y <= this.y + this.handleHeight/2)) {
						returnValue = true;
					}
				}
			}else{
				//x is inside
				if ((x >= this.p.width - this.handleWidth) && (x <= this.p.width)) {
					//y is inside 
					if ((y >= this.y - this.handleHeight/2) && (y <= this.y + this.handleHeight/2)) {
						returnValue = true;
					}
				}
			}
			break;
		case RIGHT:
			// TODO to implement
			System.out.println("RIGHT not yet implemented");
			break;
			
		case TOP:
			//TODO to implement
			System.out.println("TOP not yet implemented");
			break;
			
		case BOTTOM:
			//TODO to implement
			System.out.println("BOTTOM not yet implemented");
			break;
		}// end switch
		
		return returnValue;
	}
	
	/**
	 * indicates if the mouse is inside the flyout menu box
	 * @param x the mouse position
	 * @param y the mouse position
	 */
	private boolean isInsideMenuBox(float x, float y){
		boolean returnValue = false;
		switch(this.where){
		case LEFT:
			if(this.checked){
				//x is inside
				if ((x >= this.p.width - this.width) && (x <= this.p.width)) {
					//y is inside 
					if ((y >= this.y - this.handleHeight/2) && (y <= this.y + this.height)) {
						returnValue = true;
					}
				}
			}
			break;
		case RIGHT:
			// TODO to implement
			System.out.println("RIGHT not yet implemented");
			break;
			
		case TOP:
			//TODO to implement
			System.out.println("TOP not yet implemented");
			break;
			
		case BOTTOM:
			//TODO to implement
			System.out.println("BOTTOM not yet implemented");
			break;
		}// end switch
		
		return returnValue;
		
	}
	
	/**
	 * adds a component to the flyout menu. 
	 * at the moment only working for {@code PRadioButtons}
	 * @param prb the {@code PRadioButtons} to add
	 */ 
	public void add(PRadioButton prb){
		if(this.componentsMaxWidth < prb.getComponentWidth()){
			this.componentsMaxWidth = prb.getComponentWidth();
			this.width = this.componentsMaxWidth * 1.5f;
		}
		if(this.componentsMaxHeight < prb.getComponentHeight()){
			this.componentsMaxHeight = prb.getComponentHeight();
		}
		
		this.components.add(prb);
		this.height = (components.size() * componentsMaxHeight * 1.3f) + (handleHeight/2);
		for (int i = 0; i < components.size(); i++){
			this.components.get(i).setLocation(this.p.width - this.width + (this.componentsMaxHeight*1.3f), this.y + (i * (this.componentsMaxHeight*1.3f)));
		}
	}
	
	/**
	 * <b>not implemented.</b>
	 * As the comments note, you're often better off listening for mousePressed 
	 * or mouseReleased rather than mouseClicked because for mouseClicked to work, 
	 * the press and release must be from the same point, and if the mouse shifts 
	 * even a slight amount, the click won't register.
	 * from (http://stackoverflow.com/questions/7340001/determine-clicked-jpanel-component-in-the-mouselistener-event-handling)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	
	/**
	private void notifyObservers(){
		Iterator<Observer> i = observers.iterator();
		while(i.hasNext()){
			Observer o = (Observer) i.next();
			o.update(this);
		}
	}**/

	/**
	 * not implemented
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if(isInside(e.getX(), e.getY())){
			toggleChecked();
		}
	}

	/**
	 * the mouse listener to open or close the menu and toggle the PRadioButtons
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		//System.out.println("Clicked at x= " + e.getX() + " y=" + e.getY());
		if(checked == true && isInsideMenuBox(e.getX(), e.getY())){
			Iterator<PRadioButton> iter = components.iterator();
			while(iter.hasNext()){
				PRadioButton rb = (PRadioButton) iter.next();
				if(rb.isInside(e.getX(), e.getY())){
					rb.setChecked(true);
				}else{
					if(rb.isChecked()){
						rb.setChecked(false);
					}
				}
			}
		}
	}
	
	/**
	 * not implemented.
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	/**
	 * not implemented.
	 */
	@Override
	public void mouseExited(MouseEvent e) {
	}
	

	
	

}
