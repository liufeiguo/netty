/** 
 * Project Name:netty 
 * File Name:TestScheduledThreadPool.java 
 * Package Name:java8.juc 
 * Date:2019年1月8日上午10:32:34 
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package java8.juc;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/** 
 * ClassName:TestScheduledThreadPool <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2019年1月8日 上午10:32:34 <br/> 
 * @author   liu-guofei 
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public class TestScheduledThreadPool {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);
		
		Future<Integer> schedule = pool.schedule(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				int num = new Random().nextInt(100);
				System.out.println(Thread.currentThread().getName() + " : " + num);
				return num;
			}
		},3,TimeUnit.SECONDS);
		System.out.println(schedule.get());
		
		pool.shutdown();
		System.exit(0);
		
	}
}
 