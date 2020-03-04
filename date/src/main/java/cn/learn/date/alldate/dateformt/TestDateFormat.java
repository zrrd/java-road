package cn.learn.date.alldate.dateformt;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shaoyijong
 */
@Slf4j
public class TestDateFormat {

  public static void main(String[] args) {
    dateSimpleFormatTest(Calendar.getInstance());
  }


  public static void dateFormatTest(Calendar c) {
    /*String都是先转换为java.util.Date，然后再转换成所需的格式
    DateFormat 的作用是 格式化并解析“日期/时间”。实际上，它是Date的格式化工具，
    它能帮助我们格式化Date，进而将Date转换成我们想要的String字符串供我们使用
    有4种风格FULL，LONG，MEDIUM和SHORT

    DateFormat.SHORT
    DateFormat是一个抽象类 DateFormat.getDateInstance()返回的是DateFormat的子类SimpleDateFormat对象

    FULL 0 2017年11月16日 星期四
    LONG 1 2017年11月16日
    MEDIUM 2 2017-11-16
    SHORT 3 17-11-16*/

    //也可以出入一个local参数表示地点和语言
    //输出日期
    DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
    //输出时间和日期
    DateFormat df1 = DateFormat.getDateTimeInstance();
    //输出时间
    DateFormat df2 = DateFormat.getTimeInstance();

    //Calendar的返回类型是date
    df.format(c.getTime());
    // String 转 date ; 需要捕获异常
    try {
      df.parse("2017年11月16日");
    } catch (ParseException e) {
      log.error("时间转化失败", e);
    }
  }

  /**
   * <pre>
   * 定好的字母 字母	日期或时间元素	表示	示例 G	Era 标志符	Text	AD
   * y	年	Year	1996; 96
   * M	年中的月份	Month	July; Jul; 07
   * w	年中的周数	Number	27
   * W	月份中的周数	Number	2
   * D	年中的天数	Number	189
   * d	月份中的天数	Number	10
   * F	月份中的星期	Number	2
   * E	星期中的天数	Text	Tuesday; Tue a	Am/pm 标记	Text	PM
   * H	一天中的小时数（0-23）	Number	0
   * k	一天中的小时数（1-24）	Number	24
   * K	am/pm 中的小时数（0-11）	Number	0
   * h	am/pm 中的小时数（1-12）	Number	12
   * m	小时中的分钟数	Number	30
   * s	分钟中的秒数	Number	55
   * S	毫秒数	Number	978
   * z	时区	General time zone	Pacific Standard Time; PST; GMT-08:00
   * Z	时区	RFC 822 time zone	-0800
   * </pre>
   */
  public static void dateSimpleFormatTest(Calendar c) {
    //一般常用的时间格式yyyyMMddHHmmss
    SimpleDateFormat sdf = new SimpleDateFormat("y k");
    sdf.format(c.getTime());
    try {
      sdf.parse("1995 1");
    } catch (ParseException e) {
      log.error("时间转化失败", e);
    }
  }
}
