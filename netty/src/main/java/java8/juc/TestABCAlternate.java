/** 
 * Project Name:netty 
 * File Name:TestABCAlternate.java 
 * Package Name:java8.juc 
 * Date:2019年1月7日下午3:15:30 
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package java8.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** 
 * ClassName:TestABCAlternate <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2019年1月7日 下午3:15:30 <br/> 
 * @author   liu-guofei 
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public class TestABCAlternate {
   public static void main(String[] args) {
	AlternateDemo ad = new AlternateDemo();
	
	new Thread(new Runnable() {
		
		@Override
		public void run() {
			
			for (int i = 0; i <= 20; i++) {
				ad.loopA(i);
			}
		}
	},"A").start();
new Thread(new Runnable() {
		
		@Override
		public void run() {
			
			for (int i = 0; i <= 20; i++) {
				ad.loopB(i);
			}
		}
	},"B").start();
new Thread(new Runnable() {
	
	@Override
	public void run() {
		
		for (int i = 0; i <= 20; i++) {
			ad.loopC(i);
		}
	}
},"C").start();
}
	
}

class AlternateDemo{
	private int number = 1;
	
	private Lock lock = new ReentrantLock();
	private Condition condition1 = lock.newCondition();
	private Condition condition2 = lock.newCondition();
	private Condition condition3 = lock.newCondition();
	
	/** 
	 * loopA:(这里用一句话描述这个方法的作用). <br/> 
	 * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	 * 
	 * @author gcx 
	 * @param totalLoop  第几轮
	 * @since JDK 1.8 
	 */  
	public void loopA(int totalLoop) {
		lock.lock();
		try {
			if (number != 1) {
				try {
					condition1.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for (int i = 0; i < 1; i++) {
				 System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + totalLoop);
			}
			number = 2;
			condition2.signalAll();
		} finally {
			lock.unlock();
		}
	}
	
	public void loopB(int totalLoop) {
		lock.lock();
		try {
			if (number != 2) {
				try {
					condition2.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for (int i = 0; i < 1; i++) {
				 System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + totalLoop);
			}
			number = 3;
			condition3.signalAll();
		} finally {
			lock.unlock();
		}
	}
	
	public void loopC(int totalLoop) {
		lock.lock();
		try {
			if (number != 3) {
				try {
					condition3.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for (int i = 0; i < 1; i++) {
				 System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + totalLoop);
			}
			number = 1;
			condition1.signalAll();
		} finally {
			lock.unlock();
		}
	}
	
	
}
 