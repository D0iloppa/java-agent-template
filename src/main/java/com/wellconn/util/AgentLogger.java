/**
 * 
 */
package com.wellconn.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * @author doil
 *
 */
public class AgentLogger {
	
	private static String agentName = "WC-AGENT"; // 기본값
	
	public enum LogLevel {
	    LOG("🔍"),
	    INFO("✅"),
	    WARN("⚠️"),
	    ERROR("🛑");

	    private final String symbol;

	    LogLevel(String symbol) {
	        this.symbol = symbol;
	    }

	    public String getSymbol() {
	        return symbol;
	    }
	}


    public static void init(String name) {
        agentName = name;
    }
	
	
	/**
	 * 1. 메소드명 : log
	 * 2. 작성일: 2025. 3. 26.
	 * 3. 작성자: doil
	 * 4. 설명: 
	 * 5. 수정일: doil
	 */
    public static void log(String logMsg) {
    	AgentLogger.log(LogLevel.LOG, logMsg);
    }
	
    /**
     * 
     * 1. 메소드명 : log
     * 2. 작성일: 2025. 3. 26.
     * 3. 작성자: doil
     * 4. 설명: 
     * 5. 수정일: doil
     */
    public static void log(LogLevel logLevel, String logMsg) {
    	
       // 현재 날짜 및 시간 가져오기
       LocalDateTime now = LocalDateTime.now();

       // 포맷 설정 (yyyy-MM-dd HH:mm:ss)
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
       String formattedDateTime = now.format(formatter);
       
       String levelName = logLevel.name();  // or logLevel.toString()
       String symbol = logLevel.getSymbol();

       String finalLog = String.format("[%s:%s] %s %s | at %s", agentName, levelName, symbol, logMsg, formattedDateTime);

       
       if (logLevel == LogLevel.ERROR) {
    	    System.err.println(finalLog);
    	} else {
    	    System.out.println(finalLog);
    	}
       
       
   }


	/**
	 * 1. 메소드명 : getStackTraceAsString
	 * 2. 작성일: 2025. 3. 26.
	 * 3. 작성자: doil
	 * 4. 설명: 
	 * 5. 수정일: doil
	 */
	public static String getStackTraceAsString(Throwable t) {
		StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return sw.toString();
	}


	/**
	 * 1. 메소드명 : error
	 * 2. 작성일: 2025. 3. 26.
	 * 3. 작성자: doil
	 * 4. 설명: 
	 * 5. 수정일: doil
	 */
	public static void error(String msg, Throwable e) {
		// TODO Auto-generated method stub
		
		String stackTrace = AgentLogger.getStackTraceAsString(e);
		
		AgentLogger.log(AgentLogger.LogLevel.ERROR, String.format("%s:\n%s\n", msg, stackTrace));
			
	}
}
