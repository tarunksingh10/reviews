package com.reviews.app.controllers;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateMain {

	public static Date getDate(String dateString) {
		// TODO Auto-generated method stub

		String format = "yyyy-MM-dd HH:mm:ss";

		SimpleDateFormat df = new SimpleDateFormat(format);

		// String dates="2014-01-01";
		StringBuilder str = new StringBuilder(dateString);
		str.append(" 00:00:00");

		Date from = null;
		try {
			from = df.parse(str.toString());

			// Date to = df.parse("2016-02-01");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return from;
	}

	
}
