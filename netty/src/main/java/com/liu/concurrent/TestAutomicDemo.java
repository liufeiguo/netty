/** 
 * Project Name:netty 
 * File Name:TestAutomicDemo.java 
 * Package Name:com.liu.concurrent 
 * Date:2019年1月5日下午2:08:15 
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.liu.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/** 
 * ClassName:TestAutomicDemo <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2019年1月5日 下午2:08:15 <br/> 
 * @author   liu-guofei 
 * @version   
 * @since    JDK 1.8 
 * @see   
 *     volatile 保证内存可见性
 *      CAS（Copy-And-Swap）算法保证数据的原子性
 *      CAS 是硬件对于并发操作共享数据的支持
 *      内存值 V
 *      预估值 A
 *      更新值B
 */
public class TestAutomicDemo {
 
	public static void main(String[] args) {
		AtomicDemo ad = new AtomicDemo();
		
		for (int i = 0; i < 10; i++) {
			new Thread(ad).start();
		}	
	}
}


class AtomicDemo implements Runnable{

	private  AtomicInteger serailNumber = new AtomicInteger();
	@Override
	public void run() {
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() +":" +getSerailNumber());
		
	}
	public int getSerailNumber() {
		return serailNumber.getAndIncrement();
	}
	public void setSerailNumber(AtomicInteger serailNumber) {
		this.serailNumber = serailNumber;
	}
	
}
 