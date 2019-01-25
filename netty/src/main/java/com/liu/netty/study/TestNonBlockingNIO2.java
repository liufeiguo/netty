/** 
 * Project Name:netty 
 * File Name:TestNonBlockingNIO2.java 
 * Package Name:com.liu.netty.study 
 * Date:2018年12月29日下午2:08:47 
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.liu.netty.study;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import org.junit.Test;

/** 
 * ClassName:TestNonBlockingNIO2 <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2018年12月29日 下午2:08:47 <br/> 
 * @author   liu-guofei 
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public class TestNonBlockingNIO2 {


	/** 
	 * test:(这里用一句话描述这个方法的作用). <br/> 
	 * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	 * 
	 * @author gcx  
	 * @throws IOException 
	 * @since JDK 1.8 
	 */  
	@Test
	public void send() throws IOException {
		DatagramChannel dc = DatagramChannel.open();
		
		dc.configureBlocking(false);
		
		ByteBuffer buf = ByteBuffer.allocate(1024);
		
		Scanner scanner = new Scanner(System.in);
		
		while (scanner.hasNext()) {
			String str = scanner.next();
			buf.put((new Date().toString()+":\n"+str).getBytes());
			buf.flip();
			dc.send(buf, new InetSocketAddress("127.0.0.1", 9898));
			buf.clear();
		}
		
		dc.close();
	}
	
	@Test
	public void receive() throws IOException {
		
		DatagramChannel dc = DatagramChannel.open();
		
		dc.configureBlocking(false);
		
		dc.bind(new InetSocketAddress(9898));
		
		Selector selector = Selector.open();
		
		dc.register(selector, SelectionKey.OP_READ);
		
		while (selector.select() > 0) {
			 Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			 
			 while (iterator.hasNext()) {
				SelectionKey sk = iterator.next();
				 if (sk.isReadable()) {
					ByteBuffer buf = ByteBuffer.allocate(1024);
					
					dc.receive(buf);
					buf.flip();
					System.out.println(new String(buf.array(), 0, buf.limit()));
					buf.clear();
				}
			}
			 iterator.remove();
		}
		
	}
}
 