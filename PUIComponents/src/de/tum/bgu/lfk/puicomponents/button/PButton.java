package src.de.tum.bgu.lfk.puicomponents.button;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;


/**
 * A simple button class to use within <a href="http://www.processing.org">processing</a>.
 * Action handling should by done via the e.g. mouseClicked() functions etc. <br>
 * 
 * @author Mathias Jahnke, Technische Universit&auml;t M&uuml;nchen, <a href="http://www.lfk.bgu.tum.de">Chair of Cartography</a>
 * @version 0.0.2
 * @since 12.06.2014
 */

public class PButton extends PButtonComponent{
	private PApplet p;
	
	//adapt the button width and height to the width and height of the label
	private boolean adaptToLabel;

	//only gray values so far
	//assign NULL if the value should not be set
	private Integer stroke;
	private Integer strokeHighlight;
	private Integer fill;
	private Integer fillHighlight;
	private int textColor;
	private int textColorHighlight;
	
	private float marginTop, marginRight, marginBottom, marginLeft;
	private float paddingTop, paddingRight, paddingBottom, paddingLeft;
	
	private float cornerRadius;
	private float outlineWeight;

	private PFont textFont;

	private String text;

	/**
	 * creates a new PButtonobject. <br> All attributes have to be set by appropriate functions like setSize, setLocaten etc. 
	 */
	public PButton() {
	}

	
	/**
	 * creates a new PButton object. With its center point at x, y and a size of width and height.
	 * The label font is Arial 14point. to change this use the appropriate functions. 
	 * @param x x-value of the buttons center point in screen coordinates
	 * @param y y-value of the buttons center point in screen coordinates
	 * @param width button width
	 * @param height button height
	 * @param label the button label
	 * @param p the PApplet to draw on
	 */
	public PButton(float x, float y, float width, float height, String label, PApplet p){
		this.p = p;

		this.setSize(width, height);
		
		this.setLocation(x, y);

		this.setChecked(false);
		this.textFont = this.p.createFont("Arial", 14, true);
		this.text = label;

		this.stroke = 0; //40
		this.strokeHighlight = 0; //230
		this.fill = null; // noFill()
		this.fillHighlight = null; //125
		this.textColor = 0; //230
		this.textColorHighlight = 0; //230
		
		this.cornerRadius = 0;
		this.outlineWeight = 0;
		
		this.adaptToLabel = false;
		
	}
	
	/**
	 * 
	 * @param x x-value of the buttons center point
	 * @param y y-value of the buttons center point
	 * @param text the label of the button
	 * @param p PApplet
	 */
	public PButton(float x, float y, String text, PApplet p){
		this.p = p;

		this.setLocation(x, y);

		this.setChecked(false);
		this.textFont = this.p.createFont("Arial", 14, true);
		
		this.text = text;

		this.stroke = 0; //40
		this.strokeHighlight = 0; //230
		this.fill = 255; // noFill()
		this.fillHighlight = null; //125
		this.textColor = 0; //230
		this.textColorHighlight = 0; //230
		
		this.cornerRadius = 0;
		this.outlineWeight = 0;
		
		this.adaptToLabel = true;
		
		this.p.textFont(this.textFont);
		
		float textWidth = 0;
		float textHeight = 0;
		
		if(text.length() == 0){
			this.setSize(55.f, 22.f);
		}else if(adaptToLabel == true){
				textWidth = this.p.textWidth(text) + 9;
				textHeight = this.p.textAscent() + p.textDescent() + 7;
				this.setSize(textWidth, textHeight);;
		}
	}
	
	/**
	 * if set to true the Button adapt their width and height to the label size (text and font)
	 * @param adapt true if the label should adapt otherwise false
	 */
	public void setAdaptToLabel(boolean adapt){
		this.adaptToLabel = adapt;
	}
	
	
	/**
	 * checks whether the button adapts to the label size (text and font)
	 * @return true if the button is adapting otherwise false
	 */
	public boolean isAdaptToLabel(){
		return this.adaptToLabel;
	}
	
	
	/**
	 * checks if the PComponent contains the point (x, y).
	 * @param x the x coordinate 
	 * @param y the y coordinate
	 * @return true if the PCOmponent contains the point(x, y) otherwise false
	 */
	@Override
	public boolean contains(float x, float y) {
		boolean returnValue = false;
		//x is inside
		if ((x > getX() - getWidth()/2) && (x < getX() + getWidth()/2)) {
			//y is inside 
			if ((y > getY() - getHeight()/2) && (y < getY() + getHeight()/2)) {
				returnValue = true;
			}
		}
		return returnValue;
	}

	
	/**
	 * draws the button every time the function is called
	 * should be placed within draw-function of the processing sketch
	 */
	@Override
	public void draw() {
		//to center text and button
		p.rectMode(PConstants.CENTER);
		p.textAlign(PConstants.CENTER, PConstants.CENTER);
		//button outline thickness
		p.strokeWeight(outlineWeight);
	
		if(isChecked()){
			//button outline color if clicked
			if(strokeHighlight == null){
				p.noStroke();
			}else{
				p.stroke(strokeHighlight);
			}
			//button fill color if clicked 
			if(fillHighlight == null){
				p.noFill();
			}else {
				p.fill(fillHighlight);
			}
			//draw the button at position x, y and buttonWidth and buttonHeight and a corner-radius of 0
			p.rect(getX(), getY(), getWidth(), getHeight(), cornerRadius);
			//button text color if clicked
			p.fill(textColorHighlight);  
			//drawing the button label
			p.textFont(this.textFont);
			
			p.text(text, getX(), getY() - (p.textAscent() * 0.1f));
		}
		else {
			//button outline color if NOT clicked
			if(stroke == null){
				p.noStroke();
			}else{
				p.stroke(stroke);
			}
			//button fill color if NOT clicked
			if(fill == null){
				p.noFill();
			}else{
				p.fill(fill);
			}
			//draw the button at position x, y and buttonWidth and buttonHeight and a corner radius of 0
			p.rect(getX(), getY(), getWidth(), getHeight(), cornerRadius);
			//Button text color if NOT clicked
			p.fill(textColor);  
			//drawing the button label
			p.textFont(this.textFont);
			p.text(text, getX(), getY() - (p.textAscent() * 0.1f));
		}
	}

