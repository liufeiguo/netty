package java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * @author liuguofei
 * @time 下午4:22:02
 * @desprition
 * @pacake netty
 * 
 */
public class TestStreamAPI {

	/**
	 *通过Collection系列集合提供的 stream()或paralleStream()
	 */
	@Test
	public void test1() {
	
		List<String> list = new ArrayList< >();
		@SuppressWarnings("unused")
		Stream<String> stream1 = list.stream();
		
		//Arrays 中的静态方法
		Employee[] employees = new Employee[10];
		@SuppressWarnings("unused")
		Stream<Employee> stream = Arrays.stream(employees);
		
		//stream中的静态方法获取of()
		@SuppressWarnings("unused")
		Stream<String> of = Stream.of("aa","bb","cc");
		
		//创建无限流  迭代
		Stream<Integer> iterate = Stream.iterate(0, (x)->x+2);
		iterate.limit(10).forEach(System.out::println);
		
		Stream.generate(()->Math.random())
		      .limit(5)
		      .forEach(System.out::println);
		
	}
	public List<Employee> emps = Arrays.asList(
			new Employee(18, "zs", 23, 18888),
			new Employee(15, "lisi", 24, 1888),
			new Employee(19, "wangwu", 25, 118888),
			new Employee(23, "maliu", 12, 188),
			new Employee(36, "zhaoqi", 5, 1888888),
			new Employee(36, "zhaoqi", 5, 1888888),
			new Employee(36, "zhaoqi", 5, 1888888)
			);
	@Test	//迭代由StreamAPI完成
	public void test2() {
		emps.stream()
		    .filter((x)->{
		    	System.out.println(1);
		    	return x.getAge()>23;
		    	})
		    .forEach(System.out::println);
		    
	}
	@Test
	public void test3() {
		emps.stream()
		.filter((e) -> e.getSalary()>5000)
		.limit(2)
		.skip(1)
		.forEach(System.out::println);
	}
	@Test
	public void test4() {
		emps.stream()
		.filter((e) -> e.getSalary()>5000)
		.distinct()//根据equals和hashCode去重
		.forEach(System.out::println);
	}
	@Test
	public void test5() {
		List<String> list = Arrays.asList("aaa","bbb","ccc","ddd","eee");
		
		list.stream()
		    .map((str)->str.toUpperCase())
		    .forEach(System.out::println);
		
		emps.stream()
		    .map(Employee::getName )
		    .forEach(System.out::println);
		
		Stream<Stream<Character>> map = list.stream().map(TestStreamAPI::filterCharacter);
		      map.forEach((s)->{
		    	  s.forEach(System.out::println);
		      });
		      
		      list.stream().flatMap(TestStreamAPI::filterCharacter)
		                   .forEach(System.out::println);
		
	}
	
	public static Stream<Character> filterCharacter(String str) {
		List<Character> list = new ArrayList<>();
		
		for (Character character : str.toCharArray()) {
			list.add(character);
		}
		
		return list.stream();
	}
	@Test
	public void test7() {
		List<String> list = Arrays.asList("aaa","bbb","ccc","ddd","eee");
		list.stream()
		    .sorted()
		    .forEach(System.out::println);
		emps.stream()
	    .sorted((e1,e2) ->{
	    	if (e1.getAge()==(e2.getAge())) {
				return e1.getName().compareTo(e2.getName()); 
			}else {
				return Integer.compare(e1.getAge(), e2.getAge());
			}
	    	
	    }).forEach(System.out::println);
	}
	
	
}
