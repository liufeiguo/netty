/** 
 * Project Name:netty 
 * File Name:ForkJoinCalulate.java 
 * Package Name:java8 
 * Date:2019年1月3日下午1:41:58 
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package java8;

import java.util.concurrent.RecursiveTask;

/** 
 * ClassName:ForkJoinCalulate <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2019年1月3日 下午1:41:58 <br/> 
 * @author   liu-guofei 
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public class ForkJoinCalulate extends RecursiveTask<Long>{

	


    
	/** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.8 
	 */  
	private static final long serialVersionUID = 1L;

	private long start;
	
	private long end;
	
	private static final long THRESHOLD = 10000;
	
	public ForkJoinCalulate(long start,long end) {
		 this.start = start;
		 this.end = end;
	}
	
	@Override
	protected Long compute() {
		
		long length = end -start;
		
		if (length <= THRESHOLD) {
			
			long sum =0;
			
			for (long i = start; i <= end; i++) {
	        
				sum+=i;
				
			}
			return sum;
		}else {
			long middle = (start+end)/2;
			ForkJoinCalulate left = new ForkJoinCalulate(start, middle);
			left.fork();//拆分子任务
			ForkJoinCalulate right = new ForkJoinCalulate(middle+1, end);
			right.fork();
			
			return left.join() +right.join();
		}
	
	}

	

}
 