package model.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
	private static final String dateformat = "yyyy-MM-dd HH:mm:ss";
	
	public static String getFormattedDateString(Date date){
		//2014-11-14 22:15:01
	    SimpleDateFormat sdfDate = new SimpleDateFormat(dateformat);//dd/MM/yyyy
	    Date now = new Date();
	    String strDate = sdfDate.format(now);
	    return strDate;

	}
	public static Date getFormattedDateObject(String dateString){
		if(dateString==null || dateString.isEmpty()){
			return null;
		}
		
		DateFormat formatter = new SimpleDateFormat(dateformat);
		
		try {
			return formatter.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	
}
