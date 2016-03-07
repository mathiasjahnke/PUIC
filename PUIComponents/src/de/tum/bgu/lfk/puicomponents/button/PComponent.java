package src.de.tum.bgu.lfk.puicomponents.button;

import java.util.Observable;
import java.util.UUID;

import processing.core.PApplet;
import processing.core.PConstants;

/**
 * 
 * A components base-class implementing the respective interface (PIComponent). The components origin is the components upper 
 * left corner. </br> No styling associated with the component.   
 * @author Mathias Jahnke, Technische Universit&auml;t M&uuml;nchen, <a href="http://www.lfk.bgu.tum.de">Chair of Cartography</a>
 * @version 0.0.1
 * @since 07.03.2016
 *
 */
public class PComponent implements PIComponent {

	private float width;
	private float height;
	private float x;
	private float y;
	
	private UUID componentId;
	
	private PApplet p;
	
	/**
	 * default constructor. </br>
	 * only for unique id's in inheritance
	 */
	public PComponent(){
		this.componentId = UUID.randomUUID();
	}
	
	/**
	 * parameterized constructor
	 * @param p the PApplet to draw on
	 */
	public PComponent(PApplet p) {
		this.p = p;
		this.componentId = UUID.randomUUID();
	}
	
	/**
	 * returns the component's unique identifier. 
	 * @return the component's unique identifier
	 */
	@Override
	public UUID getComponentId(){
		return this.componentId;
	}
	
	/**
	 * returns the components current height.
	 */
	@Override
	public float getHeight() {
		return this.height;
	}

	
	/**
	 * returns the components current width
	 */
	@Override
	public float getWidth() {
		return this.width;
	}

	
	/**
	 * sets the components width and height.
	 * @param width the components new width
	 * @param height the components new height
	 */
	@Override
	public void setSize(float width, float height) {
		this.height = height;
		this.width = width;

	}

	
	/**
	 * Returns the x coordinate of the component's current origin
	 */
	@Override
	public float getX() {
		return this.x;
	}

	
	/**
	 * Returns the y coordinate of the component's current origin.
	 */
	@Override
	public float getY() {
		return this.y;
	}

	@Override
	/**
	 * Sets the component's origin to the new coordinates x and y.
	 * @param x the new x coordinate of the component's origin.
	 * @param y the new y coordinate of the component's origin.
	 */
	public void setLocation(float x, float y) {
		this.x = x;
		this.y = y;
	}

	
	/**
	 * true if the coordinates (x, y) are within the components area. 
	 * The method assumes rectMode() as its default value (CORNER).
	 * @param x the x coordinate of the point to test
	 * @param y the y coordinate of the point to test  
	 */
	@Override
	public boolean contains(float x, float y) {
		boolean rv = false;
		if((x > this.x) && (x < this.x + this.width)){
			if((y > this.y) && (y < this.y + this.height)){
				rv = true;
			}
		}
		return rv;
	}

	@Override
	/**
	 * Raw or rudimentary implementation of the components draw()-function. The styling is set to a rectangle 
	 * with black border and white area. To change the styling the method needs to be overridden by a derived class. </br></br>
	 * The method assumes rectMode() to be set to its default value (CORNER). Therefore the method needs to 
	 * be overridden/implemented by a derived class if rectMode() should be set to CORNERS, CENTER or RADIUS. 
	 * 
	 */
	public void draw() {	
		p.rectMode(PConstants.CORNER);
		p.fill(0);
		p.stroke(255);
		p.rect(x, y, width, height);

	}

}
