package model.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
	
	public static String getFormattedDate(Date date){
		//2014-11-14 22:15:01
	    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
	    Date now = new Date();
	    String strDate = sdfDate.format(now);
	    return strDate;

	}
}
