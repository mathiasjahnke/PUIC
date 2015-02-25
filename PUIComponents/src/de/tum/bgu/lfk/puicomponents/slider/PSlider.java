package de.tum.bgu.lfk.puicomponents.slider;

import processing.core.PApplet;
import processing.core.PConstants;

/**
 * a simple slider for using with <a href="http://www.processing.org">processing</a>. 
 * the class belongs to the custom ui controls.
 * @author Mathias Jahnke, Technische Universit&auml;t M&uuml;nchen, <a href="http://www.lfk.bgu.tum.de">Chair of Cartography</a>
 * @version 0.0.1
 * @since 19.02.2015
 */
public class PSlider {
	
	private PApplet p;
	
	private float x;
	private float y;
	
	private float width;
	private float height;
	
	private float maximum;
	private float minimum;
	private float value;
	
	/**
	 * Standard constructor </br>
	 * <b>do nut use</b>
	 */
	public PSlider(){
		
	}
	
	/**
	 * parameterized constructor
	 * @param x x-value of the sliders center point in screen coordinates
	 * @param y y-value of the sliders center point in screen coordinates
	 * @param width the width
	 * @param height the height
	 * @param p the PApplet to draw on
	 */
	public PSlider(float x, float y, float width, float height, PApplet p){
		
		this.p = p;
		
		this.x = x;
		this.y = y;
		
		this.width = width;
		this.height = height;
	}
	
	/**
	 * 
	 * @param maximum
	 */
	public void setMaximum(float maximum){
		this.maximum = maximum;
	}
	
	/**
	 * 
	 * @param minimum
	 */
	public void setMinimum(float minimum){
		this.minimum = minimum;
	}
	
	/**
	 * set the initial value of the slider position
	 * @param value
	 */
	public void setValue(float value){
		this.value = value;
	}
	
	/**
	 * gets the actual value of the slider
	 * @return return the value of the tickmark
	 * 
	 */
	public float getValue(){
		return value;
	}
	
	/**
	 * 
	 */
	public void draw(){
		p.rectMode(PConstants.CENTER);
		p.rect(x, y, width, height);
	}
	
	
	
	
	
}
