package de.tum.bgu.lfk.puicomponents.charts;

import de.tum.bgu.lfk.datahandling.table.Table;
import processing.core.PApplet;
import processing.core.PConstants;

/**
 * a bar chart in processing. 
 * its like a time series only one bar per data point.
 * TODO should be independent from ...datahandling.table should work with processing table
 * @author Mathias Jahnke, Technische Universit&auml;t M&uuml;nchen, <a href="http://www.lfk.bgu.tum.de">Department of Cartography</a>
 * @version 0.0.2
 * @since 12.06.2014
 */
public class BarChart {
	//the applet to draw on
	private PApplet p;
	
	// the position on the canvas 
	// and the size of the barChart
	private int x;
	private int y;
	private int w;
	private int h;
	
	// the data of the barChart to visualize
	private Table data;
	private int dataColumn;
	private int idColumn;
	
	//styling of the chart
	private int chartColor; //the charts background color
	private boolean chartOutline; //should the chart have an outline
	private int chartOutlineColor; //the color of the outline
	
	//Styling the bars
	private int dataColor; //the fill color of the bar
	private boolean dataOutline; // should every bar have an outline
	private int dataOutlineColor; //the outline color of the bars 
	
	//Highlighting bars
	private int highlightDataColor;
	private boolean highlightOutline;
	private boolean highlightFill;
	private int highlightStrokeWeight;
	
	//Styling of the axis
	private int axisColor;
	private int axisWeight;
	
	//Chart margins in pixel
	private int topMargin; 
	private int bottomMargin; 
	private int leftMargin;
	private int rightMargin;
	
	//the half of the margin between two bars
	private int interBarMargin;
	
	
	//************* CONSTRUCTORS ************* 
	/**
	 * default constructor
	 */
	public BarChart(){
		
	}
	
	/**
	 * parameterized constructor to have a bar chart covering the whole canvas.
	 * @param p the PApplet to draw on
	 */
	public BarChart(PApplet p){
		this.p = p;
		
		this.x = 0;
		this.y = 0;
		
		this.w = p.width;
		this.h = p.height;
		
		this.chartColor = p.color(218,215,203);
		this.chartOutline = false;
		this.chartOutlineColor = p.color(218,215,203);
		
		this.dataColor = p.color(162,173,0);
		this.dataOutline = false;
		this.dataOutlineColor = p.color(162,173,0);
		
		this.highlightDataColor = p.color(105,8,90);
		this.highlightOutline = false;
		this.highlightFill = true;
		this.highlightStrokeWeight = 1;
		
		this.axisColor = p.color(227,114,34);
		this.axisWeight = 1;
		
		this.topMargin = 3;
		this.bottomMargin = 3;
		this.leftMargin = 3;
		this.rightMargin = 3;
		
		this.interBarMargin = 2;
	}
	
	/**
	 * parameterized constructor
	 * @param p the PApplet to draw on
	 * @param x coordinate of the upper left corner of the chart
	 * @param y coordinate of the upper left corner of the chart
	 * @param w width of the chart in pixel
 	 * @param h height of the chart in pixel
	 */
	public BarChart(PApplet p, int x, int y, int w, int h){
		this.p = p;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		
		this.chartColor = p.color(218,215,203);
		this.chartOutline = false;
		this.chartOutlineColor = p.color(218,215,203);
		
		this.dataColor = p.color(162,173,0);
		this.dataOutline = false;
		this.dataOutlineColor = p.color(162,173,0);
		
		this.highlightDataColor = p.color(105,8,90);
		this.highlightOutline = false;
		this.highlightFill = true;
		this.highlightStrokeWeight = 1;
		
		this.axisColor = p.color(227,114,34);
		this.axisWeight = 1;
		
		this.topMargin = 3;
		this.bottomMargin = 3;
		this.leftMargin = 3;
		this.rightMargin = 3;
		
		this.interBarMargin = 2;
	}
	
	
	
	//************* GETTER & SETTER *************
	
	/**
	 * to set the inter bar margin.
	 * the margin to set has to be indicated as the half between two bars in pixel-
	 * standard value is 2 pixel.
	 * @param margin half of the margin between two bars (in pixel)
	 */
	public void setInterBarMargin(int margin){
		this.interBarMargin = margin;
	}
	
	/**
	 * to set the color for the axis of the bar chart
	 * a standard value is set with the constructors
	 * @param color the axis color to set
	 */
	public void setAxisColor(int color){
		this.axisColor = color;
	}
	
