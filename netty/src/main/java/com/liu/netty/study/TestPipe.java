/** 
  * Project Name:netty 
 * File Name:TestPipe.java 
 * Package Name:com.liu.netty.study 
 * Date:2018年12月29日下午2:35:56 
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.liu.netty.study;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

import org.junit.Test;

/** 
 * ClassName:TestPipe <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2018年12月29日 下午2:35:56 <br/> 
 * @author   liu-guofei 
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public class TestPipe {
      
	@Test
	public void test1() throws IOException {
		//获取管道
		Pipe pipe = Pipe.open();
		
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		//将缓冲区中的数据写入通道
		Pipe.SinkChannel sinkChannel = pipe.sink();
		
		buffer.put("通过单向管道发送数据".getBytes());
		
		buffer.flip();
		sinkChannel.write(buffer);
		
		//读取缓冲区中的数据
		Pipe.SourceChannel sourceChannel = pipe.source();
		buffer.flip();
	    int read = sourceChannel.read(buffer);
	    System.out.println(new String(buffer.array(),0,read));
	    sourceChannel.close();
	    sinkChannel.close();
	}
}
 