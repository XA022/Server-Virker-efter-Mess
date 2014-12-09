package model.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DateHelper {
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static Date getDateFromCBSEvent(ArrayList<String> dateArray) {
		
		return getFormattedDateObject(getFormattedStringFromCBSArray(dateArray));
	}
	
	public static String getFormattedDateStringFromCBSEvent(ArrayList<String> dateArray) {
		return getFormattedDateString(getDateFromCBSEvent(dateArray));
	}
	
	public static String getFormattedDateString(Date date){
		//2014-11-14 22:15:01
	    SimpleDateFormat sdfDate = new SimpleDateFormat(DATE_FORMAT);//dd/MM/yyyy
	    String strDate = sdfDate.format(date);
	    return strDate;

	}
	public static Date getFormattedDateObject(String dateString){
		if(dateString==null || dateString.isEmpty()){
			return null;
		}
		
		DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		
		try {
			return formatter.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	private static String getFormattedStringFromCBSArray(ArrayList<String> dateArray) {
		// Format example: 2014, 10, 28, 9, 50
		String year = dateArray.get(0);
		String month = dateArray.get(1);		
		String day = dateArray.get(2).length()==1 ? "0" + dateArray.get(2) : dateArray.get(2);
		String hour = dateArray.get(3).length()==1 ? "0" + dateArray.get(3) : dateArray.get(3);
		String minute = dateArray.get(4);
		String second = "00";
		String dateString = year + "-" + month + "-" + day +
				" " + hour + ":" + minute + ":" + second;
		System.out.println("dateString: " + dateString);
		return dateString;
	}
	
	public static String getFormat() {
		return DATE_FORMAT;
	}
	
}
