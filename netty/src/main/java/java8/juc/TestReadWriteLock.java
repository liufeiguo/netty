/** 
 * Project Name:netty 
 * File Name:TestReadWriteLock.java 
 * Package Name:java8.juc 
 * Date:2019年1月7日下午3:32:58 
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package java8.juc;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/** 
 * ClassName:TestReadWriteLock <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2019年1月7日 下午3:32:58 <br/> 
 * @author   liu-guofei 
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public class TestReadWriteLock {
  
	public static void main(String[] args) {
		ReadWriteLockDemo rwld = new ReadWriteLockDemo();
		new Thread(() -> rwld.set((int)(Math.random() * 100)),"Write").start();
		 
		for (int i = 0; i < 100; i++) {
			new Thread(() -> rwld.get(),"Read").start();
		}
	}
	
}
class ReadWriteLockDemo{
	
	private int number = 0;
	
	private ReadWriteLock lock =new ReentrantReadWriteLock();
	
	public void get() {
		    lock.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + ":" + number);
		} finally {
			lock.readLock().unlock();
		}
		
	}
	
	public void set(int number) {
		lock.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + ":" + number);
			this.number = number;
		} finally {
			lock.writeLock().unlock();
		}
		
	}
} 