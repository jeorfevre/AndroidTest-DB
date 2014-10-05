package com.rizze.vcfiscal.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Tools class with miscs & nuts
 * @author Jean-Emmanuel / JE@RIZZE.COM
 *
 */
public class Tools {

	protected static SimpleDateFormat formatterGMT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		
	/**
	 * get a formatted date string GMT
	 * Format:yyyy-MM-dd'T'HH:mm:ss.SSS'Z' exemple 2013-11-30T12:45:56.001Z
	 * @param date Date object
	 * @return error: null, ok :formated string of date
	 */
	public static String getDateAsString(Date date){
		String s=null;
		try {
			if(date==null){return null;}
			long time=date.getTime();
			s = formatterGMT.format(new Date(time));				
		} catch (Exception e) {
				
		}
		return s;
	}
	
	/**
	 * get string representation of now date GMT
	 * Format:yyyy-MM-dd'T'HH:mm:ss.SSS'Z' exemple 2013-11-30T12:45:56.001Z
	 * @return
	 */
	public static String getDateAsString(){		
		return getDateAsString(new Date());
	}
	
	public static String getUUID(){
		return String.valueOf(UUID.randomUUID()).replaceAll("-", "").toUpperCase();
	}
	
}
