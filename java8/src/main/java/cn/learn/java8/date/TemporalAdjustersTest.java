package cn.learn.java8.date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

/**
 * 时间调节器
 *
 * @author 邵益炯
 * @date 2018/10/9
 */
public class TemporalAdjustersTest {

  private static void testAdjusters() {
    LocalDate localDate = LocalDate.now();
    System.out.println("current date" + localDate);

    //get the next tuesday
    //TemporalAdjusters有大量的静态方法可以花式调整时间
    LocalDate nextTuesday = localDate.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
    System.out.println("Next Tuesday on : " + nextTuesday);

    //get the second saturday of next month
    LocalDate firstInYear = LocalDate.of(localDate.getYear(), localDate.getMonth(), 1);

    //当前年月的一号 -> 下个礼拜6/本身就是列表6 -> 下个礼拜6
    LocalDate secondSaturday = firstInYear.with(
        TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY)).with(
        TemporalAdjusters.next(DayOfWeek.SATURDAY));
    System.out.println("Second saturday on : " + secondSaturday);
  }

  public static void main(String[] args) {
    testAdjusters();
  }
}
