package de.tum.bgu.lfk.puicomponents.button;

import de.tum.bgu.lfk.puicomponents.constants.CheckBoxOptions;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;

/**
 * a simple check box class to use within <a href="http://www.processing.org">processing</a>.
 * action handling should by done via the e.g. mouseClicked() functions etc. </br>
 * The PCheckBox is always a square. </br> </br>
 * the x- and y-coordinate are representing the the center of the PCheckBox
 * @author Mathias Jahnke, Technische Universit&auml;t M&uuml;nchen, <a href="http://www.lfk.bgu.tum.de">Chair of Cartography</a>
 * @version 0.0.1
 * @since 04.07.2014
 */
public class PCheckBox {
	
	private PApplet p;
	
	private float x;
	private float y;
	
	private float checkBoxSize; //always a square
	
	private String text;
	private PFont textFont; 
	
	private boolean checked;
	
	private CheckBoxOptions markerSymbol;
	
	/**
	 * default constructor.</br>
	 * <b>do not use</b> because at the moment not all attributes can be set via methods.
	 */
	public PCheckBox(){
		
	}
	
	/**
	 * parameterized constructor
	 * @param x the check box center point in pixel coordinates
	 * @param y the check box center point in pixel coordinates
	 * @param label the string of the check box label
	 * @param p the PApplet on which to draw the check box
	 */
	public PCheckBox(int x, int y, int checkBoxSize, String label, PApplet p){
		this.p = p;
		this.x = x;
		this.y = y;
		this.checkBoxSize = checkBoxSize;
		this.text = label;
		this.textFont = this.p.createFont("Arial", 14, true);
		this.checked = false;
		this.markerSymbol = CheckBoxOptions.TICKMARK;
	}
	
	/**
	 * sets the status of the check box
	 * @param checked true if checked otherwise false
	 */
	public void setChecked(boolean checked){
		this.checked = checked;
	}
	
	/**
	 * returns true if the check box is checked
	 * @return true if checked otherwise false
	 */
	public boolean isChecked(){
		return checked;
	}
	
	/**
	 * changes the status to true if its false and to false if its true.
	 * to be used in combination with isInside() and e.g. a mousClicked() event
	 */
	public void toggleChecked(){
		if(checked == true){
			checked = false;
		}else
			checked = true;
	}
	
	/**
	 * to set the marker symbol as tickmark or cross 
	 * @param markerSymbol
	 */
	public void setMarkerSymbol(CheckBoxOptions markerSymbol){
		this.markerSymbol = markerSymbol;
	}
	
	/**
	 * checks if the position (x, y) are inside the PCheckBox
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return true if x and y are inside otherwise false
	 */
	public boolean isInside(int x, int y){
		p.rectMode(PConstants.CENTER);
		boolean returnValue = false;
		//x is inside
		if ((x > this.x - checkBoxSize/2) && (x < this.x + checkBoxSize/2)) {
			//y is inside 
			if ((y > this.y - checkBoxSize/2) && (y < this.y + checkBoxSize/2)) {
				returnValue = true;
			}
		}
		return returnValue;
	}
	
	/**
	 * draw the PCheckBox to screen.
	 * differentiating if it is marked or not
	 */
	public void draw(){
		p.rectMode(PConstants.CENTER);
		//draw the box and the label
		p.textAlign(PConstants.LEFT, PConstants.CENTER);
		p.textFont(this.textFont);
		p.fill(0);
		p.text(text, x + checkBoxSize/2 + 5, y - 2);

		p.fill(255);
		p.strokeWeight(1);
		p.rect(x, y, checkBoxSize, checkBoxSize);
		
		//draw checked status
		if(this.checked == true){
			switch(this.markerSymbol){
			case TICKMARK:
				p.strokeWeight(2);
				p.strokeCap(PConstants.SQUARE);
				p.line(x - (checkBoxSize * (2.f/6)), y , x , y + (checkBoxSize * (2.f/6)));
				p.line(x, y + (checkBoxSize * (2.f/6)), x + (checkBoxSize * (2.f/6)), y - (checkBoxSize * (2.f/6)));
				break;
			case CROSS:
				p.strokeWeight(2);
				p.strokeCap(PConstants.SQUARE);
				p.line(x - (checkBoxSize * (2.f/6)), y - (checkBoxSize * (2.f/6)), x + (checkBoxSize * (2.f/6)), y + (checkBoxSize * (2.f/6)));
				p.line(x - (checkBoxSize * (2.f/6)), y + (checkBoxSize * (2.f/6)), x + (checkBoxSize * (2.f/6)), y - (checkBoxSize * (2.f/6)));
				break;
			}
		}
		
	}
	

}
