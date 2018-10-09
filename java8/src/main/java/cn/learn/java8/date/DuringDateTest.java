package cn.learn.java8.date;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;

/**
 * 时间差处理
 *
 * @author 邵益炯
 * @date 2018/10/9
 */
public class DuringDateTest {

  private static void testPeriod() {
    //处理有关基于时间的日期数量 处理年月日的
    LocalDate localDate = LocalDate.now();
    LocalDate plusWeeks = localDate.plusWeeks(3);
    System.out.println("当前日期" + localDate);

    Period between = Period.between(localDate, plusWeeks);
    //两个时间差
    System.out.println("时间差" + between.getDays());
    //时间差相加
    Period days = Period.ofDays(10);
    localDate = localDate.plus(days);
    System.out.println("当前日期增加十天" + localDate);
  }

  private static void testDuration() {
    //处理有关基于时间的时间量 处理时分秒的
    LocalTime localTime = LocalTime.now();
    Duration duration = Duration.ofSeconds(30);
    LocalTime localTime1 = localTime.plus(duration);
    System.out.println("当前时间" + localTime);
    System.out.println("增加30s后时间差" + Duration.between(localTime, localTime1).toMillis());
  }

  public static void main(String[] args) {
    testPeriod();
    testDuration();
  }
}