	/**
	 * sets the axis weight
	 * standard value is 1 pixel
	 * @param weight the weight to set in pixel
	 */
	public void setAxisWeight(int weight){
		this.axisWeight = weight;
	}
	
	//************* PRIVATE METHODS *************
	
	/**
	 * draws a rectangle 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	private void drawSingleBar(int x1, int y1, int x2, int y2){
		
		p.rectMode(PConstants.CORNERS);
		p.fill(dataColor);
		if(dataOutline){
			p.stroke(dataOutlineColor);
			p.strokeWeight(1);
		}else{
			p.noStroke();
		}
		
		p.rect(x1, y1, x2, y2);
		
	}
	
	/**
	 * highlights a single bar
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	private void highlightSingleBar(int x1, int y1, int x2, int y2){
		
		p.rectMode(PConstants.CORNERS);
		if(highlightOutline){
			p.stroke(highlightDataColor);
			p.strokeWeight(this.highlightStrokeWeight);
		}else{
			p.noStroke();
		}
		
		if(highlightFill){
			p.fill(highlightDataColor);
		}else {
			p.noFill();
		}
		
		p.rect(x1, y1, x2, y2);
		
	}
	
	/**
	 * returns the maximum value of the column col 
	 * @param col the column from which to drive the maximum value
	 * @return the maximum value
	 */
	private float getMax(int col){

		float max = PConstants.MIN_FLOAT;
		
		for (int row = 0; row < data.getRowCount(); row++) {
			float value = data.getFloatByRowIndex(row, col);
			if (value > max) {
				max = value;
			}
		}
		return max;
	}
	
	/**
	 * returns the minimum value of the column col
	 * @param col the column from which to drive the minimum value
	 * @return the minimum value
	 */
	private float getMin(int col){
		
		float min = PConstants.MAX_FLOAT;
		
		for (int row = 0; row < data.getRowCount(); row++) {
			float value = data.getFloatByRowIndex(row, col);
			if (value < min) {
				min = value;
			}
		}
		return min;
	}
	
	/**
	 * calculate the value where  
	 * @return where the zero line should be placed
	 */
	private int calcZeroLine(){
		float pzl1 = PApplet.norm(Math.abs(getMax(dataColumn)), 0, (Math.abs(getMax(dataColumn)) + Math.abs(getMin(dataColumn))));
		float zl =  PApplet.lerp(0, h - topMargin - bottomMargin, pzl1);
		return Math.round(zl);
	}

	
	//************* PUBLIC METHODS *************
	
	/**
	 * sets the data to draw the bar chart
	 * @param data the data
	 * @param dataCol the column of the data array which should be drawn
	 * @param idCol the column in which to find the id for the data (not yet used)
	 */
	public void setData(Table data, int idCol, int dataCol){
		this.data = data;
		this.dataColumn = dataCol;
		this.idColumn = idCol;
	}
	
	/**
	 * to set the styling for the whole chart
	 * standard values are set with the parameterized constructors
	 * @param chartColor the background color of the chart
	 * @param chartOutline if the chart should have an outline 
	 * @param chartOutlineColor the color of the chart's outline
	 */
	public void setChartStyling(int chartColor, boolean chartOutline, int chartOutlineColor){
		this.chartColor = chartColor;
		this.chartOutline = chartOutline;
		this.chartOutlineColor = chartOutlineColor;
	}
	
	/**
	 * to set the styling for the bars representing the data
	 * standard values are set with the paraemterized constructor
	 * @param dataColor background color of the bars
	 * @param dataOutline if every data bar should have an outline
	 * @param dataOutlineColor the color for the outline
	 */
	public void setDataStyling(int dataColor, boolean dataOutline, int dataOutlineColor){
		this.dataColor = dataColor;
		this.dataOutline = dataOutline;
		this.dataOutlineColor = dataOutlineColor;
	}
	
