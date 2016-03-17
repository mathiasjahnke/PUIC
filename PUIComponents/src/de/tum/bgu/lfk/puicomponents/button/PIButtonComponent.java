package src.de.tum.bgu.lfk.puicomponents.button;

/**
 * the interface to extend the PComponent with some status information
 * @author Mathias Jahnke, Technische Universit&auml;t M&uuml;nchen, <a href="http://www.lfk.bgu.tum.de">Chair of Cartography</a>
 * @version 0.0.1
 * @since 17.03.2016
 *
 */
public interface PIButtonComponent {
	
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
	 * toggles component's status from checked (true) to not checked (false) and the other way round.
	 */
	public void toggleChecked();

}
