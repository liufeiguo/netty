/** 
 * Project Name:netty 
 * File Name:TestThreadPool.java 
 * Package Name:java8.juc 
 * Date:2019年1月7日下午5:07:18 
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package java8.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/** 
 * ClassName:TestThreadPool <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2019年1月7日 下午5:07:18 <br/> 
 * @author   liu-guofei 
 * @version   
 * @since    JDK 1.8 
 * @see 
 * ExecutorService newFixedThreadPool()  创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待    
 * ExecutorService newCachedThreadPool()  创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
 * ExecutorService newSingleThreadExecutor()  创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
 * ExecutorService newScheduledThreadPool() 创建一个定长线程池，支持定时及周期性任务执行。
 */ 
public class TestThreadPool {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService pool = Executors.newFixedThreadPool(5);
		
		Future<Integer> task = pool.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				int sum = 0;
				for (int i = 0; i < 100; i++) {
					sum += i;
				}
				return sum;
			}
			
		});
		System.out.println(task.get());
		pool.shutdown();
		/*
		 * TestThreadPoolDemo tt = new TestThreadPoolDemo();
		 * 
		 * //分配任务 pool.submit(tt);
		 * 
		 * //关闭线程 pool.shutdown();
		 */
		
		
		
		
	}
}
class TestThreadPoolDemo implements Runnable{

	private int i = 0;
	@Override
	public void run() {
		while (i <=100) {
			System.out.println(Thread.currentThread().getName() + ":" +i++);
		}
	}
	
} 
