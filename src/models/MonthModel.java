package models;

import java.util.Calendar;

public class MonthModel {
	
	/**
	 * @param colorPattern
	 * <br>Normal color = 1
	 * <br>Out of range = 0	
	 */
	private static String[][] MONTH_SHORT = {{"янв","фев","мар","апр"},
											 {"май","июн","июл","авг"},
											 {"сен","окт","ноя","дек"}};
	
	private int rows = 3;
	private int columns = 4;
	
	
	private Boolean[][] colorPattern = new Boolean[rows][columns];
	private Boolean[][] selectedPattern = new Boolean[rows][columns];
	private String[][] monthArray = new String[rows][columns];
	
	public MonthModel(Calendar currentDate){
		int monthNow = currentDate.get(Calendar.MONTH);

		for (int i = 0; i < rows; i++){
			for (int j = 0; j < columns; j ++){
				monthArray[i][j] = MONTH_SHORT[i][j];
				colorPattern[i][j] = true;	
				selectedPattern[i][j] = (monthNow == (i*columns + j))? true:false;
			}
		}
	}

	public Boolean[][] getColorPattern() {
		return colorPattern;
	}

	public void setColorPattern(Boolean[][] colorPattern) {
		this.colorPattern = colorPattern;
	}

	public String[][] getMonthArray() {
		return monthArray;
	}

	public void setDayArray(String[][] monthArray) {
		this.monthArray = monthArray;
	}

	public Boolean[][] getSelectedPattern() {
		return selectedPattern;
	}

	public void setSelectedPattern(Boolean[][] selectedPattern) {
		this.selectedPattern = selectedPattern;
	}
}