	/**
	 * sets the margins (spacing) between the data and the border of the chart.
	 * top, bottom, left and right margins can be set
	 * if topMargin + bottomMargin exceeds h or
	 * if leftMargin + rightMarging exceeds w 
	 * standard values which are set by the parameterized constructors are used 
	 * @param topMargin in pixel (standard value 3 pixel)
	 * @param bottomMargin in pixel (standard value 3 pixel)
	 * @param leftMargin in pixel (standard value 3 pixel)
	 * @param rightMargin in pixel (standard value 3 pixel)
	 */
	public void setMargins(int topMargin, int bottomMargin, int leftMargin, int rightMargin){
		if(((topMargin + bottomMargin) > h) || ((leftMargin + rightMargin) > w)){
			System.out.println("margins exceeds " + h + " or " + w + "! using standard values!");
		}else{
			this.topMargin = topMargin;
			this.bottomMargin = bottomMargin;
			this.leftMargin = leftMargin;
			this.rightMargin = rightMargin;
		}
	}
	
	/**
	 * to set the axis Styling at once
	 * @param color the axis color
	 * @param weight the stroke width for the axis
	 */
	public void setAxisStyling(int color, int weight){
		this.axisColor = color;
		this.axisWeight = weight;
	}
	
	/**
	 * sets the highlight styling emphasize the bar.
	 * a combination of the parameters fill and outline is possible
	 * @param color the color to highlight the bar
	 * @param fill true if the bar should highlighted by a fill
	 * @param outline true if the bar should be highlighted by a outline
	 * @param strokeWeight the weight of the outline stroke
	 */
	public void setHighlightStyling(int color, boolean fill, boolean outline, int strokeWeight){
		this.highlightDataColor = color;
		this.highlightFill = fill;
		this.highlightOutline = outline;
		this.highlightStrokeWeight = strokeWeight;
	}
	
	/**
	 * checks if the mouse is located in one of the bars. 
	 * if the mouse is located inside a bar the according data id is returned
	 * therefore the idColumn has to be specified
	 * @param mx position x in screen coordinates like from mouseX
	 * @param my position y in screen coordinates like from mouseY
	 * @return the id of the bar in which the mx and my values are located if not inside "noId" is returned
	 */
	public String isInside(int mx, int my){
	
		int xLength = (x + w - rightMargin) - (x + leftMargin);
		int numValues = data.getRowCount();
		float barWidth = (float)xLength/numValues;
		int zl = calcZeroLine();
		int barHeight;
		String res = "noId";
		for(int i = 1; i < data.getRowCount() + 1; i++){
			//if in x range
			if((mx >= (x + leftMargin + interBarMargin + (Math.round(barWidth * (i - 1))))) && (mx <=  (x + leftMargin + Math.round((barWidth * i) - interBarMargin)))){
				//check if bar pos or neg
				if(data.getFloatByRowIndex(i - 1, dataColumn) < 0){
					//check y for neg
					barHeight = (int) PApplet.map(data.getFloatByRowIndex(i - 1, dataColumn), 0, getMin(dataColumn), 0, h - topMargin - bottomMargin - zl);
					
					//System.out.println(p.mouseX + " : " + p.mouseY);
					//System.out.println("N bottom: " + (y + zl + topMargin) + " : " + "top :" + (y + zl + barHeight - bottomMargin));
					
					if((my >= (y + zl + topMargin)) && (my <= (y + zl + barHeight + topMargin))){
						res = data.getStringByRowIndex(i - 1, idColumn);
					}
				}else{
					//check y for pos
					barHeight = (int) PApplet.map(data.getFloatByRowIndex(i - 1, dataColumn), 0, getMax(dataColumn), 0, zl);
					
					//System.out.println(p.mouseX + " : " + p.mouseY);
					//System.out.println("P bottom: " + (y + zl + topMargin) + " : "+ "top: " + (y + zl - barHeight + topMargin));
					
					if((my <= (y + zl + topMargin)) && (my >= (y + zl - barHeight + topMargin))){
						res = data.getStringByRowIndex(i - 1, idColumn);
					}
				}
			}
		}
		return res;
	}
	
