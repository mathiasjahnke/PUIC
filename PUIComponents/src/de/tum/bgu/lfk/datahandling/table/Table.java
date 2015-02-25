package de.tum.bgu.lfk.datahandling.table;

import processing.core.PApplet;

//http://www.creativeapplications.net/processing/craft-your-processing-sketches-in-eclipse/
/**
 * a table class to use within processing and processing js.
 * based on the table class from ben fry and ceasy reas out of their book "Visualizing Data" (OReilly)</br></br>
 * to make a processing class JavaScript compatible see at <a href="http://forum.processing.org/one/topic/table-class-in-processingjs.html">how to make the table JavaScript compatible</a>
 * @author Mathias Jahnke, Technische Universit&auml;t M&uuml;nchen, <a href="http://www.lfk.bgu.tum.de">Department of Cartography</a>
 * @version 0.0.2
 * @since 12.06.2014
 */
public class Table {
	
	private String[][] data;
	private int rowCount;
	private int columnCount;
	private PApplet p;

	/**
	 * default constructor.
	 * initialized the Table with 10 rows and 10 columns
	 */
	public Table() {
		data = new String[10][10];
	}

	/**
	 * parameterized constructor. To load the table from data of a tsv (tab seperated file) 
	 * file. TODO extend to csv (comma separated files)
	 * @param filename the filename (absolute, relative or url from whre to load the file
	 */
	public Table(PApplet applet, String filename) {
		
		p = applet;
		String[] rows = p.loadStrings(filename);
		data = new String[rows.length][];

		for (int i = 0; i < rows.length; i++) {
			if (PApplet.trim(rows[i]).length() == 0) {
				continue; // skip empty rows
			}
			if (rows[i].startsWith("#")) {
				continue;  // skip comment lines
			}

			// split the row on the tabs
			// String[] pieces = split(rows[i], TAB);
			String[] pieces = PApplet.split(rows[i], "\t"); // => use with JavaScript mode
			// copy to the table array
			data[rowCount] = pieces;
			rowCount++;

			// this could be done in one fell swoop via:
			//data[rowCount++] = split(rows[i], TAB);
		}
		// resize the 'data' array as necessary
		data = (String[][]) PApplet.subset(data, 0, rowCount);
		columnCount = data[0].length;
	}

	/**
	 * returns the number of rows of the table
	 * @return the number of rows  
	 */
	public int getRowCount() {
		return rowCount;
	}

	/**
	 * returns the number of columns of this table
	 * @return the number of columns
	 */
	public int getColumnCount(){
		return columnCount;
	}

	/**
	 * find a row by its name, returns -1 if no row found.
	 * the name or id has to be in column 0. 
	 * @param name the name of the row at column 1 (index 0)
	 * @return the index of the row
	 */
	public int getRowIndex(String name) {
		for (int i = 0; i < rowCount; i++) {
			if (data[i][0].equals(name)) {
				return i;
			}
		}
		System.out.println("No row named '" + name + "' was found");
		return -1;
	}

	/**
	 * returns the name of the row from column 0  
	 * @param row the row number to query from
	 * @return the name of the row as String
	 */
	public String getRowName(int row) {
		return getStringByRowIndex(row, 0);
	}

	/**
	 * returns a data element as a string, by using row and column indices
	 * @param rowIndex the index of the row 
	 * @param column the number of the column
	 * @return returns the String of the data element
	 */
	public String getStringByRowIndex(int rowIndex, int column) {
		return data[rowIndex][column];
	}

	/**
	 * returns a data element as a String by its row name and the column index.
	 * the rowName has to be in the first column (index 0).
	 * @param rowName the nam of the row at the first column (index 0)
	 * @param column the column index 
	 * @return the string of the data element
	 */
	public String getStringByRowName(String rowName, int column) {
		return getStringByRowIndex(getRowIndex(rowName), column);
	}

