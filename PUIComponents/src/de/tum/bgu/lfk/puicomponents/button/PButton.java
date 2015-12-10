package src.de.tum.bgu.lfk.puicomponents.button;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;


/**
 * a simple button class to use within <a href="http://www.processing.org">processing</a>.
 * action handling should by done via the e.g. mouseClicked() functions etc. </br>
 * at the moment the button does not adjust themself to the caption (text) </br>
 * 
 * @author Mathias Jahnke, Technische Universit&auml;t M&uuml;nchen, <a href="http://www.lfk.bgu.tum.de">Chair of Cartography</a>
 * @version 0.0.2
 * @since 12.06.2014
 */
public class PButton implements PIButton{
	private PApplet p;
	
	//adapt the button width and height to the width and height of the label
	private boolean adaptToLabelWidth;
	private boolean adaptToLabelHeight;

	private boolean checked;
	//button width and height
	private float height;
	private float width;
	//button position on screen
	private float x;
	private float y;

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
	 * default constructor </br>
	 * should not be used!
	 */
	public PButton() {
	}

	/**
	 * parameterized constructor.
	 * x, y will specify the center point of the button object.
	 * @param x x-value of the button's center point in screen coordinates (pixel)
	 * @param y y-value of the button's center point in screen coordinates (pixel)
	 * @param buttonWidth width of the button in pixel
	 * @param buttonHeight height of the button in pixel
	 * @param p the PApplet
	 */
	@Deprecated
	public PButton(float x, float y, float buttonWidth, float buttonHeight, PApplet p) {
		this.p = p;
		this.width = buttonWidth;
		this.height = buttonHeight;
		this.x = x;
		this.y = y;
		this.checked = false;
		this.text = "";

		this.stroke = 0; //40
		this.strokeHighlight = 0; //230
		this.fill = null; // noFill()
		this.fillHighlight = null; //125
		this.textColor = 0; //230
		this.textColorHighlight = 0; //230
		
		this.cornerRadius = 0;
		this.outlineWeight = 0;
	}
	
	/**
	 * parameterized constructor.
	 * x, y will specify the center point of the button object
	 * @param x x-value of the buttons center point in screen coordinates
	 * @param y y-value of the buttons center point in screen coordinates
	 * @param buttonWidth button width
	 * @param buttonHeight button height
	 * @param text the button label
	 * @param p the PApplet to draw on
	 */
	@Deprecated
	public PButton(float x, float y, float buttonWidth, float buttonHeight, String text, PApplet p){
		this.p = p;
		this.width = buttonWidth;
		this.height = buttonHeight;
		this.x = x;
		this.y = y;
		this.checked = false;
		this.textFont = this.p.createFont("Arial", 14, true);
		this.text = text;

		this.stroke = 0; //40
		this.strokeHighlight = 0; //230
		this.fill = null; // noFill()
		this.fillHighlight = null; //125
		this.textColor = 0; //230
		this.textColorHighlight = 0; //230
		
		this.cornerRadius = 0;
		this.outlineWeight = 0;
		
		this.adaptToLabelWidth = true;
		this.adaptToLabelHeight = true;
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
		//this.buttonWidth = buttonWidth;
		//this.buttonHeight = buttonHeight;
		this.x = x;
		this.y = y;
		this.checked = false;
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
		
		this.adaptToLabelWidth = true;
		this.adaptToLabelHeight = true;
		
		this.p.textFont(this.textFont);
		if(text.length() == 0){
			this.width = 55.f;
			this.height = 22.f;
		}else{
			if(adaptToLabelWidth == true){
				float textWidth = this.p.textWidth(text);
				this.width = textWidth + 9;
				//System.out.println(this.width);
			} 
			if (adaptToLabelHeight == true){
				float textHeight = this.p.textAscent() + p.textDescent();
				this.height = textHeight + 7;
				//System.out.println(this.height);
			}
		}
	}

	/** 
	 * set to true if the button is clicked
	 * @param clicked 
	 */
	@Override
	public void setChecked(boolean clicked) {
		this.checked = clicked;
	}

