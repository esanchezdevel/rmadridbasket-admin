package es.basket.rmadrid.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
	
	public static Date createGameDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"); 
		LocalDateTime gameTime = LocalDateTime.parse(date, formatter);
		
		return Date.from(gameTime.atZone(ZoneId.systemDefault()).toInstant());
	}
}
