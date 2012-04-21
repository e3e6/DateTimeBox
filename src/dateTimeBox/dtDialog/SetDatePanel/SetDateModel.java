package dateTimeBox.dtDialog.SetDatePanel;

import java.util.Calendar;

import models.DateModel;
import models.MonthModel;
import models.YearModel;


public class SetDateModel {
	
	private static String[] MONTH_FULL = {"январь","‘евраль","ћарт","јпрель",
										  "ћай","»юнь","»юль","јвгуст",
										  "—ент€брь","ќкт€брь","Ќо€брь","ƒекабрь"};
	
	private static String[][] MONTH_SHORT = {{"€нв","фев","мар","апр"},
											 {"май","июн","июл","авг"},
											 {"сен","окт","но€","дек"}};
	
	private int table_mode;
	
	/**
	 * @param MODE_DAY заголовок - мес€ц год, в таблице - дни
	 */
	public final static int MODE_DAY = 0;
	
	/**
	 * @param MODE_MONTH заголовок - год, в таблице - мес€цы
	 */
	public final static int MODE_MONTH = 1;
	
	/**
	 * @param MODE_YEAR заголовок - диапазон лет, в таблице - года
	 */
	public final static int MODE_YEAR = 2;
	
	private Calendar currentDate;

	
// конструктор класса	
	public SetDateModel(Calendar date) {
		currentDate = date;
		table_mode = 0;
	}
		
	public Calendar getCurrentDate() {
		return currentDate;
	}
	
	public int getCurrentDecade(){
		return (currentDate.get(Calendar.YEAR)/10)*10;
	}

	public void setCurrentDate(Calendar currentDate) {
		this.currentDate = currentDate;
	}

	public void setCurrentFromText(String text){
		
		
		
		switch (table_mode) {
		case 0:
			setDay(Integer.parseInt(text));
			break;
		case 1:
			setMonth(getMonthFromShort(text));
			break;
		case 2:
			setYear(Integer.parseInt(text));
			break;
		default:
			break;
		}
		
	}
	
	public void setDay(int value){
		currentDate.set(Calendar.DATE, value);
	}
	
	public void setMonth(int value){
		currentDate.set(Calendar.MONTH, value);
	}
	
	public void setYear(int value){
		currentDate.set(Calendar.YEAR, value);
	}

	public int getTable_mode() {
		return table_mode;
	}



	public void setTable_mode(int tableMode) {
		table_mode = tableMode;
	}



	public String getMonthFull(int i) {
		return MONTH_FULL[i];
	}

//переключаем режим
	public void switchNextMode(){	
		if (table_mode < 2) table_mode++;
		else table_mode = 0;
	}
	
	public void switchPreviousMode(){		
		if (table_mode > 0) table_mode--;
		else table_mode = 0;
	}
	
	/**
	 * @param up true(month++),  false(month--)
	 * 	   		 
	 */
	public void switchNextCurrentDate(boolean up){
		
		
		switch (table_mode) {
		case 0:
			currentDate.roll(Calendar.MONTH, up);
			break;

		case 1:
			currentDate.roll(Calendar.YEAR, up);
			break;
			
		case 2:
			currentDate.roll(Calendar.YEAR, up?10:-10);
			break;
			
		default:
			break;
		}
	}

	
	private int getMonthFromShort(String text){
		return getIndexFromArray(text, MONTH_SHORT);
	}
	
	private int getIndexFromArray(String text, String[][] array){
		for (int i = 0; i < array.length; i++){
			for (int j = 0; j < array[0].length; j++){
				if (array[i][j].equals(text)) {
					
					return i*array[0].length + j;
				}
			}
		}
		return -1;
	}

//XXX возвращаем текущее состо€ние модели
	
	public String[][] getCurrentModel(){
		
		switch (table_mode) {
		case 0:
			return new DateModel(getCurrentDate()).getDayArray();
		case 1:
			return new MonthModel(getCurrentDate()).getMonthArray();
		case 2:
			return new YearModel(getCurrentDate(), getCurrentDecade()).getYearArray();
		default:
			return null;
		}
	}
	
	public Boolean[][] getCurrentColorPattern() {
		switch (table_mode) {
		case 0:
			return new DateModel(getCurrentDate()).getColorPattern();
		case 1:
			return new MonthModel(getCurrentDate()).getColorPattern();
		case 2:
			return new YearModel(getCurrentDate(), getCurrentDecade()).getColorPattern();
		default:
			return null;
		}
	}

	public Boolean[][] getCurrentSelected() {
		
		switch (table_mode) {
		case 0:
			return new DateModel(getCurrentDate()).getSelectedPattern();
		case 1:
			return new MonthModel(getCurrentDate()).getSelectedPattern();
		case 2:
			return new YearModel(getCurrentDate(), getCurrentDecade()).getSelectedPattern();
		default:
			return null;
		}
	}

}
