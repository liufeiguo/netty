/** 
 * Project Name:netty 
 * File Name:TestChannel.java 
 * Package Name:com.liu.netty.study 
 * Date:2018年12月28日上午9:55:49 
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.liu.netty.study;  
/** 
 * ClassName:TestChannel <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2018年12月28日 上午9:55:49 <br/> 
 * @author   gcx 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
/**
 * 
 * ClassName: TestChannel <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason: TODO ADD REASON(可选). <br/> 
 * date: 2018年12月28日 上午9:56:06 <br/> 
 * 1.channel:用于源节点与目标节点的连接。在java NIO中负责缓冲区中数据的传输 。channel本身不存储数据
 * 需要配合buffer进行传输
 * 2.通道的主要实现类
 * java.nio.channels.Channel 接口：
 *     |--FileChannel
 *     |--SocketChannel TCP
 *     |--ServerSocketChannel TCP
 *     |--DatagramChannel   UDP
 *  3.获取通道
 *    一、java针对通道的类提供了getChannel()方法
 *      本地IO
 *      FileInputStream/FileOutStream
 *      RandomAccessFile
 *      
 *      网路IO
 *      Socket
 *      ServerScoket
 *      DatagramScoket
 *     二、在JDK1.7之后的NIO2针对各个通道提供了open()
 *     三、JDK1.7之后的NIO2的Files 工具类的newByteChannnel() 
 *   4.通道之间的数据传输
 *     transferFrom()
 *     transferTo()  
 *   5.分散（Scatter）与聚集 (Gatter)
 *    分散读取（Scattering Read）:将通道中的数据分散到多个缓冲区中
 *    聚集写入 (Gattering Writes):将多个缓冲区的数据聚集到通道中
 *    6.字符集 Charset
 *    编码：字符串 -->字节数组
 *    解码： 字符数组 --> 字符串
 * @author liu-guofei  
 * @version  
 * @since JDK 1.8
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

public class TestChannel {
	
	//字符集
	@Test
	public void test6() throws CharacterCodingException {
		Charset cs = Charset.forName("UTF-8");
		
		//获取编码器
		CharsetEncoder ce = cs.newEncoder();
		
		//获取解码器
		CharsetDecoder cd = cs.newDecoder();
		
		CharBuffer charBuffer = CharBuffer.allocate(1024);
		charBuffer.put("撒大声地");
		charBuffer.flip();
		
		//编码
		ByteBuffer buffer =ce.encode(charBuffer);
		
		for (int i = 0; i < 12; i++) {
			System.out.println(buffer.get());
		}
		//解码
		buffer.flip();
		//System.out.println("111");
		CharBuffer charBuffer2 = cd.decode(buffer);
		//System.out.println("222");
		System.out.println(charBuffer2.toString());
		
		Charset charset = Charset.forName("GBK");
		buffer.flip();
		CharBuffer charBuffer3 = charset.decode(buffer);
		System.out.println(charBuffer3.toString());
		
	}
	
	@Test
	public void test5() {
		
		Map<String, Charset> maps =Charset.availableCharsets();
		for (Entry<String, Charset> entry: maps.entrySet()) {
			System.out.println(entry.getKey()+"="+entry.getValue());
		}
	}
	
	@Test
	public void test4() throws IOException {
		RandomAccessFile raf1 = new RandomAccessFile("1.txt", "rw");
		
		//获取通道
		FileChannel channel = raf1.getChannel();
		
		//2.分配制定大小的缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(100);
		ByteBuffer buffer2 = ByteBuffer.allocate(1024);
		
		//3.分散读取
		ByteBuffer[] buffers = {buffer,buffer2};
		channel.read(buffers);
		
		for (ByteBuffer byteBuffer : buffers) {
			byteBuffer.flip();
		}
		
		System.out.println(new String(buffers[0].array(), 0, buffers[0].limit()));
		System.out.println("--------------------------");
		System.out.println(new String(buffers[1].array(), 0, buffers[1].limit()));
		
		
		//4.聚集写入
		RandomAccessFile raf2 = new RandomAccessFile("2.txt", "rw");
		FileChannel channel2 = raf2.getChannel();
		
		channel2.write(buffers);
		
		channel2.close();
	}
	
	//通道之间的数据传输 直接缓冲区
	@Test
	public void test3() throws IOException {
		  FileChannel inChannel =FileChannel.open(Paths.get("1.png"), StandardOpenOption.READ); 
		  FileChannel outChannel =FileChannel.open(Paths.get("3.png"),StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);
		  
		  //inChannel.transferTo(0, inChannel.size(), outChannel);
		  
		  outChannel.transferFrom(inChannel, 0, inChannel.size());
		  
		  
		  inChannel.close();
		  outChannel.close();
	}
	
	
	  //利用通道完成文件的复制（直接缓冲区）
	  
	  @Test public void test2() throws IOException { 
		  FileChannel inChannel =FileChannel.open(Paths.get("1.png"), StandardOpenOption.READ); 
		  FileChannel outChannel =FileChannel.open(Paths.get("3.png"),StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);
	  
	  //内存映射文件 
	  MappedByteBuffer inMappedByteBuffer =inChannel.map(MapMode.READ_ONLY, 0, inChannel.size()); 
	  MappedByteBuffer outMappedByteBuffer = outChannel.map(MapMode.READ_WRITE, 0, inChannel.size());
	  
	  //直接对缓冲区进行数据的读写操作
	  byte[] by = new byte[inMappedByteBuffer.limit()];
	  inMappedByteBuffer.get(by); outMappedByteBuffer.put(by);
	  
	  if (outChannel!=null) { outChannel.close(); } if (inChannel !=null) {
	  inChannel.close(); }
	  
	  
	  }
	 
	//使用直接缓冲区完成文件的复制(内存映射文件)
	/*
	 * @Test public void test2() throws IOException{//2127-1902-1777 long start =
	 * System.currentTimeMillis();
	 * 
	 * FileChannel inChannel = FileChannel.open(Paths.get("1.png"),
	 * StandardOpenOption.READ); FileChannel outChannel =
	 * FileChannel.open(Paths.get("3.png"), StandardOpenOption.WRITE,
	 * StandardOpenOption.READ, StandardOpenOption.CREATE);
	 * 
	 * //内存映射文件 MappedByteBuffer inMappedBuf = inChannel.map(MapMode.READ_ONLY, 0,
	 * inChannel.size()); MappedByteBuffer outMappedBuf =
	 * outChannel.map(MapMode.READ_WRITE, 0, inChannel.size());
	 * 
	 * //直接对缓冲区进行数据的读写操作 byte[] dst = new byte[inMappedBuf.limit()];
	 * inMappedBuf.get(dst); outMappedBuf.put(dst);
	 * 
	 * inChannel.close(); outChannel.close();
	 * 
	 * long end = System.currentTimeMillis(); System.out.println("耗费时间为：" + (end -
	 * start)); }
	 */
    /**
     * 
     * test1:通过通道完成文件的复制. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author gcx  
     * @throws IOException 
     * @since JDK 1.6
     */
	//利用通道完成文件的复制（非直接缓冲区）
	@Test
	public void test1() throws IOException {
		FileInputStream fis = new FileInputStream("1.png");
		FileOutputStream fus = new FileOutputStream("2.png");
		
		/*
		 *获取通道
		 */
		FileChannel inChannel =fis.getChannel();
		FileChannel outChannel = fus.getChannel();
		/*
		 * 缓冲区
		 *
		 */
		ByteBuffer buf = ByteBuffer.allocate(1024);
		
		/*
		 * 将通道中的数据写入缓冲区
		 */
		while(inChannel.read(buf) != -1) {
			 buf.flip();//切换成读取数据的模式
			 //将缓冲区的数字写入通道中
			 outChannel.write(buf);
			 buf.clear();//清空缓冲区
		}
		if (outChannel!=null) {
			outChannel.close();
		}
		if (inChannel !=null) {
			inChannel.close();
		}
		if (fus !=null) {
			fus.close();

		}
		if (fis !=null) {
			fis.close();
		}
				
	}
	
}
 