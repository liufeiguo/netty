/** 
 * Project Name:netty 
 * File Name:TestForkJoin.java 
 * Package Name:java8 
 * Date:2019年1月3日下午1:56:13 
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package java8;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

import org.junit.Test;

/** 
 * ClassName:TestForkJoin <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2019年1月3日 下午1:56:13 <br/> 
 * @author   liu-guofei 
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public class TestForkJoin {

	@Test
	public void test1() {//545
		Instant now = Instant.now();
		
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		
		ForkJoinTask<Long> task = new ForkJoinCalulate(0, 1000000000L);
		Long invoke = forkJoinPool.invoke(task);
		System.out.println(invoke);
		
		Instant end = Instant.now();
		
		System.out.println(Duration.between(now, end).toMillis());
	}
	
	@Test
	public void test2() {//3736
		Instant now = Instant.now();
		
		Long sum = 0L;
		
		for (long i = 0; i < 1000000000L; i++) {
			sum +=i;
		}
		
		System.out.println(sum);
		
        Instant end = Instant.now();
		
		System.out.println(Duration.between(now, end).toMillis());
	}
	
	@Test
	public void test3() {//442
		Instant now = Instant.now();
		long reduce = LongStream.rangeClosed(0, 1000000000L)
		          .parallel()
		          .reduce(0, Long::sum);
		System.out.println(reduce);
		
        Instant end = Instant.now();
		
		System.out.println(Duration.between(now, end).toMillis());
	}
}
 