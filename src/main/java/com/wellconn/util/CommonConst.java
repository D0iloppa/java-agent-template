/**
 * 
 */
package com.wellconn.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author doil
 *
 */
public class CommonConst {
	
	public static String currentDateAndTime(){
		LocalDateTime seoulNow = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String currentDateAndTime = seoulNow.format(formatter);
		return currentDateAndTime;
	}
	

	public static String getCurrentDateTime(){
		LocalDateTime seoulNow = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		String currentDateAndTime = seoulNow.format(formatter);
		return currentDateAndTime;
	}
	
	
	
	
}
