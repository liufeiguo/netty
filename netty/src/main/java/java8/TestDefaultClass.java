/** 
 * Project Name:netty 
 * File Name:TestDefaultClass.java 
 * Package Name:java8 
 * Date:2019年1月3日下午4:09:43 
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package java8;  
/** 
 * ClassName:TestDefaultClass <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2019年1月3日 下午4:09:43 <br/> 
 * @author   liu-guofei 
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public class TestDefaultClass {
   
	public static void main(String[] args) {
		
		//类优先原则
		SubClass sc = new SubClass();
	    System.out.println(sc.getName());
	    
	    MyInterface.show();
		
	}
	
}
 