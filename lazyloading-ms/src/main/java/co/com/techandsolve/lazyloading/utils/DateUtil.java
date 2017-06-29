package co.com.techandsolve.lazyloading.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public final static String FORMATO_UNO = "yyyy/MM/dd";
	
	public static String dateToString(Date date, String format){
		String dateString = "";
		if(date != null){
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			dateString = dateFormat.format(date);
		}
		return dateString;
	}
	
}
