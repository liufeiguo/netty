package com.liu.netty.study;

import java.nio.ByteBuffer;

import org.junit.Test;

/** 
 * ClassName: TestBuffer <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason: TODO ADD REASON(可选). <br/> 
 * date: 2018年12月27日 下午2:48:31 <br/> 
 * descrption: 一、缓冲区（Buffer）:在java NIO 中负责数据的存取。缓冲区是数组。用于存储不同数据类型的数据(boolean 除外)
   *   例如：ByteBuffer  通过allocate()获取缓冲区
   *  缓冲区获取数据的两个核心方法
 * put() 存入
 * get() 获取   
   * 缓冲区中的四个核心属性
 * private int mark = -1; 用于记录当前postion的位置，可以通过reset()恢复到mark的位置
 * private int position = 0; 正在操作数据的位置
 * private int limit; 界限，表示缓冲区中可以操作数据的大小 （limit后数据不能进行读写）
 * private int capacity; 最大容量，一但生命不能改变
 *    0<=mark<= position <=  limit <= capacity
 *    
 * 5.直接缓冲区和非直接缓冲区 
  * 非直接 缓冲区：通过allocate()方法分配缓冲区，将缓冲区建立在JVM内存中
  *直接缓存区 ：通过allocateDirect() 方法分配缓冲区，将缓冲区建立在物理内存中，提高效率 
 *    
 * @author liu-guofei
 * @version  
 * @since JDK 1.8 
 */  
public class TestBuffer {
	
	@Test
	public void test3() {
		/**
		 * 直接缓冲区
		 */
		ByteBuffer bty = ByteBuffer.allocateDirect(1024);
		
		System.out.println(bty.isDirect());
		
		System.out.println(500000*0.06);
		
	}
	@Test
	public void test2() {
		String str = "abcde";
		ByteBuffer buf = ByteBuffer.allocate(1024);
		buf.put(str.getBytes());
		buf.flip();
		byte[] byt = new byte[buf.limit()];
        buf.get(byt,0,2);
        System.out.println(new String(byt,0,2));
        System.out.println(buf.position());
        
        //mark();
        buf.mark();
        buf.get(byt, 2, 2);
        
        System.out.println(new String(byt,2,2));
        
        System.out.println(buf.position());
        
        buf.reset();
        
        System.out.println(buf.position());
        
        if (buf.hasRemaining()) {
        	System.out.println(buf.remaining());
		}
	}

	@Test
	public void test1() {
		/**
		 * 分配一个指定大小的缓冲区
		 */
		String str = "abcde";
		ByteBuffer buf = ByteBuffer.allocate(1024);
		
		System.out.println(buf.position());
		
		System.out.println(buf.limit());
		
		System.out.println(buf.capacity());
		/**
		 * 利用put()存数据
		 */
		buf.put(str.getBytes());
		System.out.println("----------put()------------");
        System.out.println(buf.position());
		
		System.out.println(buf.limit());
		
		System.out.println(buf.capacity());
		
		/**
		 * 切换成读取数据的模式flip()
		 */
		buf.flip();
		System.out.println("----------flip()------------");
        System.out.println(buf.position());
		
		System.out.println(buf.limit());
		
		System.out.println(buf.capacity());
		
		/**
		 * 利用get()读取缓冲区中的数据
		 */
		byte[] byt = new byte[buf.limit()];
		buf.get(byt);
		System.out.println(new String(byt ,0, byt.length));
		System.out.println("----------get()------------");
        System.out.println(buf.position());
		
		System.out.println(buf.limit());
		
		System.out.println(buf.capacity());
		
		/**
		 * rewind() 可重复读数据
		 */
        buf.rewind();
		System.out.println(new String(byt ,0, byt.length));
		System.out.println("----------rewind()------------");
        System.out.println(buf.position());
		
		System.out.println(buf.limit());
		
		System.out.println(buf.capacity());
		/**
		 * clear() 清空,但是缓冲区中的数据存在，处于被遗忘状态
		 */
		buf.clear();
		System.out.println("----------clear()------------");
        System.out.println(buf.position());
		
		System.out.println(buf.limit());
		
		System.out.println(buf.capacity());

		
		
	}
	
}