	/**
	 * returns a data element as an int by its row name and the column index.
	 * the rowName has to be in the first column (index 0).
	 * @param rowName the nam of the row at the first column (index 0)
	 * @param column the column index 
	 * @return returns the int of the data element
	 */
	public int getIntByRowName(String rowName, int column) {
		return PApplet.parseInt(getStringByRowName(rowName, column));
	}

	/**
	 * returns a data element as an int, by using row and column indices
	 * @param rowIndex the index of the row 
	 * @param column the number of the column
	 * @return returns the int of the data element
	 */
	public int getIntByRowIndex(int rowIndex, int column) {
		return PApplet.parseInt(getStringByRowIndex(rowIndex, column));
	}

	/**
	 * returns a data element as a float by its row name and the column index.
	 * the rowName has to be in the first column (index 0).
	 * @param rowName the nam of the row at the first column (index 0)
	 * @param column the column index 
	 * @return returns the float of the data element
	 */
	public float getFloatByRowName(String rowName, int column) {
		return PApplet.parseFloat(getStringByRowName(rowName, column));
	}

	/**
	 * returns a data element as a float, by using row and column indices
	 * @param rowIndex the index of the row 
	 * @param column the number of the column
	 * @return returns the float of the data element
	 */
	public float getFloatByRowIndex(int rowIndex, int column) {
		return PApplet.parseFloat(getStringByRowIndex(rowIndex, column));
	}

	/**
	 * sets the row name at the first column
	 * @param row the row index
	 * @param what the String to set
	 */
	public void setRowName(int row, String what) {
		data[row][0] = what;
	}

	/**
	 * sets the value for the table element on a specific location
	 * @param rowIndex the index of the row
	 * @param column the column at which to set the value
	 * @param what the string to set
	 */
	public void setStringByRowIndex(int rowIndex, int column, String what) {
		data[rowIndex][column] = what;
	}

	/**
	 * sets the value for the table element on a specific location
	 * @param rowName the the name of the row at which to set the value
	 * @param column the column at which to set the value
	 * @param what the string to set
	 */
	public void setStringByRowName(String rowName, int column, String what) {
		int rowIndex = getRowIndex(rowName);
		data[rowIndex][column] = what;
	}

	/**
	 * sets the value for the table element on a specific location
	 * @param rowIndex the index of the row
	 * @param column the column at which to set the value
	 * @param what the int to set
	 */
	public void setIntByRowIndex(int rowIndex, int column, int what) {
		data[rowIndex][column] = PApplet.str(what);
	}

	/**
	 * sets the value for the table element on a specific location
	 * @param rowName the the name of the row at which to set the value
	 * @param column the column at which to set the value
	 * @param what the int to set
	 */
	public void setIntByRowName(String rowName, int column, int what) {
		int rowIndex = getRowIndex(rowName);
		data[rowIndex][column] = PApplet.str(what);
	}

	/**
	 * sets the value for the table element on a specific location
	 * @param rowIndex the index of the row
	 * @param column the column at which to set the value
	 * @param what the float to set
	 */
	public void setFloatByRowIndex(int rowIndex, int column, float what) {
		data[rowIndex][column] = PApplet.str(what);
	}

	/**
	 * sets the value for the table element on a specific location
	 * @param rowName the the name of the row at which to set the value
	 * @param column the column at which to set the value
	 * @param what the float to set
	 */
	public void setFloatByRowName(String rowName, int column, float what) {
		int rowIndex = getRowIndex(rowName);
		data[rowIndex][column] = PApplet.str(what);
	}

	/*
	  // Write this table as a TSV file
	  // can be used if PrintWriter is imüplemented in processing js
	  void write(PrintWriter writer) {
	    for (int i = 0; i < rowCount; i++) {
	      for (int j = 0; j < data[i].length; j++) {
	        if (j != 0) {
	          writer.print("\t"); // => use in JavaScript Mode
	          //writer.print(TAB);
	        }
	        if (data[i][j] != null) {
	          writer.print(data[i][j]);
	        }
	      }
	      writer.println();
	    }
	    writer.flush();
	  }
	 */

}
