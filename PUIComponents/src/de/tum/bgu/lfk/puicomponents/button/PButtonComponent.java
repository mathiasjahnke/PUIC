package src.de.tum.bgu.lfk.puicomponents.button;

/**
 * Extending PComponent for using as a base for a button. <br>
 * 
 * @author Mathias Jahnke, Technische Universit&auml;t M&uuml;nchen, <a href="http://www.lfk.bgu.tum.de">Chair of Cartography</a>
 * @version 0.0.1
 * @since 15.03.2016
 *
 */
public class PButtonComponent extends PComponent implements PIButtonComponent{
	
	private boolean checked;
	
	/**
	 * default constructor
	 */
	public PButtonComponent(){
		super();
	}
	
	/**
	 * sets if the component is checked/marked. 
	 * @param checked true if marked otherwise false
	 */
	@Override
	public void setChecked(boolean checked){
		this.checked = checked;
	}
	
	/**
	 * retrieves if the component is checked or not.
	 * @return true if checked otherwise false
	 */
	@Override
	public boolean isChecked(){
		return this.checked;
	}
	
	/**
	 * toggles component's status from checked (true) to not checked (false) and the other way round.
	 */
	@Override
	public void toggleChecked(){
		if(this.checked){
			this.checked = false;
		}else{
			this.checked = true;
		}
	}

}
