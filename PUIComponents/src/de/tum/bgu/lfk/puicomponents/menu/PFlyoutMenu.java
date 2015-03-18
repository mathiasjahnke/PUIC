package de.tum.bgu.lfk.puicomponents.menu;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;

import de.tum.bgu.lfk.puicomponents.button.PIComponent;
import de.tum.bgu.lfk.puicomponents.constants.FlyoutMenuOptions;
import processing.core.PApplet;
import processing.core.PConstants;

/**
 * having a button with a icon at the top, left, bottom or right which expands on clicking. 
 * after expanding it give some possibilities to set value or so.</br>  
 * TODO:</br>
 * flyout at bottom and top.</br> 
 * flyout self adopting </br>
 * something like a flyout menu group with an equal gravity and the opend one is drawn on top of the rest. </br>
 * aligning of components horizontal or vertical and left or right.</br>
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
	
	private FlyoutMenuOptions gravity;
	
	//Styling
	private Integer stroke;
	private Integer fill;
	private float cornerRadius;
	
	//add other components
	private ArrayList<PIComponent> components;
	private float componentsMaxWidth;
	private float componentsMaxHeight;
	
	/**
	 * constructs an flyout menu which is self adopting to the size of added components. 
	 * the default gravity is set to RIGHT.
	 * @param x the x position used if gravity is set to TOP or BOTTOM
	 * @param y the y position used if gravity is set to LEFT or RIGHT
	 * @param width the initial width of the flyout menu
	 * @param height the initial height of the flyout menu
	 * @param p the PApplet to draw on
	 */
	public PFlyoutMenu(float x, float y, float width, float height, PApplet p){
		
		this.p = p;
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		this.gravity = FlyoutMenuOptions.RIGHT;
		this.checked = false;
		
		this.stroke = p.color(150);
		this.fill = p.color(200);
		this.cornerRadius = 6;
		this.handleWidth = 20;
		this.handleHeight = 30;
		
		this.components = new ArrayList<PIComponent>();
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
	 * @param fill the fill color of the opened menu and the handle.
	 */
	public void setStyling(Integer stroke, Integer fill){
		this.stroke = stroke;
		this.fill = fill;
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
	 * sets where to put the flyout menu. default value is left
	 * @param gravity left, right, top or bottom
	 */
	public void setGravity(FlyoutMenuOptions gravity){
		this.gravity = gravity;
		updateComponentLocations();
	}

	/**
	 * draws the menu.
	 */
	public void draw(){
		p.rectMode(PConstants.CENTER);
		//if(where == FlyoutMenuOptions.LEFT){ 
		switch(this.gravity){
		case RIGHT:  								// only y is important
			if(checked == false){
				drawHandle(this.p.width, this.y);
			}else{
				drawHandle(this.p.width - this.width, this.y);
				p.rectMode(PConstants.CENTER);
				p.stroke(this.stroke);
				p.fill(this.fill);
				p.strokeWeight(1);
				p.rect(this.p.width - this.width/2, this.y + this.height/2 - this.handleHeight/2, this.width, this.height);
				
				Iterator<PIComponent> iter = components.iterator();
				while(iter.hasNext()){
					PIComponent prb = (PIComponent) iter.next();
					prb.draw();
				}
			}
			break;
			
		case BOTTOM:
			if(checked == false){
				drawHandle(this.x, this.p.height - handleHeight/2);
			}else {
				drawHandle(this.x, this.p.height - this.height - handleHeight/2);
				p.rectMode(PConstants.CENTER);
				p.stroke(this.stroke);
				p.fill(this.fill);
				p.strokeWeight(1);
				p.rect(this.x + this.width/2 -handleWidth/2, this.p.height - this.height/2, this.width, this.height);
				
				Iterator<PIComponent> iter = components.iterator();
				while(iter.hasNext()){
					PIComponent prb = (PIComponent) iter.next();
					prb.draw();
				}
			}
			break;
			
		case LEFT:
			if(checked == false){
				drawHandle(0, this.y);
			}else{
				drawHandle(this.width, this.y);
				p.rectMode(PConstants.CENTER);
				p.stroke(this.stroke);
				p.fill(this.fill);
				p.strokeWeight(1);
				p.rect(this.width/2, this.y + this.height/2 - this.handleHeight/2, this.width, this.height);
				
				Iterator<PIComponent> iter = components.iterator();
				while(iter.hasNext()){
					PIComponent prb = (PIComponent) iter.next();
					prb.draw();
				}
			}
			break;
			
		case TOP:
			if(checked == false){
				drawHandle(this.x, 0);
			}else{
				drawHandle(this.x, this.height);
				p.rectMode(PConstants.CENTER);
				p.stroke(this.stroke);
				p.fill(this.fill);
				p.strokeWeight(1);
				p.rect(this.x + this.width/2 - handleWidth/2, this.height/2 , this.width, this.height);
				
				Iterator<PIComponent> iter = components.iterator();
				while(iter.hasNext()){
					PIComponent prb = (PIComponent) iter.next();
					prb.draw();
				}
			}
			
			break;
		default:
			System.out.println("draw not yet implemented");
			break;
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
		switch(this.gravity){
		case RIGHT:
			p.rect(x - handleWidth/2, y, handleWidth, handleHeight, cornerRadius, 0, 0, cornerRadius);
			break;
		case BOTTOM:
			p.rect(x, y, handleWidth, handleHeight, cornerRadius, cornerRadius, 0, 0);
			break;
		case LEFT:
			p.rect(x + handleWidth/2.0f, y, handleWidth, handleHeight, 0, cornerRadius, cornerRadius, 0);
			break;
		case TOP:
			p.rect(x, y + handleHeight/2.0f, handleWidth, handleHeight, 0, 0, cornerRadius, cornerRadius);
			break;
		default:
			break;
		}
		
	}
	
	/**
	 * checks if the x and y coordinates are falling inside the handle area
	 * @param x the x coordinate 
	 * @param y the y coordinate
	 * @return true if inside otherwise false
	 */
	public boolean isInside(float x, float y){
		boolean returnValue = false;
		switch(this.gravity){
		case RIGHT:
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
		case LEFT:
			if(this.checked){
				if((x >= this.width) && (x <= this.width + handleWidth)){
					if ((y >= this.y - this.handleHeight/2) && (y <= this.y + this.handleHeight/2)) {
						returnValue = true;
					}
				}
			}else{
				if(x <= this.handleWidth){
					if ((y >= this.y - this.handleHeight/2) && (y <= this.y + this.handleHeight/2)) {
						returnValue = true;
					}
				}
			}
			break;
			
		case TOP:
			if(this.checked){ //opened
				
			}else{ //closed
				if((x >= this.x - handleWidth/2) && (x <= this.x + handleWidth/2)){
					if((y >= this.y) && (y <= this.y + handleHeight)){
						returnValue = true;
					}
				}
			}
			break;
			
		case BOTTOM:
			if (this.checked){
				
			}else{
				if((x >= this.x - handleWidth/2) && (x <= this.x + handleWidth/2)){
					if((y >= this.p.height - handleHeight)){
						returnValue = true;
					}
				}
			}
			break;
		}// end switch
		
		return returnValue;
	}
	
	/**
	 * indicates if the mouse is inside the opened flyout menu box
	 * @param x the mouse position
	 * @param y the mouse position
	 */
	private boolean isInsideMenuBox(float x, float y){
		boolean returnValue = false;
		switch(this.gravity){
		case RIGHT:
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
		case LEFT:
			if(this.checked){
				//x is inside
				if(x <= this.width){
					//y is inside
					if ((y >= this.y - this.handleHeight/2) && (y <= this.y + this.height)) {
						returnValue = true;
					}
				}
			}
			break;
			
		case TOP:
			if(this.checked){
				if((x >= this.x - handleWidth/2) && (x <= this.x - handleWidth/2 + this.width)){
					if((y >= this.y) && (y <= this.y + this.height)){
						returnValue = true;
					}
				}
			}
			break;
			
		case BOTTOM:
			if(this.checked){
				if((x >= this.x - handleWidth/2) && (x <= this.x - handleWidth/2 + this.width)){
					if(y >= this.p.height - this.height){
						returnValue = true;
					}
				}
			}
			break;
		}// end switch
		
		return returnValue;
		
	}
	
	/**
	 * update the component locations and is called in add(Component) and if the gravity is changed 
	 */
	@SuppressWarnings("incomplete-switch")
	private void updateComponentLocations(){
		switch(this.gravity){
		case RIGHT:
			for (int i = 0; i < components.size(); i++){
				this.components.get(i).setLocation(this.p.width - this.width + (this.componentsMaxHeight*1.3f), this.y + (i * (this.componentsMaxHeight*1.3f)));
			}
			break;
		case LEFT:
			for (int i = 0; i < components.size(); i++){
				this.components.get(i).setLocation(this.componentsMaxHeight*1.3f, this.y + (i * (this.componentsMaxHeight*1.3f)));
			}
			break;
		case TOP:
			for (int i = 0; i < components.size(); i++) {
				this.components.get(i).setLocation(this.x - handleWidth/2 + (this.componentsMaxHeight*1.3f), this.y + handleHeight/2 + (i * this.componentsMaxHeight * 1.3f));
			}
			break;
		case BOTTOM:
			for (int i = 0; i < components.size(); i++) {
				this.components.get(i).setLocation(this.x - handleWidth/2 + (this.componentsMaxHeight*1.3f), this.y - handleHeight + (i * this.componentsMaxHeight * 1.3f));
			}
		}
		
	}
	
	/**
	 * adds a component to the flyout menu. 
	 * at the moment working for {@code PIComponent}.
	 * for mouseEventHandling see the PButtonGroup
	 * @param pic the {@code PRadioButtons} to add
	 */ 
	public void add(PIComponent pic){
		if(this.componentsMaxWidth < pic.getComponentWidth()){
			this.componentsMaxWidth = pic.getComponentWidth();
			this.width = this.componentsMaxWidth * 1.5f;
		}
		if(this.componentsMaxHeight < pic.getComponentHeight()){
			this.componentsMaxHeight = pic.getComponentHeight();
		}
		
		this.components.add(pic);
		this.height = (components.size() * componentsMaxHeight * 1.3f) + (handleHeight/2);
		
		updateComponentLocations();
		
		
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
	 * to open or close the flyout menu on a mouse pressed event
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if(isInside(e.getX(), e.getY())){
			toggleChecked();
		}else if(!isInsideMenuBox(e.getX(), e.getY())){
			setChecked(false);
		}
	}

	/**
	 * <b>not implemented.</b>
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	
	/**
	 * <b>not implemented.</b>
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	/**
	 * <b>not implemented.</b>
	 */
	@Override
	public void mouseExited(MouseEvent e) {
	}
	

	
	

}
