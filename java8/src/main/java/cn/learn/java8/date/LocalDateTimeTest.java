package cn.learn.java8.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

/**
 * java日期测试 根据系统中当前时刻和默认时区计算出年月日的信息
 *
 * @author 邵益炯
 * @date 2018/10/9
 */
public class LocalDateTimeTest {

  private static void testLocalDateTime() {
    //LocalDateTime LocalDate LocalTime 都有大量的方法来判断与设置时间格式
    //显示日期与时分秒
    LocalDateTime localDateTime = LocalDateTime.now();
    System.out.println(localDateTime);
    //显示日期
    LocalDate localDate = localDateTime.toLocalDate();
    System.out.println(localDate);
    //显示时分秒毫秒
    LocalTime localTime = localDateTime.toLocalTime();
    System.out.println(localTime);

    //month是个枚举类哦
    Month month = localDateTime.getMonth();
    int day = localDateTime.getDayOfMonth();
    int seconds = localDateTime.getSecond();
    System.out.println("Month: " + month
        + "day: " + day
        + "seconds: " + seconds
    );

    //有许多静态方法来创建时间
    LocalDateTime date2 = localDateTime.withDayOfMonth(10).withYear(2012);
    System.out.println("date2: " + date2);

    //12 december 2014
    LocalDate date3 = LocalDate.of(2014, Month.DECEMBER, 12);
    System.out.println("date3: " + date3);

    //22 hour 15 minutes
    LocalTime date4 = LocalTime.of(22, 15);
    System.out.println("date4: " + date4);

    //parse a string
    LocalTime date5 = LocalTime.parse("20:15:30");
    System.out.println("date5: " + date5);
  }

  public static void main(String[] args) {
    testLocalDateTime();
  }
}
