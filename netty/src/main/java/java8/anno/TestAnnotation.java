/** 
 * Project Name:netty 
 * File Name:TestAnnotation.java 
 * Package Name:java8.anno 
 * Date:2019年1月4日下午3:37:52 
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package java8.anno;

import java.lang.reflect.Method;

import org.junit.Test;

/** 
 * ClassName:TestAnnotation <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2019年1月4日 下午3:37:52 <br/> 
 * @author   liu-guofei 
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public class TestAnnotation {
     
	@MyAnnotation("word")
	@MyAnnotation("hello")
	@MyAnnotation("hello")
	public void show() {
		
	}
	
	@Test
    public void test1() throws NoSuchMethodException, SecurityException {
		Class<TestAnnotation> clazz = TestAnnotation.class;
		Method method = clazz.getMethod("show");
		System.out.println(method);
		MyAnnotation[] annotationsByType = method.getAnnotationsByType(MyAnnotation.class);
		System.out.println(annotationsByType.length);
		for (MyAnnotation myAnnotation:annotationsByType) {
			System.out.println(myAnnotation.value());
		}
	}
	
}
 