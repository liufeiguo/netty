package java8;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Test;

import java8.Employee.Status;

public class TestStreamAPI2 {

	public List<Employee> emps = Arrays.asList(new Employee(18, "zs", 23,
			18888, Status.FREE),
			new Employee(15, "lisi", 24, 1888, Status.BUSY), new Employee(19,
					"wangwu", 25, 118888, Status.VOCATION), new Employee(23,
					"maliu", 12, 188, Status.FREE), new Employee(36, "zhaoqi",
					5, 1888888, Status.FREE), new Employee(36, "zhaoqi", 5, 1888888, Status.FREE),
			new Employee(36, "zhaoqi", 5, 1888888, Status.FREE));

	@Test
	public void test1() {
		boolean allMatch = emps.stream().allMatch(
				(e) -> e.getStatus().equals(Status.BUSY));
		System.out.println(allMatch);

		boolean anyMatch = emps.stream().anyMatch(
				(e) -> e.getStatus().equals(Status.BUSY));

		System.out.println(anyMatch);

		boolean noneMatch = emps.stream().noneMatch(
				(e) -> e.getStatus().equals(Status.BUSY));
		System.out.println(noneMatch);

		Optional<Employee> findFirst = emps
				.stream()
				.sorted((e1, e2) -> Double.compare(e1.getSalary(),
						e2.getSalary())).findFirst();
		System.out.println(findFirst.get());

		Optional<Employee> findAny = emps.stream()
				.filter((e) -> e.getStatus().equals(Status.FREE)).findAny();
		System.out.println(findAny);

	}

	@Test
	public void test2() {
		long count = emps.stream().count();
		System.out.println(count);

		Optional<Employee> max = emps.stream().max(
				(e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
		System.out.println(max);

		Optional<Double> min = emps.stream().map(Employee::getSalary)
				.min(Double::compare);
		System.out.println(min);
	}

	@Test
	public void test3() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		Integer reduce = list.stream().reduce(0, (x, y) -> x + y);
		System.out.println(reduce);

		Optional<Double> reduce2 = emps.stream().map(Employee::getSalary)
				.reduce(Double::sum);
		System.out.println(reduce2);

		emps.stream().map(Employee::getName).collect(Collectors.toList())
				.forEach(System.out::println);
		;

		emps.stream().map(Employee::getName).collect(Collectors.toSet())
				.forEach(System.out::println);
		;
		
		emps.stream()
		    .map(Employee::getName)
		    .collect(Collectors.toCollection(HashSet::new))
		    .forEach(System.out::println);; 
	}
	@Test
	public void test4() {
		
		Long collect = emps.stream()
		    .collect(Collectors.counting());
		System.out.println(collect);
		
		 Double collect2 = emps.stream()
			    .collect(Collectors.averagingDouble(Employee::getSalary));
			System.out.println(collect2);
			
			 Double collect3 = emps.stream()
			    .collect(Collectors.summingDouble(Employee::getSalary));
			System.out.println(collect3);
			
		   Optional<Employee> collect4 = emps.stream()
		       .collect(Collectors.maxBy((e1,e2) ->Double.compare(e1.getSalary(), e2.getSalary())));
		   System.out.println(collect4);
		   
		   //最小值
		   Optional<Employee> collect5 = emps.stream()
		       .collect(Collectors.minBy((e1,e2) ->Double.compare(e1.getSalary(), e2.getSalary())));
		   System.out.println(collect5);
		   
	}
	
	/**
	 * 分租
	 */
	@Test
	public void test5() {
		Map<Status, List<Employee>> collect = emps.stream()
		    .collect(Collectors.groupingBy(Employee::getStatus));
		
		System.out.println(collect);
		
		Map<Status, Map<String, List<Employee>>> collect2 = emps.stream()
		    .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) ->{
		    	if (((Employee) e).getAge() <=20) {
					return "青年";
				}else {
					return "老年";
				}
		    })));
		collect2.forEach((key,value) ->{
			System.out.println(key);
			value.forEach((key1,value1)->{
				System.out.println(key1);
				System.out.println(value1);
			});
		}
		    
				);
		//System.out.println(collect2);
	}
	@Test
	public void test6() {
		
		Map<Boolean, List<Employee>> collect = emps.stream()
		    .collect(Collectors.partitioningBy((e) ->e.getSalary()>500));
		
		System.out.println(collect);
	}
}
