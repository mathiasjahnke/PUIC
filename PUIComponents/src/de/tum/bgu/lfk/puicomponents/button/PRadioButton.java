package de.tum.bgu.lfk.puicomponents.button;

import java.util.Observable;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;

/**
 * a simple radio button for using with <a href="http://www.processing.org">processing</a>.
 * within the radiobutton the padding is always 0. The components width and height include the padding but mot the margins
 * @author Mathias Jahnke, Technische Universit&auml;t M&uuml;nchen, <a href="http://www.lfk.bgu.tum.de">Chair of Cartography</a>
 * @version 0.0.1
 * @since 12.02.2015
 */
public class PRadioButton extends Observable implements PIButton, PIComponent{
	
	private PApplet p;
	
	private float x;
	private float y;
	
	private String text;
	private PFont textFont;
	
	private float radius;
	
	private int strokeColor;
	private int fillColor;
	private int fillColorMarked;
	private int textColor;
	
	private boolean checked;
	
	//width and height of the whole component including the graphic and the text
	private float componentWidth;
	private float componentHeight;
	
	private float marginTop, marginRight, marginLeft, marginBottom;
	private float paddingTop, paddingRight, paddingLeft, paddingBottom;
	
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
		
		this.strokeColor = p.color(0);
		this.fillColor = p.color(255);
		this.fillColorMarked = p.color(0);
		this.textColor = p.color(0);
		
		//Calculate component width and height
		if((this.p.textAscent() + this.p.textDescent() <= (radius * 2))){
			this.componentHeight = radius * 2;
		}else{
			this.componentHeight = this.p.textAscent() + this.p.textDescent();
		}
		this.componentWidth = this.p.textWidth(this.text) + radius + (this.componentHeight * 0.4f);
		
		this.paddingTop = 0;
		this.paddingRight = 0;
		this.paddingBottom = 0;
		this.paddingLeft = 0;
		
