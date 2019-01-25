/** 
 * Project Name:netty 
 * File Name:TestProductAndConsumer.java 
 * Package Name:java8.juc 
 * Date:2019年1月7日下午2:31:41 
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package java8.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** 
 * ClassName:TestProductAndConsumer <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2019年1月7日 下午2:31:41 <br/> 
 * @author   liu-guofei 
 * @version   
 * @since    JDK 1.8 
 * @see 
 * 生产者和消费者案例
 *       
 */
public class TestProductAndConsumerForLock {

	public static void main(String[] args) {
		Clerk clerk = new Clerk();
		
		Productor productor = new Productor(clerk);
		
		Consumer consumer = new Consumer(clerk);
		
		new Thread(productor,"生产者A").start();
		new Thread(consumer,"消费者A").start();
		
		new Thread(productor,"生产者B").start();
		new Thread(consumer,"消费者B").start();
	}
	
}
class Clerk{
	private int product = 0;
	
	private Lock lock =  new ReentrantLock();
	private Condition condition = lock.newCondition();
	//进货
	public  void get() {
		lock.lock();
		try {
			while (product >= 1) {
				System.out.println("产品已满");
				try {
					condition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
				
				System.out.println(Thread.currentThread().getName()+":"+ ++product);
				condition.signalAll(); 
		} finally {
			lock.unlock();
		}
		
		
	}
	
	//卖货
	public  void sale() {
		lock.lock();
		try {
			while (product <= 0) {
				System.out.println("缺货");
				
				try {
					condition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
				System.out.println(Thread.currentThread().getName()+":"+--product);
				condition.signalAll(); 
		} finally {
			lock.unlock();
		}
		
	}
	
}

class Productor implements Runnable{
	private Clerk clerk;
	
	 public Productor(Clerk clerk) {
	       this.clerk = clerk;
	}

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			//System.out.println(i);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			clerk.get();
		}
		
	}
}
class Consumer implements Runnable{
	private Clerk clerk;
	
	 public Consumer(Clerk clerk) {
	       this.clerk = clerk;
	}

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			clerk.sale();
		}
		
	}
}
 