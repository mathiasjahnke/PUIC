package src.de.tum.bgu.lfk.puicomponents.button;

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
	 * sets the margin for the component. (according to css)
	 * @param top margin
	 * @param right margin
	 * @param bottom margin
	 * @param left margin
	 */
	public void setMargin(float top, float right, float bottom, float left);
	
	/**
	 * sets the padding for the component. (According to CSS)
	 * @param top padding
	 * @param right padding
	 * @param bottom padding
	 * @param left padding
	 */
	public void setPadding(float top, float right, float bottom, float left);
	
	/**
	 * to draw the component onto the canvas
	 */
	public void draw();

}
