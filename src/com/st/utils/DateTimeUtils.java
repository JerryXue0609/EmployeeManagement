package com.st.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DateTimeUtils {
	public static String getCurrentDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(0);
		return sdf.format(date);
		
	}

}