	/** 
	 * return true if the button is clicked otherwise false
	 * @return boolean 
	 */
	public boolean isChecked() {
		return checked;
	}
	
	/**
	 * changes the status of checked from true to false or vice versa
	 */
	public void changeChecked(){
		if(checked == true){
			checked = false;
		}else
			checked = true;
	}
	
	/**
	 * sets a new PFont for the button label.
	 * If width or height not explicitly set the button adapt automatically to the new font
	 * @param labelFont the new PFont
	 */ 
	public void setFont(PFont labelFont){
		this.textFont = labelFont;
		this.p.textFont(this.textFont);
		if(adaptToLabelWidth == true){
			float textWidth = this.p.textWidth(text);
			this.width = textWidth + 9;
			//System.out.println(this.buttonWidth);
		} 
		if (adaptToLabelHeight == true){
			float textHeight = this.p.textAscent() + p.textDescent();
			this.height = textHeight + 7;
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
		if(adaptToLabelWidth == true){
			float textWidth = this.p.textWidth(text);
			this.width = textWidth + 9;
			//System.out.println(this.buttonWidth);
		} 
		if (adaptToLabelHeight == true){
			float textHeight = this.p.textAscent() + p.textDescent();
			this.height = textHeight + 7;
			//System.out.println(this.buttonHeight);
		}
	}
	
	/**
	 * return the text to display with the button
	 * @return String 
	 */
	@Override
	public String getText() {
		return this.text;
	}
	
	/**
	 * has to be true if the button should adapt themself to the label size
	 * @param adaptToLabelWidth boolean 
	 */
	@Deprecated
	public void adaptToLabel(boolean adaptToLabelWidth, boolean adaptToLabelHeight){
		this.adaptToLabelWidth = adaptToLabelWidth;
		this.adaptToLabelHeight = adaptToLabelHeight;
	}
	
	/**
	 * 
	 * @return the button width
	 */
	public float getWidth(){
		return this.width;
	}
	
	/**
	 * to set the buttonWidth manual. 
	 * if the button width is set manually the button does no longer adapt them self to a new font or text
	 * @param width
	 */
	public void setWidth(float width){
		this.width = width;
		this.adaptToLabelWidth = false;
	}
	
	/**
	 * 
	 * @return the button height
	 */
	public float getHeight(){
		return this.height;
	}
	
	/**
	 * sets the button height.
	 * if the button height is set manually the button does no longer adapt them self to a new font or text.
	 * @param height
	 */
	public void setHeight(float height){
		this.height = height;
		this.adaptToLabelHeight = false;
	}
	
	/**
	 * to set the corner radius of the button.
	 * the default value for corner radius is 0.
	 * @param cornerRadius
	 */
	public void setCornerRadius(float cornerRadius){
		this.cornerRadius = cornerRadius;
	}
	
	/**
	 * to set the outline weight of the button (stroke thickness)
	 * @param outlineWeight
	 */
	public void setOutlineWeight(float outlineWeight){
		this.outlineWeight = outlineWeight;
	}
	
	/**
	 * sets the margin for top, right, bottom and left
	 * @param top
	 * @param right
	 * @param bottom
	 * @param left
	 */
	public void setMargin(float top, float right, float bottom, float left){
		this.marginTop = top;
		this.marginRight = right;
		this.marginBottom = bottom;
		this.marginLeft = left;
	}
	
	/**
	 * sets the margin for all four sides (top, right, bottom and left)
	 * @param all
	 */
	public void setMargin(float all){
		this.marginTop = all;
		this.marginRight = all;
		this.marginBottom = all;
		this.marginLeft = all;
	}
	
	/**
	 * sets the padding for top, right, bottom, left.
	 * @param top
	 * @param right
	 * @param bottom
	 * @param left
	 */
	public void setPadding(float top, float right, float bottom, float left){
		this.paddingTop = top;
		this.paddingRight = right;
		this.paddingBottom = bottom;
		this.paddingLeft = left;
	}
	
	/**
	 * sets the padding for all four sides (top, right, bottom and left)
	 * @param all
	 */
	public void setPadding(float all){
		this.paddingTop = all;
		this.paddingRight = all;
		this.paddingBottom = all;
		this.paddingLeft = all;
	}
	
	

	/**
	 * draws the button every time the function is called
	 * should be placed within draw-function of the processing sketch
	 */
	public void draw() {
		//to center text and button
		p.rectMode(PConstants.CENTER);
		p.textAlign(PConstants.CENTER, PConstants.CENTER);
		//button outline thickness
		p.strokeWeight(outlineWeight);
		
		if (this.checked) {
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
			p.rect(x, y, width, height, cornerRadius);
			//button text color if clicked
			p.fill(textColorHighlight);  
			//drawing the button label
			p.textFont(this.textFont);
			
			p.text(text, this.x, this.y - (p.textAscent() * 0.1f));
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
			p.rect(x, y, width, height, cornerRadius);
			//Button text color if NOT clicked
			p.fill(textColor);  
			//drawing the button label
			p.textFont(this.textFont);
			p.text(text, this.x, this.y - (p.textAscent() * 0.1f));
		}
	}

	/**
	 * <b>this method is renamed</b>
	 * set the outline and stroke color of the button if not called standard values are used
	 * at this stage only int values can be passed to the function
	 * to use this function with rgb colors use the color()-function as an argument. the color() function will map
	 * the rgb values to a integer
	 * @param stroke color of the button border if the button is not clicked
	 * @param strokeHighlight color of the button border if the button is clicked
	 * @param fill color of the button fill if the button is not clicked
	 * @param fillHighlight color of the button fill if the button is clicked
	 */
	@Deprecated
	public void setButtonLook(Integer stroke, Integer strokeHighlight, Integer fill, Integer fillHighlight){
		this.stroke = stroke;
		this.strokeHighlight = strokeHighlight;
		this.fill = fill;
		this.fillHighlight = fillHighlight;
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
	 * sets the caption (text) of the button and the styling of the caption
	 * @param text the label of the button
	 * @param textColor the text color 
	 * @param textColorHighlight the text color if the button is highlighted
	 */
	@Deprecated
	public void setButtonLabel(String text, int textColor, int textColorHighlight) {
		if(textFont != null){
			p.textFont(this.textFont);
		}
		this.text = text;
		this.textColor = textColor;
		this.textColorHighlight = textColorHighlight;
	}
	
	/**
	 * sets the color for the button text.
	 * If not called default values are used. </br>
	 * Differentiates between a color and a color if the button is clicked or used as a toggle button
	 * @param textColor
	 * @param textColorHighlight
	 */
	public void setTextStyling(int textColor, int textColorHighlight){
		this.textColor = textColor;
		this.textColorHighlight = textColorHighlight;
	}
	
	/**
	 * TODO
	 * @param text the label text of the button and the styling of the caption
	 * @param buttonFont the PFont of the button
	 * @param buttonTextColor the caption (text) color
	 * @param buttonTextColorHighlight the caption (text) color if the button is highlighted
	 */
	@Deprecated
	public void setButtonLabel(String text, PFont buttonFont, int buttonTextColor, int buttonTextColorHighlight) {
		if(textFont != null){
			p.textFont(this.textFont);
		}
		this.text = text;
		this.textFont = buttonFont;
		this.textColor = buttonTextColor;
		this.textColorHighlight = buttonTextColorHighlight;
	}



	//checks if a point lies inside the button area or not
	/**
	 * checks if the x and y coordinates are falling inside the button area
	 * @param x the x coordinate 
	 * @param y the y coordinate
	 * @return true if inside otherwise false
	 */
	@Override
	public boolean isInside(float x, float y) {
		boolean returnValue = false;
		//x is inside
		if ((x > this.x - width/2) && (x < this.x + width/2)) {
			//y is inside 
			if ((y > this.y - height/2) && (y < this.y + height/2)) {
				returnValue = true;
			}
		}
		return returnValue;
	}
	
}
