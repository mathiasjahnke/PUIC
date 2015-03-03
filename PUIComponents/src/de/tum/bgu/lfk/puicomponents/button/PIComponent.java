package de.tum.bgu.lfk.puicomponents.button;

/**
 * the components interface
 * @author Mathias Jahnke, Technische Universit&auml;t M&uuml;nchen, <a href="http://www.lfk.bgu.tum.de">Chair of Cartography</a>
 * @version 0.0.1
 * @since 02.03.2015
 *
 */
public interface PIComponent {
	
	/**
	 * returns the height of the component
	 * @return the components height
	 */
	public float getComponentHeight();
	
	/**
	 * returns the width of the component
	 * @return the components width
	 */
	public float getComponentWidth();
	
	/**
	 * sets the location of the component
	 * @param x the x value
	 * @param y the y value
	 */
	public void setLocation(float x, float y);
	
	/**
	 * to draw the component onto the canvas
	 */
	public void draw();

}
