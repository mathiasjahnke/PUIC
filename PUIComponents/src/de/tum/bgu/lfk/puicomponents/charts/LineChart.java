package src.de.tum.bgu.lfk.puicomponents.charts;

import processing.core.PApplet;
import processing.core.PConstants;
import src.de.tum.bgu.lfk.datahandling.table.Table;

/**
 * a line chart in processing. 
 * only one line per chart 
 * TODO should be independent from ...datahandling.table should work with processing table
 * @author Mathias Jahnke, Technische Universit&auml;t M&uuml;nchen, <a href="http://www.lfk.bgu.tum.de">Department of Cartography</a>
 * @version 0.0.1
 * @since 17.06.2014
 */
public class LineChart {
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

	//Styling the data and line
	private int dataColor; //the fill color of the bar
	private boolean dataOutline; // should every bar have an outline
	private int dataOutlineColor; //the outline color of the bars
	private int dataCircleRadius; //the radius for the data dots/circles
	
	private boolean connectData; //connects every dot/circle with a line 
	private int connectDataWeight; //the weight of the connections lines
	private int connectDataColor; //the color of the connection lines

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

	//************* CONSTRUCTORS ************* 
	/**
	 * default constructor
	 */
	public LineChart(){

	}

	/**
	 * parameterized constructor to have a bar chart covering the whole canvas.
	 * @param p the PApplet to draw on
	 */
	public LineChart(PApplet p){
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
		this.dataCircleRadius = 7;
		
		this.connectData = false; 
		this.connectDataWeight = 1; 
		this.connectDataColor = p.color(0,0,0); 

		this.highlightDataColor = p.color(105,8,90);
		this.highlightOutline = false;
		this.highlightFill = true;

		this.axisColor = p.color(227,114,34);
		this.axisWeight = 1;

		this.topMargin = 3;
		this.bottomMargin = 3;
		this.leftMargin = 3;
		this.rightMargin = 3;

	}

	/**
	 * parameterized constructor
	 * @param p the PApplet to draw on
	 * @param x coordinate of the upper left corner of the chart
	 * @param y coordinate of the upper left corner of the chart
	 * @param w width of the chart in pixel
	 * @param h height of the chart in pixel
	 */
	public LineChart(PApplet p, int x, int y, int w, int h){
		this.p = p;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;

		this.chartColor = p.color(218,215,203);
		this.chartOutline = false;
		this.chartOutlineColor = p.color(218,215,203);

		this.dataColor = p.color(162,173,0);
		this.dataOutline = true;
		this.dataOutlineColor = p.color(162,173,0);
		this.dataCircleRadius = 7;
		
		this.connectData = false; 
		this.connectDataWeight = 1; 
		this.connectDataColor = p.color(0,0,0); 

		this.highlightDataColor = p.color(105,8,90);
		this.highlightOutline = false;
		this.highlightFill = true;

		this.axisColor = p.color(227,114,34);
		this.axisWeight = 1;

		this.topMargin = 3;
		this.bottomMargin = 3;
		this.leftMargin = 3;
		this.rightMargin = 3;

	}


	//************* GETTER & SETTER *************


	/**
	 * to set the color for the axis of the line chart
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
	
	/**
	 * draws a circle
	 * @param x
	 * @param y
	 * @param radius
	 */
	private void drawCircle(int x, int y, int radius){

		p.ellipseMode(PConstants.CENTER);
		p.fill(dataColor);
		if(dataOutline){
			p.stroke(dataOutlineColor);
			p.strokeWeight(1);
		}else{
			p.noStroke();
		}

		p.ellipse(x, y, radius, radius);
	}

	/**
	 * highlights a circle
	 * @param x
	 * @param y
	 * @param radius
	 */
	private void highlightCircle(int x, int y, int radius){
		p.ellipseMode(PConstants.CENTER);
		if(highlightOutline){
			p.stroke(highlightDataColor);
		}else{
			p.noStroke();
		}
		
		if(highlightFill){
			p.fill(highlightDataColor);
		}else {
			p.noFill();
		}
		p.strokeWeight(this.highlightStrokeWeight);
		p.ellipse(x, y, radius, radius);;
	}
	
