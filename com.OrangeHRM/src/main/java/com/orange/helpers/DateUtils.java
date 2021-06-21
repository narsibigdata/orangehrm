package com.orange.helpers;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DateUtils {
	/*
	 * public static void main(String[] args) { LocalDateTime date =
	 * LocalDateTime.now(); //System.out.println("Start-> "+date); try {
	 * Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
	 * String futureDa=getDaysFromToday(181); System.out.println(futureDa); }
	 */
	private static final int TEN_MINUTES = 10 * 60 * 1000;

	public static int getTodayDate() {
		LocalDateTime date = LocalDateTime.now();

		return Integer.parseInt(String.valueOf(date.getDayOfMonth()));

	}

	public static boolean isTimeExceeded(long startTime) {

		Long currentTime = System.currentTimeMillis();
		System.out.println("start  time -> " + startTime);
		System.out.println("current  time -> " + currentTime);
		System.out.println("difference in  time -> " + (currentTime - startTime));

		if (currentTime - startTime <= TEN_MINUTES) {
			return false;
		}
		return true;
	}

	public static String randomPasswordGenrator() {
		LocalDateTime date = LocalDateTime.now();
		String pass = "Testing@";
		String month = String.valueOf(date.getMonthValue());
		String dateValue = String.valueOf(date.getDayOfMonth());
		String hour = String.valueOf(date.getHour());
		String min = String.valueOf(date.getMinute());
		String sec = String.valueOf(date.getSecond());
		return pass + month + dateValue + hour + min + sec;
	}

	public static String getEmailPerDay(List<String> emailsList) {
		LocalDate currentDate = LocalDate.now();
		String dow = currentDate.getDayOfWeek().toString();
		for (String email : emailsList) {
			if (email.contains(dow)) {
				return email;
			}
		}
		return "no match found";
	}

	public static String getDaysFromToday(int value) {
		LocalDate today = LocalDate.now();
		LocalDate futureDate = today.plusDays(value);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH);
		return futureDate.format(formatter).toString();
	}
	public static String getCurrentDayForMailValidator() {
		
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
		SimpleDateFormat f = new SimpleDateFormat("EEE MMM d ");//HH:mm:ss z yyyy
	    System.out.println(f.format(new Date()));
	    return f.format( new Date() );
	}
}
