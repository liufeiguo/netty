/** 
 * Project Name:netty 
 * File Name:SubClass.java 
 * Package Name:java8 
 * Date:2019年1月3日下午4:08:47 
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package java8;  
/** 
 * ClassName:SubClass <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2019年1月3日 下午4:08:47 <br/> 
 * @author   liu-guofei 
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public class SubClass /* extends TestMyClass */ implements MyFunction,MyInterface {

	@Override
	public String getName() {
		
		return MyFunction.super.getName();
	}

}
 