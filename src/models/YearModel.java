package models;

import java.util.Calendar;

public class YearModel {
	
	/**
	 * @param colorPattern
	 * <br>Normal color = 1
	 * <br>Out of range = 0	
	 */
	int rows = 3;
	int columns = 4;
	
	private Boolean[][] colorPattern = new Boolean[rows][columns];
	private Boolean[][] selectedPattern = new Boolean[rows][columns];
	private String[][] yearArray = new String[rows][columns];
	
	public YearModel(Calendar currentDate, int decade){
		
		int currentYear = currentDate.get(Calendar.YEAR);
		
		Calendar year = (Calendar.getInstance());
				 year.set(Calendar.YEAR, decade - 1);
		

		for (int i = 0; i < rows; i++){
			for (int j = 0; j < columns; j ++){
				yearArray[i][j] = ("" + year.get(Calendar.YEAR));
				colorPattern[i][j] = ((year.get(Calendar.YEAR)>=decade) && (year.get(Calendar.YEAR) < decade + 10))? true:false;	
				selectedPattern[i][j] = (currentYear == year.get(Calendar.YEAR))? true:false;
				
				year.add(Calendar.YEAR, 1);
			}
		}
	}

	public Boolean[][] getColorPattern() {
		return colorPattern;
	}

	public void setColorPattern(Boolean[][] colorPattern) {
		this.colorPattern = colorPattern;
	}

	public String[][] getYearArray() {
		return yearArray;
	}

	public void setYearArray(String[][] yearArray) {
		this.yearArray = yearArray;
	}

	public Boolean[][] getSelectedPattern() {
		return selectedPattern;
	}

	public void setSelectedPattern(Boolean[][] selectedPattern) {
		this.selectedPattern = selectedPattern;
	}
}