	/**
	 * connect each data point with a line
	 */
	private void connectDataPoints(){
		
		int xLength = (x + w - rightMargin) - (x + leftMargin);
		int numValues = data.getRowCount();
		float barWidth = (float)xLength/numValues;
		int barHeight;
		//calculating zero line for the chart
		int zl = calcZeroLine();
		
		int x1, y1, x2, y2;
		
		for(int i = 0; i < data.getRowCount() - 1; i++){

			if(data.getFloatByRowIndex(i, dataColumn) < 0){
				barHeight = (int) PApplet.map(data.getFloatByRowIndex(i, dataColumn), 0, getMin(dataColumn), 0, h - topMargin - bottomMargin - zl);
				//drawCircle((int)(x + leftMargin + barWidth/2 + (Math.round(barWidth * i))), (int)(y + zl + topMargin + barHeight), dataCircleRadius);
				x1 = (int)(x + leftMargin + barWidth/2 + (Math.round(barWidth * i)));
				y1 = (int)(y + zl + topMargin + barHeight);

			}else{
				barHeight = (int) PApplet.map(data.getFloatByRowIndex(i, dataColumn), 0, getMax(dataColumn), 0, zl);
				//drawCircle((int)(x + leftMargin + barWidth/2 + (Math.round(barWidth * i))), (int)(y + zl + topMargin - barHeight), dataCircleRadius);
				x1 = (int)(x + leftMargin + barWidth/2 + (Math.round(barWidth * i)));
				y1 = (int)(y + zl + topMargin - barHeight);
			}
			
			if(data.getFloatByRowIndex(i + 1, dataColumn) < 0){
				barHeight = (int) PApplet.map(data.getFloatByRowIndex(i + 1, dataColumn), 0, getMin(dataColumn), 0, h - topMargin - bottomMargin - zl);
				//drawCircle((int)(x + leftMargin + barWidth/2 + (Math.round(barWidth * i))), (int)(y + zl + topMargin + barHeight), dataCircleRadius);
				x2 = (int)(x + leftMargin + barWidth/2 + (Math.round(barWidth * (i + 1))));
				y2 = (int)(y + zl + topMargin + barHeight);

			}else{
				barHeight = (int) PApplet.map(data.getFloatByRowIndex(i + 1, dataColumn), 0, getMax(dataColumn), 0, zl);
				//drawCircle((int)(x + leftMargin + barWidth/2 + (Math.round(barWidth * i))), (int)(y + zl + topMargin - barHeight), dataCircleRadius);
				x2 = (int)(x + leftMargin + barWidth/2 + (Math.round(barWidth * (i + 1))));
				y2 = (int)(y + zl + topMargin - barHeight);
			}
			
			p.strokeCap(PConstants.ROUND);
			p.strokeJoin(PConstants.MITER);
			p.strokeWeight(connectDataWeight);
			p.stroke(connectDataColor);
			//System.out.println("x1=" + x1 + " : " + "y1=" + y1 + " : " + "x2=" + x2 + " : " + "y2=" + y2 + " : ");
			p.line(x1, y1, x2, y2);
		}
		
	}

	//************* PUBLIC METHODS *************

	/**
	 * sets the data to draw the line chart
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
	 * to set the styling for the background of the chart
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
	 * to set the styling for the dots/circles representing the data
	 * standard values are set with the paraemterized constructor
	 * @param dataColor background color of the dots/circles
	 * @param dataOutline if every data dot/circle should have an outline
	 * @param dataOutlineColor the color for the outline
	 */
	public void setDataStyling(int dataColor, boolean dataOutline, int dataOutlineColor, int dataCircleRadius){
		this.dataColor = dataColor;
		this.dataOutline = dataOutline;
		this.dataOutlineColor = dataOutlineColor;
		this.dataCircleRadius = dataCircleRadius;
	}

	/**
	 * sets the margins (spacing) between the data and the border of the chart.
	 * top, bottom, left and right margins can be set
	 * if topMargin + bottomMargin exceeds h or
	 * if leftMargin + rightMarging exceeds w 
	 * standard values which are set by the parameterized constructors are used.
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
	 * to set the axis Styling 
	 * color and weight
	 * @param color the axis color
	 * @param weight the stroke width for the axis
	 */
	public void setAxisStyling(int color, int weight){
		this.axisColor = color;
		this.axisWeight = weight;
	}

