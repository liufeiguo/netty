package java8;

import java.util.Arrays;
import java.util.function.Function;

import org.junit.Test;

/**
 * @author liuguofei
 * @time 上午10:40:35
 * @desprition
 * @pacake netty
 */
public class TestMethodRef {

	@Test
	public void test1() {
		
		Function<Integer, String[]> fun = (x)-> new String[x];
		fun.apply(10);
		
		Function<Integer, String[]> fun2 = String[]::new;
		String[] apply = fun2.apply(20);
		System.out.println(fun2.toString());
		System.out.println(Arrays.toString(apply));
		
		
	}
}
