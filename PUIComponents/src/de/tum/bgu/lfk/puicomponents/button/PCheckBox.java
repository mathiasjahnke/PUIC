package src.de.tum.bgu.lfk.puicomponents.button;

import src.de.tum.bgu.lfk.puicomponents.constants.CheckBoxOptions;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;

/**
 * a simple check box class to use within <a href="http://www.processing.org">processing</a>.
 * action handling should by done via the e.g. mouseClicked() functions etc. <br>
 * The PCheckBox is always a square. <br> <br>
 * the x- and y-coordinate are representing the the center of the PCheckBox
 * @author Mathias Jahnke, Technische Universit&auml;t M&uuml;nchen, <a href="http://www.lfk.bgu.tum.de">Chair of Cartography</a>
 * @version 0.0.1
 * @since 04.07.2014
 */
public class PCheckBox extends PButtonComponent{
	
	private PApplet p;
	
	private String text;
	private PFont textFont; 
	
	private CheckBoxOptions markerSymbol;
	
	
	/**
	 * default constructor.<br>
	 * <b>do not use</b> because at the moment not all attributes can be set via methods.
	 */
	public PCheckBox(){
		
	}
	
	/**
	 * parameterized constructor
	 * @param x the check box center point in pixel coordinates
	 * @param y the check box center point in pixel coordinates
	 * @param checkBoxSize the size of the checkbox in pixel
	 * @param label the string of the check box label
	 * @param p the PApplet on which to draw the check box
	 */
	public PCheckBox(int x, int y, int checkBoxSize, String label, PApplet p){
		this.p = p;
		//this.x = x;
		//this.y = y;
		this.setLocation(x, y);
		//this.checkBoxSize = checkBoxSize;
		this.setSize(checkBoxSize, checkBoxSize);
		this.text = label;
		this.textFont = this.p.createFont("Arial", 14, true);
		//this.checked = false;
		this.setChecked(false);
		this.markerSymbol = CheckBoxOptions.TICKMARK;
	}
	
	
	/**
	 * to set the marker symbol as tickmark or cross. 
	 * @param markerSymbol TICKMARK or CROSS depending on which marker symbol should be used
	 */
	public void setMarkerSymbol(CheckBoxOptions markerSymbol){
		this.markerSymbol = markerSymbol;
	}

	
	/**
	 * true if the coordinates (x, y) are within the PCheckBox area.
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return true if x and y are inside otherwise false
	 */
	@Override
	public boolean contains(float x, float y){
		p.rectMode(PConstants.CENTER);
		boolean returnValue = false;
		//x is inside
		if ((x > this.getX() - this.getWidth()/2) && (x < this.getX() + this.getWidth()/2)) {
			//y is inside 
			if ((y > this.getY() - this.getHeight()/2) && (y < this.getY() + this.getHeight()/2)) {
				returnValue = true;
			}
		}
		return returnValue;
	}
	
	/**
	 * draw the PCheckBox to screen.
	 * differentiating if it is marked or not.
	 */
	public void draw(){
		p.rectMode(PConstants.CENTER);
		//draw the box and the label
		p.textAlign(PConstants.LEFT, PConstants.CENTER);
		p.textFont(this.textFont);
		p.fill(0);
		p.text(text, getX() + this.getWidth()/2 + 5, getY() - 2);

		p.fill(255);
		p.strokeWeight(1);
		p.rect(getX(), getY(), this.getWidth(), this.getHeight());
		
		//draw checked status
		if(this.isChecked() == true){
			switch(this.markerSymbol){
			case TICKMARK:
				p.strokeWeight(2);
				p.strokeCap(PConstants.SQUARE);
				p.line(getX() - (getWidth() * (2.f/6)), getY() , getX() , getY() + (getWidth() * (2.f/6)));
				p.line(getX(), getY() + (getHeight() * (2.f/6)), getX() + (getHeight() * (2.f/6)), getY() - (getHeight() * (2.f/6)));
				break;
			case CROSS:
				p.strokeWeight(2);
				p.strokeCap(PConstants.SQUARE);
				p.line(getX() - (getWidth() * (2.f/6)), getY() - (getWidth() * (2.f/6)), getX() + (getWidth() * (2.f/6)), getY() + (getWidth() * (2.f/6)));
				p.line(getX() - (getHeight() * (2.f/6)), getY() + (getHeight() * (2.f/6)), getX() + (getHeight() * (2.f/6)), getY() - (getHeight() * (2.f/6)));
				break;
			}
		}
		
	}
	

}
