/** 
 * Project Name:netty 
 * File Name:TestLocalDateTime.java 
 * Package Name:java8.time 
 * Date:2019年1月4日下午1:55:35 
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package java8.time;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

import org.junit.Test;

/** 
 * ClassName:TestLocalDateTime <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2019年1月4日 下午1:55:35 <br/> 
 * @author   liu-guofei 
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
public class TestLocalDateTime {

	
	@Test
	public void test1() {
		
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);
		
		LocalDateTime of = LocalDateTime.of(2015, 10,19,13,22,33);
		System.out.println(of);
		
		LocalDateTime plusYears = ldt.plusYears(2);
		System.out.println(plusYears);
		
		LocalDateTime minusMinutes = ldt.minusMinutes(2);
		System.out.println(minusMinutes);
		
		
	}
	 
	//Instant :时间（以1970年1月一日00:00:00到某个时间之间的毫秒级）
	@Test
	public void test2() {
		
		Instant ins1 = Instant.now();//默认获取UTC 时区
		System.out.println(ins1);
		OffsetDateTime atOffset = ins1.atOffset(ZoneOffset.ofHours(8));
		System.out.println(atOffset);
        
		System.out.println(ins1.toEpochMilli());
		
		Instant ofEpochSecond = Instant.ofEpochSecond(1);
		System.out.println(ofEpochSecond);
		
	}
	
	@Test
	public void test3() {
		
		Duration between = Duration.between(Instant.now(), Instant.now());
		System.out.println(between.getNano());
	}
	@Test
	public void test4() {
		
		LocalDate ld1 = LocalDate.of(2015, 1, 1);
		LocalDate ld2 = LocalDate.now();
		
		Period between = Period.between(ld1, ld2);
		System.out.println(between.toTotalMonths());
		
	}
	/** 
	 * test5:测试TemporalAdjuster <br/> 
	 * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	 * 
	 * @author gcx  
	 * @since JDK 1.8 
	 */  
	@Test
	public void test5() {
		LocalDateTime ldt = LocalDateTime.now();
		
		LocalDateTime with = ldt.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));

		
		System.out.println(with);
		
		LocalDateTime with2 = ldt.with((l)->{
		      LocalDateTime ldt2 = (LocalDateTime) l;
		      DayOfWeek dow = ldt2.getDayOfWeek();
		      if (dow.equals(DayOfWeek.FRIDAY)) {
				return ldt2.plusDays(3);
			}else if (dow.equals(DayOfWeek.SATURDAY)) {
				return	ldt2.plusDays(2);
			}else {
				return ldt2.plusDays(1);
			}
		});
		System.out.println(with2);
		
	}
	
	/** 
	 * test6:测试DateTimeFormatter. <br/> 
	 * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	 * 
	 * @author gcx  
	 * @since JDK 1.8 
	 */ 
	@Test
	public void test6() {
		DateTimeFormatter isoDateTime = DateTimeFormatter.ISO_DATE;
		LocalDateTime ldt = LocalDateTime.now();
		
		String format = ldt.format(isoDateTime);
		System.out.println(format);
		
		DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
		String format2 = ldt.format(ofPattern);
		System.out.println(format2);
		System.out.println(ofPattern.format(ldt));
	}
	
	/** 
	 * test7:测试ZoneId、ZonedDateTime . <br/> 
	 * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	 * 
	 * @author gcx  
	 * @since JDK 1.8 
	 */  
	@Test
	public void test7() {
		//Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
		//availableZoneIds.forEach(System.out::println);
		LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
		System.out.println(now);
		ZonedDateTime atZone = LocalDateTime.now().atZone(ZoneId.of("Asia/Shanghai"));
		System.out.println(atZone);
	}
}
 