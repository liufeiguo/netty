/** 
 * Project Name:netty 
 * File Name:DateFormatThreadLocal.java 
 * Package Name:java8.time 
 * Date:2019年1月4日上午10:20:48 
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package java8.time;  
/** 
 * ClassName:DateFormatThreadLocal <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2019年1月4日 上午10:20:48 <br/> 
 * @author   liu-guofei 
 * @version   
 * @since    JDK 1.8 
 * @see       
 */

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatThreadLocal {
  
	private static final ThreadLocal<DateFormat> DATE_TIME_THREAD_LOCAL = new ThreadLocal<DateFormat>() {
		  
		protected DateFormat initialValue() {
	        
			return new SimpleDateFormat("yyyyMMdd");
	    }
	};
	
	
	
	public static Date convert(String source) throws ParseException {
		return DATE_TIME_THREAD_LOCAL.get().parse(source);
	}
	
}
 