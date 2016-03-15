package src.de.tum.bgu.lfk.puicomponents.button;

import java.util.Observable;
import java.util.UUID;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;

/**
 * a simple radio button for using with <a href="http://www.processing.org">processing</a>.
 * within the radiobutton the padding is always 0. The components width and height include the padding but mot the margins
 * @author Mathias Jahnke, Technische Universit&auml;t M&uuml;nchen, <a href="http://www.lfk.bgu.tum.de">Chair of Cartography</a>
 * @version 0.0.2
 * @since 12.02.2015
 */
public class PRadioButton extends PButtonComponent{
	
	private PApplet p;
	
	private String text;
	private PFont textFont;
	
	private float radius;
	
	private int strokeColor;
	private int fillColor;
	private int fillColorMarked;
	private int textColor;
	
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
		super();
		setLocation(x, y);
		this.text = text;
		this.p = p;
		
		this.radius = radius;
		this.setChecked(false);
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
		
		setSize(componentWidth, componentHeight);
		
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
		super();
		this.text = text;
		this.p = p;
		
		setLocation(p.width / 2, p.height / 2);
		
		this.radius = radius;
		this.setChecked(false);
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
	
		setSize(componentWidth, componentHeight);
		
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
	 * draw the radio button.
	 * if the button is checked the button is drawn with a dot inside
	 */
	@Override
	public void draw(){
		if(isChecked() == false){
			p.ellipseMode(PConstants.RADIUS);
			p.strokeWeight(1);
			p.fill(fillColor);
			p.stroke(strokeColor);
			//ellipse(x, y, radius, radius);
			p.ellipse(getX(), getY(), radius, radius);
		}else{
			p.ellipseMode(PConstants.RADIUS);
			p.strokeWeight(1);
			p.fill(fillColor);
			p.stroke(strokeColor);
			//p.ellipse(x, y, radius, radius);
			p.ellipse(getX(), getY(), radius, radius);
			p.ellipseMode(PConstants.CENTER);
			p.fill(fillColorMarked);
			//p.ellipse(x, y, radius, radius);
			p.ellipse(getX(), getY(), radius, radius);
		}
		//draw label
		p.textFont(textFont);
		p.textAlign(PConstants.LEFT, PConstants.CENTER);
		p.fill(textColor);
		//p.text(text, x + radius + (this.componentHeight*0.4f), y - 1);
		p.text(text, getX() + radius + (getHeight() * 0.4f), getY() - 1);
	}
	
//	/**
//	 * draws the radio button on the specified location.
//	 * can be used in PExpandableMenu 
//	 * @param x the x location of the radiobutton
//	 * @param y the y location of the radiobutton
//	 */
//	@Deprecated
//	public void draw(float x, float y){
//		if(isChecked() == false){
//			p.ellipseMode(PConstants.RADIUS);
//			p.strokeWeight(1);
//			p.fill(fillColor);
//			p.stroke(strokeColor);
//			p.ellipse(x, y, radius, radius);
//		}else{
//			p.ellipseMode(PConstants.RADIUS);
//			p.strokeWeight(1);
//			p.fill(fillColor);
//			p.stroke(strokeColor);
//			p.ellipse(x, y, radius, radius);
//			p.ellipseMode(PConstants.CENTER);
//			p.fill(fillColorMarked);
//			p.ellipse(x, y, radius, radius);
//		}
//		//draw label
//		p.textFont(textFont);
//		p.textAlign(PConstants.LEFT, PConstants.CENTER);
//		p.fill(textColor);
//		p.text(text, x + radius + 6, y - 2);
//	}
	
	
	/**
	 * if e.g. the mouse is inside the radio button area or not.
	 * the same like is inside(float x, float y) but with regard to PComponent.
	 * @param x
	 * @param y
	 * @return true if inside otherwise false
	 */
	public boolean contains(float x, float y){
		if(PApplet.dist(getX(), getY(), x, y) < this.radius){
			//System.out.println("contains: PRadioButton: " + getComponentId());
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
	 * @param fillColor the fill color
	 * @param fillColorMarked the fill color if marked
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
	 * @param textFont the font of the text
	 */
	public void setTextStyling(int textColor, PFont textFont){
		this.textColor = textColor;
		if(textFont != null){
			this.textFont = textFont;
		}
		
	}
	

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


	public void setPadding(float top, float right, float bottom, float left) {
		this.paddingTop = 0;
		this.paddingRight = 0;
		this.paddingBottom = 0;
		this.paddingLeft = 0;
	}
}
