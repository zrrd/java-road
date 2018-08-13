package cn.learn.date.alldate.Calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author shaoyijijong
 */
public class TestCalendar {

  /**
   * Calendar是一个抽象类 无法直接实例化 通过以下两种方法获得Calendar的实例
   */
  private static Calendar calendar0 = Calendar.getInstance();
  /**
   * GregorianCalendar 是Calendar的子类
   */
  private static Calendar calendar1 = new GregorianCalendar();

  /**
   * Calendar内部定义了很多数字常量，通过字符常量获取不同角度的时间信息
   */
  public static int getTimeAnyway() {
    return calendar0.get(Calendar.DATE);
  }

  public static void main(String[] args) {
    System.out.println(calendar0.getCalendarType());
    System.out.println(calendar0.getTimeZone());
    System.out.println(calendar0.get(Calendar.SECOND));
    System.out.println(getTimeAnyway());
  }

}
