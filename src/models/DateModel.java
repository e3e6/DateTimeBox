package models;

import java.util.Calendar;

public class DateModel {
	
	/**
	 * @param colorPattern
	 * <br>Normal color = 1
	 * <br>Out of range = 0	
	 */
	
	final int ROWS = 6;
	final int COLUMNS = 7;
	
	private Boolean[][] colorPattern = new Boolean[ROWS][COLUMNS];
	private Boolean[][] selectedPattern = new Boolean[ROWS][COLUMNS];
	private String[][] dayArray = new String[ROWS][COLUMNS];
	
	public DateModel(Calendar currentDate){
		Calendar date = Calendar.getInstance();
				 date.setTime(currentDate.getTime());
		
		int currentMonth = date.get(Calendar.MONTH);
		final int dayNow = currentDate.get(Calendar.DAY_OF_MONTH);
		final int monthNow = currentDate.get(Calendar.MONTH);

		date.set(Calendar.DATE, 1);
		
		int day_of_week = date.get(Calendar.DAY_OF_WEEK);
		
		date.add(Calendar.DATE, (day_of_week == 1)? -6: (day_of_week - 2)*(-1));
		
		for (int i = 0; i < ROWS; i++){
			for (int j = 0; j < COLUMNS; j ++){
				dayArray[i][j] = ("" + date.get(Calendar.DATE));
				colorPattern[i][j] = (currentMonth == date.get(Calendar.MONTH))? true:false;	
				selectedPattern[i][j] = (monthNow == date.get(Calendar.MONTH))&&(dayNow == date.get(Calendar.DAY_OF_MONTH))? true:false;
				date.add(Calendar.DATE, 1);
			}
		}
	}

	public Boolean[][] getColorPattern() {
		return colorPattern;
	}

	public void setColorPattern(Boolean[][] colorPattern) {
		this.colorPattern = colorPattern;
	}

	public String[][] getDayArray() {
		return dayArray;
	}

	public void setDayArray(String[][] dayArray) {
		this.dayArray = dayArray;
	}

	public Boolean[][] getSelectedPattern() {
		return selectedPattern;
	}

	public void setSelectedPattern(Boolean[][] selectedPattern) {
		this.selectedPattern = selectedPattern;
	}
}
