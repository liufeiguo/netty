/** 
 * Project Name:netty 
 * File Name:TestThread8Monitor.java 
 * Package Name:java8.juc 
 * Date:2019年1月7日下午4:34:23 
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package java8.juc;  
/** 
 * ClassName:TestThread8Monitor <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2019年1月7日 下午4:34:23 <br/> 
 * @author   liu-guofei 
 * @version   
 * @since    JDK 1.8 
 * @see  
 * 
 *      
 */
public class TestThread8Monitor {

	
	public static void main(String[] args) {
		Number number = new Number();
		//Number number2 = new Number();
		
		new Thread(() -> number.getOne()).start();
		
		//new Thread(() -> number.getTwo()).start();
		new Thread(() -> number.getTwo()).start();
		
		
		//new Thread(() -> number.getThree()).start();
	}
	
	
	
}
class Number{
	static synchronized void getOne() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("one");

	}
	 static synchronized void getTwo() {
		System.out.println("two");

	}
	 
	     void getThree() {
			System.out.println("three");

		}
}
 