	/**
	 * sets the highlight styling for the dots/circles.
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
	 * sets if the data points should be connected with a line 
	 * standard is no connecting lines
	 * @param connect true if the data points should be connected
	 * @param weight the strokeWeight of the connecting lines
	 * @param color the color of the connecting lines
	 */
	public void setConnectDataPoints(boolean connect, int weight, int color){
		this.connectData = connect;
		this.connectDataWeight = weight;
		this.connectDataColor = color;
		
	}

	/**
	 * checks if the mouse is located in one of the dots/circles. 
	 * if the mouse is located inside a dot/circle the according data id is returned
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
		
		for(int i = 0; i < data.getRowCount(); i++){
			//check if bar pos or neg
			if(data.getFloatByRowIndex(i, dataColumn) < 0){
				//check y for neg
				barHeight = (int) PApplet.map(data.getFloatByRowIndex(i, dataColumn), 0, getMin(dataColumn), 0, h - topMargin - bottomMargin - zl);

				float d = PApplet.dist((x + leftMargin + barWidth/2 + (Math.round(barWidth * i))), (y + zl + topMargin + barHeight), mx, my);

				if(d <= dataCircleRadius){
					res = data.getStringByRowIndex(i, idColumn);
				}
			}else{
				//check y for pos
				barHeight = (int) PApplet.map(data.getFloatByRowIndex(i, dataColumn), 0, getMax(dataColumn), 0, zl);

				float d = PApplet.dist((x + leftMargin + barWidth/2 + (Math.round(barWidth * i))), (y + zl + topMargin - barHeight), mx, my);

				if(d <= dataCircleRadius){
					res = data.getStringByRowIndex(i, idColumn);
				}
			}
		}
		return res;
	}

	/**
	 * highlights the dot/circle according the the id
	 * @param id the id to bar which should be highlighted
	 */
	public void highlightData(String id){

		int xLength = (x + w - rightMargin) - (x + leftMargin);
		int numValues = data.getRowCount();
		float barWidth = (float)xLength/numValues;
		int zl = calcZeroLine();
		int barHeight;

		for(int i = 0; i < data.getRowCount(); i++){
			if(data.getStringByRowIndex(i, idColumn) == id){
				if(data.getFloatByRowIndex(i, dataColumn) < 0){
					barHeight = (int) PApplet.map(data.getFloatByRowIndex(i, dataColumn), 0, getMin(dataColumn), 0, h - topMargin - bottomMargin - zl);
					
					highlightCircle((int)(x + leftMargin + barWidth/2 + (Math.round(barWidth * i))), (int)(y + zl + topMargin + barHeight), dataCircleRadius);
				}else{
					barHeight = (int) PApplet.map(data.getFloatByRowIndex(i, dataColumn), 0, getMax(dataColumn), 0, zl);
					
					highlightCircle((int)(x + leftMargin + barWidth/2 + (Math.round(barWidth * i))), (int)(y + zl + topMargin - barHeight), dataCircleRadius);
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
	 * via the different set methods otherwise standard values are used
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
		
		//connecting data points
		if(connectData){
			//System.out.println("connect");
			connectDataPoints();
		}

		//draw the data bars
		int xLength = (x + w - rightMargin) - (x + leftMargin);
		int numValues = data.getRowCount();
		float barWidth = (float)xLength/numValues;
		int barHeight;
		//calculating zero line for the chart
		int zl = calcZeroLine();

		for(int i = 0; i < data.getRowCount(); i++){

			if(data.getFloatByRowIndex(i, dataColumn) < 0){
				barHeight = (int) PApplet.map(data.getFloatByRowIndex(i, dataColumn), 0, getMin(dataColumn), 0, h - topMargin - bottomMargin - zl);

				drawCircle((int)(x + leftMargin + barWidth/2 + (Math.round(barWidth * i))), (int)(y + zl + topMargin + barHeight), dataCircleRadius);

			}else{
				barHeight = (int) PApplet.map(data.getFloatByRowIndex(i, dataColumn), 0, getMax(dataColumn), 0, zl);
				
				drawCircle((int)(x + leftMargin + barWidth/2 + (Math.round(barWidth * i))), (int)(y + zl + topMargin - barHeight), dataCircleRadius);
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
