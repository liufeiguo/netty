/** 
  * Project Name:netty 
 * File Name:MyInterface.java 
 * Package Name:java8 
 * Date:2019年1月3日下午4:11:25 
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package java8;  
/** 
 * ClassName:MyInterface <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2019年1月3日 下午4:11:25 <br/> 
 * @author   liu-guofei 
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public interface MyInterface {
   
	default	String getName() {
		return "MyInterface";
	}
	
	public static void show() {
		
		System.out.println("接口中的静态方法");
		
	}
	
}
 