/** 
 * Project Name:netty 
 * File Name:TestVolatile.java 
 * Package Name:com.liu.concurrent 
 * Date:2019年1月5日上午11:05:41 
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.liu.concurrent;  
/** 
 * ClassName:TestVolatile <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2019年1月5日 上午11:05:41 <br/> 
 * @author   liu-guofei 
 * @version   
 * @since    JDK 1.8 
 * @see       
 * volatile 内存中的数据是可见的 比synchronized关键
 * 不具备互斥性 不能保存原子性
 */
public class TestVolatile {
     public static void main(String[] args) {
		ThreadDemo td = new ThreadDemo();
		new Thread(td).start();
		while (true) {
			//synchronized (td) {
				//效率低
				if (td.isFlag()) {
					System.out.println("---------");
					break;
				}
			//}
			
		}
		//System.out.println(ThreadDemo.HASH_BITS);
		
	}
     
    
}

class ThreadDemo implements Runnable{
	static final int HASH_BITS = 0x7fffffff; 
	private volatile  boolean flag = false;
	
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	@Override
	public void run() {
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		flag = true;
		System.out.println("flag=" +isFlag());
	}
	
}
 