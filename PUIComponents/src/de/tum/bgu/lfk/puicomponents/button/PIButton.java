package de.tum.bgu.lfk.puicomponents.button;

/**
 * the Interface to handle different buttons in the PButtonGroup class
 * @author Mathias Jahnke, Technische Universit&auml;t M&uuml;nchen, <a href="http://www.lfk.bgu.tum.de">Chair of Cartography</a>
 * @version 0.0.1
 * @since 02.03.2015
 *
 */
public interface PIButton {
	
	/**
	 * retrieves the buttons name
	 * @return
	 */
	public String getText();
	
	/**
	 * sets then status of the button
	 * @param checked true if checked otherwise false
	 */
	public void setChecked(boolean checked);
	
	/**
	 * retrieves the buttons status
	 * @return true or false
	 */
	public boolean isChecked();
	
	/**
	 * checks whether a point is inside the button or not
	 * @param x the x coordinate of the point to check
	 * @param y the y coordinate of the point to check
	 * @return true if the point x, y are falling inside
	 */
	public boolean isInside(float x, float y);

}
