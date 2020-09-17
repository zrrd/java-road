package cn.learn.java8.date;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * @author 邵益炯
 * @date 2018/10/9
 */
public class ChronoUnitTest {

  private void testChronoUnit() {
    LocalDate localDate = LocalDate.now();
    System.out.println(localDate);
    //直接plus加ChronoUnit 中有很多时间的单位
    LocalDate nextWeek = localDate.plus(1, ChronoUnit.WEEKS);
    //直接加天
    LocalDate nextDay = localDate.plusDays(10);

    // 计算两个日期的差值
    ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.now());
  }
}
