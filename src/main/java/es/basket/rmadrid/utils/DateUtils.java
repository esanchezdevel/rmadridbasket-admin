package es.basket.rmadrid.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

	public static Date createDate(String date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			System.out.println("Error parsing date: " + e.getMessage());
			return new Date();
		}
	}
}
