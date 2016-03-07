package src.de.tum.bgu.lfk.puicomponents.button;

import java.util.UUID;

/**
 * The interface for a ui component for processing.
 * @author Mathias Jahnke, Technische Universit&auml;t M&uuml;nchen, <a href="http://www.lfk.bgu.tum.de">Chair of Cartography</a>
 * @version 0.0.1
 * @since 02.03.2015
 *
 */
public interface PIComponent {
	
	
	/**
	 * returns the current height of the component.
	 * @return the components height
	 */
	public float getHeight();
	
	
	/**
	 * returns the current width of the component.
	 * @return the components width
	 */
	public float getWidth();
	
	
	/**
	 * Resizes the component so that it has width width and height height.  
	 * @param width the new width of the component
 	 * @param height the new height of the component
	 */
	public void setSize(float width, float height);
	
	
	/**
	 * Returns the current x coordinate of the components origin.
	 * @return the current x coordinate of the components origin
	 */
	public float getX();
	
	
	/**
	 * Returns the current y coordinate of the components origin.
	 * @return the current y coordinate of the components origin
	 */
	public float getY();
	
	
	/**
	 * sets the location of the component
	 * @param x the x value
	 * @param y the y value
	 */
	public void setLocation(float x, float y);
	

	/**
	 * checks if the components contains x and y for the sake of e.g. mouse processing. 
	 * @param x the x coordinate of the point
	 * @param y the y coordinate of the point
	 * @return true if the component contains x and y
	 */
	public boolean contains(float x, float y);
	
	
/*	/**
	 * sets the margin for the component. (according to css)
	 * @param top margin
	 * @param right margin
	 * @param bottom margin
	 * @param left margin
	 */
//	public void setMargin(float top, float right, float bottom, float left);
	
/*	/**
	 * sets the padding for the component. (According to CSS)
	 * @param top padding
	 * @param right padding
	 * @param bottom padding
	 * @param left padding
	 */
//	public void setPadding(float top, float right, float bottom, float left);
	
	
	/**
	 * returns the component's unique id.
	 * @return the component's unique id. 
	 */
	public UUID getComponentId();
	
	
	/**
	 * to draw the component onto the canvas
	 */
	public void draw();

}
