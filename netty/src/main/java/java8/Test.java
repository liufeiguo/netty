package java8;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class Test {
    

	public static void main(String[] args) {
		Object o = new Object();//强引用
		o = null;
		System.out.println(o);
		String str = new String("aaa");
		
		SoftReference<String> softReference = new SoftReference<String>(str);//软引用
		System.out.println(softReference);
		
		
		WeakReference<String> weakReference = new WeakReference<String>(str);
		System.out.println(weakReference);
	}
}