		this.marginTop = 4;
		this.marginRight = 6;
		this.marginBottom = 4;
		this.marginLeft = 6;
	}
	
	/**
	 * initializes a new radio button and places the button in the middle of the canvas.
	 * the {@code setLocation float, float} function should be used afterwards.
	 * @param radius the radius of the radio button
	 * @param text the label of the radio button
	 * @param p the {@code PApplet}
	 */
	public PRadioButton(float radius, String text, PApplet p){
		
		this.text = text;
		this.p = p;
		
		this.x = p.width/2;
		this.y = p.height/2;
		
		this.radius = radius;
		this.checked = false;
		this.textFont = this.p.createFont("Arial", 14, true);
		
		this.strokeColor = p.color(0);
		this.fillColor = p.color(255);
		this.fillColorMarked = p.color(0);
		this.textColor = p.color(0);
		
		//Calculate component width and height
		if((this.p.textAscent() + this.p.textDescent() <= (radius * 2))){
			this.componentHeight = radius * 2;
		}else{
			this.componentHeight = this.p.textAscent() + this.p.textDescent() + paddingTop + paddingBottom;
		}
		this.componentWidth = this.p.textWidth(this.text) + radius + (this.componentHeight * 0.4f) + paddingLeft + paddingRight;
	
		this.paddingTop = 0;
		this.paddingRight = 0;
		this.paddingBottom = 0;
		this.paddingLeft = 0;
		
		this.marginTop = 4;
		this.marginRight = 6;
		this.marginBottom = 4;
		this.marginLeft = 6;
	}
	
	/**
	 * convenience function for flyout menu
	 * @param x 
	 * @param y
	 */
	@Override
	public void setLocation(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * can be set via mouseClicked() function to indicate if the button is checked (marked)
	 * @param checked boolean true if checked otherwise false
	 */
	@Override
	public void setChecked(boolean checked){
		setChanged();
		this.checked = checked;
		notifyObservers(this);
	}
	
	/**
	 * queries the status of the radio button
	 * @return boolean true if checked otherwise false
	 */
	@Override
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
			p.fill(fillColor);
			p.stroke(strokeColor);
			p.ellipse(x, y, radius, radius);
		}else{
			p.ellipseMode(PConstants.RADIUS);
			p.strokeWeight(1);
			p.fill(fillColor);
			p.stroke(strokeColor);
			p.ellipse(x, y, radius, radius);
			p.ellipseMode(PConstants.CENTER);
			p.fill(fillColorMarked);
			p.ellipse(x, y, radius, radius);
		}
		//draw label
		p.textFont(textFont);
		p.textAlign(PConstants.LEFT, PConstants.CENTER);
		p.fill(textColor);
		p.text(text, x + radius + (this.componentHeight*0.4f), y - 1);
	}
	
	/**
	 * draws the radio button on the specified location.
	 * can be used in PExpandableMenu 
	 * @param x the x location of the radiobutton
	 * @param y the y location of the radiobutton
	 */
	@Deprecated
	public void draw(float x, float y){
		if(this.checked == false){
			p.ellipseMode(PConstants.RADIUS);
			p.strokeWeight(1);
			p.fill(fillColor);
			p.stroke(strokeColor);
			p.ellipse(x, y, radius, radius);
		}else{
			p.ellipseMode(PConstants.RADIUS);
			p.strokeWeight(1);
			p.fill(fillColor);
			p.stroke(strokeColor);
			p.ellipse(x, y, radius, radius);
			p.ellipseMode(PConstants.CENTER);
			p.fill(fillColorMarked);
			p.ellipse(x, y, radius, radius);
		}
		//draw label
		p.textFont(textFont);
		p.textAlign(PConstants.LEFT, PConstants.CENTER);
		p.fill(textColor);
		p.text(text, x + radius + 6, y - 2);
	}
	
	/**
	 * if e.g. the mouse is inside the radio button area or not
	 * @param x
	 * @param y
	 * @return true if inside otherwise false
	 */
	@Override
	public boolean isInside(float x, float y){
		if(PApplet.dist(this.x, this.y, x, y) < this.radius){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * sets the styling. 
	 * in the main applet the color has to be set via color(grayValue) or color(red, green, blue) etc.
	 * 
	 * @param strokeColor the color of the outline stroke.
	 * @param strokeColorHighlight the color of the outline stroke if marked
	 * @param fillColor the fill color
	 * @param fillColorHighlight the fill color if marked
	 */
	public void setStyling(int strokeColor, int fillColor, int fillColorMarked){
		this.strokeColor = strokeColor;
		this.fillColor = fillColor;
		this.fillColorMarked = fillColorMarked;
	}
	
	/**
	 * sets the text styling.
	 * in the main applet the color has to be set via color(grayValue) or color(red, green, blue) etc.
	 * the textFont can be null.
	 * @param textColor the text color 
	 * @param textColorHighlight the text color if marked
	 * @param textFont the font of the text
	 */
	public void setTextStyling(int textColor, PFont textFont){
		this.textColor = textColor;
		if(textFont != null){
			this.textFont = textFont;
		}
		
	}
	
	/**
	 * returns the PRadioButton text
	 * @return the text as string
	 */
	@Override
	public String getText(){
		return this.text;
	}
	
	/**
	 * returns the components height. Based on the text length and the radius
	 * @return the components width
	 */
	@Override
	public float getComponentWidth(){
		return this.componentWidth;
	}
	
	/**
	 * returns the components width. based on the text height or the radius
	 * @return the components height
	 */
	@Override
	public float getComponentHeight(){
		return this.componentHeight;
	}

	@Override
	public void setMargin(float top, float right, float bottom, float left) {
		this.marginTop = top;
		this.marginRight = right;
		this.marginBottom = bottom;
		this.marginLeft = left;
	}
	
	public float getMarginTop(){
		return marginTop;
	}
	
	public float getMarginBottom(){
		return marginBottom;
	}
	
	public float getMarginRight(){
		return marginRight;
	}
	
	public float getMarginLeft(){
		return marginLeft;
	}
	
	public float getPaddingTop(){
		return paddingTop;
	}
	
	public float getPaddingBottom(){
		return paddingBottom;
	}
	
	public float getPaddingRight(){
		return paddingRight;
	}
	
	public float getPaddingLeft(){
		return paddingLeft;
	}

	@Override
	public void setPadding(float top, float right, float bottom, float left) {
		this.paddingTop = 0;
		this.paddingRight = 0;
		this.paddingBottom = 0;
		this.paddingLeft = 0;
	}

}
