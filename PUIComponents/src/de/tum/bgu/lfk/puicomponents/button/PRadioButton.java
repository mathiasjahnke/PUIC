package de.tum.bgu.lfk.puicomponents.button;

import java.util.Observable;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;

/**
 * a simple radio button for using with <a href="http://www.processing.org">processing</a>.
 * @author Mathias Jahnke, Technische Universit&auml;t M&uuml;nchen, <a href="http://www.lfk.bgu.tum.de">Chair of Cartography</a>
 * @version 0.0.1
 * @since 12.02.2015
 */
public class PRadioButton extends Observable{
	
	private PApplet p;
	
	private float x;
	private float y;
	
	private String text;
	private PFont textFont;
	
	private float radius;
	
	private int radioButtonStrokeColor;
	private int radioButtonFillColor;
	private int radioButtonFillColorMarked;
	private int radioButtonLabelColor;
	
	private boolean checked;
	
	/**
	 * standard constructor.
	 * <b>do not use</b>
	 */
	public PRadioButton(){
		
	}
	
	/**
	 * constructor to place the radio button on location x, y on the canvas
	 * 
	 * @param x x location of the radio button
	 * @param y y location of the radio button
	 * @param text label of the radio button
	 * @param p the applet to draw on
	 */
	public PRadioButton(float x, float y, float radius, String text, PApplet p){
		this.x = x;
		this.y = y;
		this.text = text;
		this.p = p;
		
		this.radius = radius;
		this.checked = false;
		this.textFont = this.p.createFont("Arial", 14, true);
		
		this.radioButtonStrokeColor = p.color(0);
		this.radioButtonFillColor = p.color(255);
		this.radioButtonFillColorMarked = this.radioButtonStrokeColor;
		this.radioButtonLabelColor = p.color(0);
	}
	
	/**
	 * initializes a new radio button and places the button in the middle of the canvas.
	 * the {@code setLocation float, float} function should be used afterwards.
	 * @param radius the radius of the radio button
	 * @param label the label of the radio button
	 * @param p the {@code PApplet}
	 */
	public PRadioButton(float radius, String label, PApplet p){
		
		this.text = label;
		this.p = p;
		
		this.x = p.width/2;
		this.y = p.height/2;
		
		this.radius = radius;
		this.checked = false;
		this.textFont = this.p.createFont("Arial", 14, true);
		
		this.radioButtonStrokeColor = p.color(0);
		this.radioButtonFillColor = p.color(255);
		this.radioButtonFillColorMarked = this.radioButtonStrokeColor;
		this.radioButtonLabelColor = p.color(0);
	}
	
	/**
	 * convenience function for expandable menu
	 * @param x 
	 * @param y
	 */
	public void setLocation(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * can be set via mouseClicked() function to indicate if the button is checked (marked)
	 * @param checked boolean true if checked otherwise false
	 */
	public void setChecked(boolean checked){
		setChanged();
		this.checked = checked;
		notifyObservers(this);
	}
	
	/**
	 * queries the status of the radio button
	 * @return boolean true if checked otherwise false
	 */
	public boolean isChecked(){
		return this.checked;
	}
	
	/**
	 * 
	 */
	public void toggleChecked(){
		setChanged();
		if(this.checked){
			this.checked = false;
		}else{
			this.checked = true;
		}
		notifyObservers(this);
	}
	
	/**
	 * draw the radio button.
	 * if the button is checked the button is drawn with a dot inside
	 */
	public void draw(){
		if(this.checked == false){
			p.ellipseMode(PConstants.RADIUS);
			p.strokeWeight(1);
			p.fill(radioButtonFillColor);
			p.stroke(radioButtonStrokeColor);
			p.ellipse(x, y, radius, radius);
		}else{
			p.ellipseMode(PConstants.RADIUS);
			p.strokeWeight(1);
			p.fill(radioButtonFillColor);
			p.stroke(radioButtonStrokeColor);
			p.ellipse(x, y, radius, radius);
			p.ellipseMode(PConstants.CENTER);
			p.fill(radioButtonFillColorMarked);
			p.ellipse(x, y, radius, radius);
		}
		//draw label
		p.textFont(textFont);
		p.textAlign(PConstants.LEFT, PConstants.CENTER);
		p.fill(radioButtonLabelColor);
		p.text(text, x + radius + 6, y - 2);
	}
	
	/**
	 * draws the radio button on the specified location.
	 * can be used in PExpandableMenu 
	 * @param x the x location of the radiobutton
	 * @param y the y location of the radiobutton
	 */
	public void draw(float x, float y){
		if(this.checked == false){
			p.ellipseMode(PConstants.RADIUS);
			p.strokeWeight(1);
			p.fill(radioButtonFillColor);
			p.stroke(radioButtonStrokeColor);
			p.ellipse(x, y, radius, radius);
		}else{
			p.ellipseMode(PConstants.RADIUS);
			p.strokeWeight(1);
			p.fill(radioButtonFillColor);
			p.stroke(radioButtonStrokeColor);
			p.ellipse(x, y, radius, radius);
			p.ellipseMode(PConstants.CENTER);
			p.fill(radioButtonFillColorMarked);
			p.ellipse(x, y, radius, radius);
		}
		//draw label
		p.textFont(textFont);
		p.textAlign(PConstants.LEFT, PConstants.CENTER);
		p.fill(radioButtonLabelColor);
		p.text(text, x + radius + 6, y - 2);
	}
	
	/**
	 * if e.g. the mouse is inside the radio button area or not
	 * @param x
	 * @param y
	 * @return true if inside otherwise false
	 */
	public boolean isInside(float x, float y){
		if(PApplet.dist(this.x, this.y, x, y) < this.radius){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * sets the button colors. 
	 * the color of the marker is the same as the stroke color.
	 * in the main Applet the color has to be set via color(grayValue) or color(red, green, blue) etc.
	 * 
	 * @param buttonStrokeColor the color of the outline stroke.
	 * @param buttonFillColor the fill color
	 * @param buttonLabelColor the text color
	 */
	public void setButtonColors(int buttonStrokeColor, int buttonFillColor, int buttonLabelColor){
		this.radioButtonStrokeColor = buttonStrokeColor;
		this.radioButtonFillColor = buttonFillColor;
		this.radioButtonFillColorMarked = buttonStrokeColor;
		this.radioButtonLabelColor = buttonLabelColor;
	
	}
	
	public String getText(){
		return this.text;
	}

}
