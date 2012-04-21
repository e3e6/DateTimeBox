package rsrc;

import java.awt.Color;
import java.text.SimpleDateFormat;

public class Constant {

	public static final Color DEFAULT_BACKGROUND = new Color(241, 245, 251);
	public static final Color LITE_BLUE_COLOR = new Color(189, 208, 236);
	
	//public static final String DEFAULT_DATE_FORMAT_STRING = "dd.MM.yyyy HH:mm:ss";

	
	public static final String DATE_FORMAT = "dd.MM.yyyy HH:mm:ss";
	public static final String DATE_MASK = " ##.##.#### ##:##:## ";
	
	
	public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT);
	
	public static final int COLOR_WRONG = 0xFFCCCC;
	public static final int COLOR_VALID = 0xFFFFCC;
}