	/**
	 * highlights the bar according the the id
	 * @param id the id to bar which should be highlighted
	 */
	public void highlightData(String id){
		
		int xLength = (x + w - rightMargin) - (x + leftMargin);
		int numValues = data.getRowCount();
		float barWidth = (float)xLength/numValues;
		int zl = calcZeroLine();
		int barHeight;
		
		for(int i = 1; i < data.getRowCount() + 1; i++){
			if(data.getStringByRowIndex(i - 1, idColumn) == id){
				if(data.getFloatByRowIndex(i - 1, dataColumn) < 0){
					barHeight = (int) PApplet.map(data.getFloatByRowIndex(i - 1, dataColumn), 0, getMin(dataColumn), 0, h - topMargin - bottomMargin - zl);
					highlightSingleBar(x + leftMargin + (Math.round(barWidth * (i - 1)) + interBarMargin), (int)(y + zl + topMargin), x + leftMargin + Math.round((barWidth * i) - interBarMargin), (int)(y + zl + topMargin + barHeight));
					System.out.println("highlight:");
					System.out.println("x1: " + (x + leftMargin + (Math.round(barWidth * (i - 1)) + interBarMargin)) + " y1: " + (y + zl + topMargin) + " x2: " + (x + leftMargin + Math.round((barWidth * i) - interBarMargin)) + " y2: " + (y + zl + barHeight + topMargin));
				}else{
					barHeight = (int) PApplet.map(data.getFloatByRowIndex(i - 1, dataColumn), 0, getMax(dataColumn), 0, zl);
					highlightSingleBar(x + leftMargin + (Math.round(barWidth * (i - 1)) + interBarMargin), (int)(y + zl + topMargin), x + leftMargin + (Math.round(barWidth * i) - interBarMargin), (int)(y + zl + topMargin - barHeight));
				}
			}
		}
		//draw x axis
		p.stroke(axisColor);
		p.strokeWeight(axisWeight);
		p.line(x + leftMargin, y + zl + topMargin, x + w - rightMargin, y + zl + topMargin);
		//draw y axis
		p.line(x + leftMargin, y + h - bottomMargin, x + leftMargin, y + topMargin);
	}
	
	/**
	 * have to be called to draw the chart.
	 * values for chart styling like color and outline can be set
	 * via the different set methods otherwise standard values are set via constructors
	 */
	public void drawChart(){
		//chartStyling and drawing of the chart area
		p.fill(chartColor);
		if(chartOutline){
			p.stroke(chartOutlineColor);
			p.strokeWeight(1);
		}else{
			p.noStroke();
		}
		p.rectMode(PConstants.CORNER);
		p.rect(x, y, w, h); //drawing chart area
		
		
		//draw the data bars
		int xLength = (x + w - rightMargin) - (x + leftMargin);
		int numValues = data.getRowCount();
		float barWidth = (float)xLength/numValues;
		int barHeight;
		//calculating zero line for the chart
		int zl = calcZeroLine();
		
		for(int i = 1; i < data.getRowCount() + 1; i++){
						
			if(data.getFloatByRowIndex(i - 1, dataColumn) < 0){
				barHeight = (int) PApplet.map(data.getFloatByRowIndex(i - 1, dataColumn), 0, getMin(dataColumn), 0, h - topMargin - bottomMargin - zl);
				
				if((i - 1) == 1){
					//System.out.println("zl: " + zl);
					//System.out.println("N: " + barHeight + " : " + data.getFloatByRowIndex(i - 1, dataColumn) + " : " + getMin(dataColumn) + " : " + (h - topMargin - bottomMargin -zl));
					System.out.println("draw:");
					System.out.println("x1: " + (x + leftMargin + (Math.round(barWidth * (i - 1)) + interBarMargin)) + " y1: " + (y + zl + topMargin) + " x2: " + (x + leftMargin + Math.round((barWidth * i) - interBarMargin)) + " y2: " + (y + zl + barHeight + topMargin));
					//System.out.println(p.mouseX + " : " + p.mouseY);
				}
				
				drawSingleBar(x + leftMargin + (Math.round(barWidth * (i - 1)) + interBarMargin), (int)(y + topMargin + zl), x + leftMargin + Math.round((barWidth * i) - interBarMargin), (int)(y + zl + topMargin + barHeight));
			}else{
				
				barHeight = (int) PApplet.map(data.getFloatByRowIndex(i - 1, dataColumn), 0, getMax(dataColumn), 0, zl);
				drawSingleBar(x + leftMargin + (Math.round(barWidth * (i - 1)) + interBarMargin), (int)(y + topMargin + zl), x + leftMargin + (Math.round(barWidth * i) - interBarMargin), (int)(y + zl + topMargin - barHeight));
			}
		}
		
		//draw x axis
		p.stroke(axisColor);
		p.strokeWeight(axisWeight);
		p.line(x + leftMargin, y + zl + topMargin, x + w - rightMargin, y + zl + topMargin);
		//draw y axis
		p.line(x + leftMargin, y + h - bottomMargin, x + leftMargin, y + topMargin);
		
	}
	


}