	/**
	 * sets a new PFont for the button label.
	 * If width or height not explicitly set the button adapt automatically to the new font
	 * @param labelFont the new PFont
	 */ 
	public void setFont(PFont labelFont){
		this.textFont = labelFont;
		this.p.textFont(this.textFont);
		if(adaptToLabel == true){
			float textWidth = this.p.textWidth(text) + 9;
			float textHeight = this.p.textAscent() + p.textDescent() + 7;
			this.setSize(textWidth, textHeight);
			//System.out.println(this.buttonWidth);
			//System.out.println(this.buttonHeight);
		} 
	}
	
	
	/**
	 * sets the button text. 
	 * if the width or height not explicitly set the button size adapt automatically to the new text
	 * @param text the new button text
	 */
	public void setText(String text){
		this.text = text;
		this.p.textFont(this.textFont);
		if(adaptToLabel == true){
			float textWidth = this.p.textWidth(text) + 9;
			float textHeight = this.p.textAscent() + p.textDescent() + 7;
			this.setSize(textWidth, textHeight);
			//System.out.println(this.buttonWidth);
			//System.out.println(this.buttonHeight);
		} 
	}
	
	/**
	 * return the text to display with the button
	 * @return String 
	 */
	public String getText() {
		return this.text;
	}
	
	/**
	 * to set the corner radius of the button.
	 * the default value for corner radius is 0.
	 * @param cornerRadius the corner radius 
	 */
	public void setCornerRadius(float cornerRadius){
		this.cornerRadius = cornerRadius;
	}
	
	/**
	 * to set the outline weight of the button (stroke thickness)
	 * @param outlineWeight the outline weight
	 */
	public void setOutlineWeight(float outlineWeight){
		this.outlineWeight = outlineWeight;
	}
	
	/**
	 * sets the margin for top, right, bottom and left
	 * @param top margin in pixel
	 * @param right margin in pixel
	 * @param bottom margin in pixel
	 * @param left margin in pixel
	 */
	public void setMargin(float top, float right, float bottom, float left){
		this.marginTop = top;
		this.marginRight = right;
		this.marginBottom = bottom;
		this.marginLeft = left;
	}
	
	/**
	 * sets the margin for all four sides (top, right, bottom and left)
	 * @param all value for all margins in pixel
	 */
	public void setMargin(float all){
		this.marginTop = all;
		this.marginRight = all;
		this.marginBottom = all;
		this.marginLeft = all;
	}
	
	/**
	 * sets the padding for top, right, bottom, left.
	 * @param top padding in pixel
	 * @param right padding in pixel
	 * @param bottom padding in pixel
	 * @param left padding in pixel
	 */
	public void setPadding(float top, float right, float bottom, float left){
		this.paddingTop = top;
		this.paddingRight = right;
		this.paddingBottom = bottom;
		this.paddingLeft = left;
	}
	
	/**
	 * sets the padding for all four sides (top, right, bottom and left)
	 * @param all value for all paddings in pixel
	 */
	public void setPadding(float all){
		this.paddingTop = all;
		this.paddingRight = all;
		this.paddingBottom = all;
		this.paddingLeft = all;
	}
	
	
	/**
	 * set the outline and stroke color of the button if not called standard values are used
	 * at this stage only int values can be passed to the function
	 * to use this function with rgb colors use the color()-function as an argument. the color() function will map
	 * the rgb values to a integer
	 * @param stroke color of the button border if the button is not clicked
	 * @param strokeHighlight color of the button border if the button is clicked
	 * @param fill color of the button fill if the button is not clicked
	 * @param fillHighlight color of the button fill if the button is clicked
	 */
	public void setStyling(Integer stroke, Integer strokeHighlight, Integer fill, Integer fillHighlight){
		this.stroke = stroke;
		this.strokeHighlight = strokeHighlight;
		this.fill = fill;
		this.fillHighlight = fillHighlight;
	}

	//sets the text for the button no check if the text fits into the button 
	/**
	 * sets the color for the button text.
	 * If not called default values are used. <br>
	 * Differentiates between a color and a color if the button is clicked or used as a toggle button
	 * @param textColor the buttons text label color 
	 * @param textColorHighlight the buttons text label color if the button is highlighted
	 */
	public void setTextStyling(int textColor, int textColorHighlight){
		this.textColor = textColor;
		this.textColorHighlight = textColorHighlight;
	}
	
}
