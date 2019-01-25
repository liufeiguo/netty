/** 
 * Project Name:netty 
 * File Name:TestSimpleDateFormat.java 
 * Package Name:java8.time 
 * Date:2019年1月4日上午10:08:29 
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package java8.time;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;



/** 
 * ClassName:TestSimpleDateFormat <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2019年1月4日 上午10:08:29 <br/> 
 * @author   liu-guofei 
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public class TestSimpleDateFormat {
     
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		/*
		 * SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		 * 
		 * 
		 * Callable<Date> task = new Callable<Date>() {
		 * 
		 * @Override public Date call() throws Exception {
		 * 
		 * return sdf.parse("20180104"); } }; ExecutorService pool =
		 * Executors.newFixedThreadPool(10);
		 * 
		 * List<Future<Date>> results = new ArrayList<>(); for (int i = 0; i < 10; i++)
		 * { results.add(pool.submit(task)); }
		 * 
		 * for (Future<Date> future : results) { System.out.println(future.get()); }
		 * pool.shutdown();
		 */

		
		
		Callable<Date> task = new Callable<Date>() {

			@Override
			public Date call() throws Exception {
				
				return DateFormatThreadLocal.convert("20180104");
			}
		};
		ExecutorService pool = Executors.newFixedThreadPool(10);
		
		List<Future<Date>> results = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			results.add(pool.submit(task));
		}
		
		for (Future<Date> future : results) {
			System.out.println(future.get());
		}
		pool.shutdown();
	}
}
 