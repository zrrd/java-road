package cn.learn.java8.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * 格式化时间
 *
 * @author 邵益炯
 * @date 2018/10/9
 */
public class DateTimeFormatterTest {
  @SuppressWarnings("all")
  private static void testFormatter() {
    /*格式化*/
    //定义了很多原来的时间格式
    LocalDateTime localDate = LocalDateTime.now();
    //2017-01-01
    String format1 = DateTimeFormatter.ISO_LOCAL_DATE.format(LocalDate.of(2017, 1, 1));
    //20170101
    String format2 = DateTimeFormatter.BASIC_ISO_DATE.format(LocalDate.of(2017, 1, 1));
    //2017-01-01T09:10:00
    String format3 = DateTimeFormatter.ISO_LOCAL_DATE_TIME
        .format(LocalDateTime.of(2017, 1, 1, 9, 10, 0));

    //2017年1月1日 星期日
    DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(LocalDate.of(2017, 1, 1));
    //上午09时10分00秒
    DateTimeFormatter.ofLocalizedTime(FormatStyle.LONG).format(LocalTime.of(9, 10, 0));
    //2017-2-27 22:32:03
    DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(LocalDateTime.now());

    //自定义格式化方式
    //2017-02-27 22:48:52
    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());

    /*解析*/
    //使用的ISO_LOCAL_DATE格式解析  2017-01-01
    LocalDate.parse("2017-01-01");
    //使用自定义格式解析  2017-01-01T08:08:08
    LocalDateTime.parse("2017-01-01 08:08:08", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
  }
}
