package cn.learn.date.alldate.untilDate;

import java.util.Date;
/**
 * 所有方法的测试放在test.allTimeType.untilDate.TestDateTest包下
 *
 * @author shaoyijiong
 * */
public class TestDate {
    /**
     * date 很多构造方法 但是到1.8版本 就剩下无参和 long参数这两个构造方法
     * */
    /**
     * 第一个是无参构造器，使用系统当前时间的毫秒数来创建Date对象，
     * 它调用了java.lang.System类的currentTimeMillis()来取得系统的当前时间的毫秒值。这是个本地方法
     * */
    /**
     * Wed Nov 15 08:58:05 CST 2017
     * 打印出来的时间分别是 星期 月份 几号 时 分 秒 CTS（China Standard Time中国标准时间）年
     * */
    Date date0 = new Date();

    /**
     * 通过date0.getTime()可以获取毫秒时间
     * 有参的构造函数参数也是毫秒时间
     * */

    Date date1 = new Date(1510707631401L);

    /**
     * Thu Jan 01 08:00:00 CST 1970是基准时间，+- 多少毫秒来表示毫秒时间
     * */
    Date date2 = new Date(0L);
}
