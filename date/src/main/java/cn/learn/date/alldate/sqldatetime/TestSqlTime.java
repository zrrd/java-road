package cn.learn.date.alldate.sqldatetime;

import java.util.Date;

/**
 * <pre>
 *  java.lang.Object
 * ....|__java.util.Date
 * ..........|__java.sql.Date/java.sql.Timestamp /java.sql.Time
 * sql下的时间都是Date的子类
 * 【父类】java.util.Date日期格式为：年月日时分秒
 * 【子类】java.sql.Date日期格式为：年月日[只存储日期数据不存储时间数据]
 * 【子类】java.sql.Time日期格式为：时分秒
 * 【子类】java.sql.Timestamp日期格式为：年月日时分秒纳秒（毫微秒）
 * </pre>
 *
 * @author shaoyijiong
 */
public class TestSqlTime {

  public void justTest() {
    Date date = new Date();

    // java.sql.Date有两个产生方式 一个还是继承父类的构造函数，通过传入long整数
    // 第二个就是通过静态函数valueOf  传入string字符串来构造（）格式为 *- *- *其它同理
    java.sql.Date sqlDate = java.sql.Date.valueOf("2017-7-1");
    java.sql.Time sqlTime = java.sql.Time.valueOf("15:12:59");
    //根据String时间转换格式
    java.sql.Timestamp sqlTimestamp = java.sql.Timestamp.valueOf("2017-8-8 15:13:2");

    // util.Date和sql时间的互相转换 date.getTime()返回的长整型
    sqlDate = new java.sql.Date(date.getTime());
  }
}
