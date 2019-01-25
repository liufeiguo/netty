/** 
 * Project Name:netty 
 * File Name:TestNonBlockingNIO.java 
 * Package Name:com.liu.netty.study 
 * Date:2018年12月29日上午10:07:03 
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.liu.netty.study;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import org.junit.Test;





/** 
 * ClassName: TestNonBlockingNIO <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason: TODO ADD REASON(可选). <br/> 
 * date: 2018年12月29日 上午10:08:37 <br/> 
 * 
 * @author liu-guofei
 * @version  
 * @since JDK 1.8 
 */  
public class TestNonBlockingNIO {
     
	/** 
	 * client:客户端 <br/> 
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
	public void client() throws IOException {
		
		//获取通道
		SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
		
		//切换成非阻塞模式
		sChannel.configureBlocking(false);
		
		//分配指定大小的缓冲区
		ByteBuffer buf = ByteBuffer.allocate(1024);
		
		//发送数据给服务端
		Scanner scanner = new Scanner(System.in);
				
		
		//发送数据给服务端
		while (scanner.hasNext()) {
			String string = scanner.next();
			buf.put((new Date().toString()+"\n" +string).getBytes());
			buf.flip();
			sChannel.write(buf);
			buf.clear();
		}
		
		
		sChannel.close();
		
	}
	
	@Test
	public void server() throws IOException {
		//获取通道
		ServerSocketChannel sChannel = ServerSocketChannel.open();
		
		//切换为非阻塞模式
		sChannel.configureBlocking(false);
		
		//绑定连接
		sChannel.bind(new InetSocketAddress(9898));
		
		//获取选择器
		Selector selector = Selector.open();
		
		//将通道注册到选择器上(指点接收事件)
		sChannel.register(selector, SelectionKey.OP_ACCEPT);
		
		//轮询式的获取选择器上已经准备的事件
		while (selector.select() > 0) {
			//已注册的监听事件
			Iterator<SelectionKey> it = selector.selectedKeys().iterator();
			
			//迭代获取
			while (it.hasNext()) {
				//获取准备就绪的事件
				
				SelectionKey sk = it.next();
				
				//判断具体时间什么事件准备就绪
				
				if (sk.isAcceptable()) {
					
					//接收就绪，获取连接
					SocketChannel accept = sChannel.accept();
					
					//切换非阻塞模式
					
					accept.configureBlocking(false);
					
					//将该通道注册到选择器上
					
					accept.register(selector, SelectionKey.OP_READ);
				}else if (sk.isReadable()) {
					
					//获取当前选择器上读就绪的状态
					SocketChannel channel = (SocketChannel) sk.channel();
					 
					//读取数据
					ByteBuffer buf = ByteBuffer.allocate(1024);
					int len = 0;
					while ((len = channel.read(buf)) >0) {
						buf.flip();
						System.out.println(new String(buf.array(), 0, len));
						buf.clear();
					}
				}
			}
			//取消选择键 SelectionKey
			it.remove();
		}
	}
}
 