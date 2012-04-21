package ru.topcode.skillbase.regexp;
 
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class Time24HoursValidator {
 
	private Pattern pattern;
	private Matcher matcher;
 
	private static final String TIME24HOURS_PATTERN = "([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";
 
	public Time24HoursValidator() {
		pattern = Pattern.compile(TIME24HOURS_PATTERN);
	}
 
	/**
	 * Валидация 24-часового формата времени с помощью регулярного выражения
	 * 
	 * @param time
	 *            время
	 * @return true валидный формат, false не валидный формат
	 */
	public boolean validate(final String time) {
		matcher = pattern.matcher(time.trim());
		return matcher.matches();
	}
}