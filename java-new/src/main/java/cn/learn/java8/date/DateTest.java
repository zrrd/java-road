package cn.learn.java8.date;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author shaoyijiong
 * @date 2021/7/20
 */
public class DateTest {

  public void testLocalDateTime() {
    // 使用静态方法实例化
    LocalDate d = LocalDate.now(); // 当前日期
    LocalTime t = LocalTime.now(); // 当前时间
    LocalDateTime dt = LocalDateTime.now(); // 当前日期和时间

    System.out.println(d); // 严格按照ISO 8601格式打印 2021-07-20
    System.out.println(t); // 严格按照ISO 8601格式打印 15:42:59.069074268
    System.out.println(dt); // 严格按照ISO 8601格式打印
    System.out.println(new Date()); // Tue Jul 20 23:48:03 CST 2021
    // 后面都以 LocalDateTime 为例

    // 通过 of 方法实例化
    LocalDateTime dateTime = LocalDateTime.of(2021, 10, 1, 9, 30, 0);
    System.out.println(dateTime); // 2021-10-01T09:30

    // 获取时间的各个基本属性
    int calendarHour = Calendar.getInstance().get(Calendar.MONTH); // 通过Calendar获取当前月份 6

    int year = dateTime.getYear(); // 获取年 2021
    Month month = dateTime.getMonth(); // 获取月 10
    int hour = dateTime.getHour(); // 获取小时 9
    int dayOfMonth = dateTime.getDayOfMonth(); // 获取在一个月的第几天 1
    DayOfWeek dayOfWeek = dateTime.getDayOfWeek(); // 获取星期几 FRIDAY
    boolean leapYear = dateTime.toLocalDate().isLeapYear(); // 是否为闰年 false
    // ***

    // 修改各个基本属性
    // LocalDateTime 是不可变的 , 所以修改玩以后都会返回一个新的时间
    LocalDateTime withYear = dateTime.withYear(2022); // 修改年到2022年
    LocalDateTime withMonth = dateTime.withMonth(11); // 修改月份到11月
    LocalDateTime withDayOfMonth = dateTime.withDayOfMonth(31); // 修改日期到31号
    //***

    System.out.println(withYear); //  2022-10-01T09:30
    System.out.println(withMonth); //  2021-11-01T09:30
    System.out.println(withDayOfMonth); // 2021-1-31T09:30
  }

  private StringRedisTemplate redisTemplate;


  public void durationTest() {
    // 当前时间
    LocalDateTime now = LocalDateTime.now();
    // 老的时间
    LocalDateTime old = LocalDateTime.of(1949, 10, 1, 9, 10);
    // 两个时间的时间间隔 有正负 , 如果将 now 放在前面就都为下面都负数了
    Duration duration = Duration.between(old, now);
    // 有了以下方法能方便的计算一些有意义的日子了 比如结婚100天 10000小时 100000分钟 🤷‍♂️
    long days = duration.toDays();              // 这段时间的总天数
    long hours = duration.toHours();            // 这段时间的小时数
    long minutes = duration.toMinutes();        // 这段时间的分钟数
    long seconds = duration.getSeconds();       // 这段时间的秒数
    long milliSeconds = duration.toMillis();    // 这段时间的毫秒数
    long nanoSeconds = duration.toNanos();      // 这段时间的纳秒数

    // 使用of方法创建
    // 5分钟间隔
    Duration ofMinutes = Duration.ofMinutes(5);
    // 10天间隔
    Duration of = Duration.of(10, ChronoUnit.DAYS);

    // 支持Duration的api

    // redis过期时间10分钟
    // 第一种传参 时间间隔 更像一个整体参数可读性更高
    // 第二个虽然也能看出来是10分钟 , 但是直观上第三个参数 10 和第四个参数 TimeUnit.MINUTES 关联不强
    // 第三种可读性最差 , 不能一眼看出来过期时间
    redisTemplate.opsForValue().set("key", "value", Duration.ofMinutes(10));
    redisTemplate.opsForValue().set("key", "value", 10, TimeUnit.MINUTES);
    redisTemplate.opsForValue().set("kye", "value", 10 * 60);

    // 自己写代码的如果需要一个类似时间段的参数可以使用 Duration

    // Period 与 Duration 类似 , 只不过是是日期差值
  }

  public void dateTimeFormatterTest() {
    //定义了很多默认的时间格式
    String format1 = DateTimeFormatter.ISO_LOCAL_DATE.format(LocalDate.of(2017, 1, 1));
    String format2 = DateTimeFormatter.BASIC_ISO_DATE.format(LocalDate.of(2017, 1, 1));
    String format3 = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.of(2017, 1, 1, 9, 10, 0));

    System.out.println(format1);     // 2017-01-01
    System.out.println(format2);     // 20170101
    System.out.println(format3);     // 2017-01-01T09:10:00

    // 本地化的时间显示
    String ofLocalizedDate = LocalDate.of(2017, 1, 1).format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
    String ofLocalizedTime = LocalTime.of(9, 10, 0).format(DateTimeFormatter.ofLocalizedTime(FormatStyle.LONG));
    String ofLocalizedDateTime = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));

    System.out.println(ofLocalizedDate); // 2017年1月1日 星期日
    System.out.println(ofLocalizedTime); // 上午09时10分00秒
    System.out.println(ofLocalizedDateTime); // 2021-7-21 12:17:25

    // 自定义格式化方式
    // 2017-02-27 22:48:52
    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());

    // 字符串
    // 字符串 默认使用 使用的ISO的时间格式解析  2017-01-01
    LocalDate.parse("2017-01-01");
    // 使用自定义格式解析  2017-01-01T08:08:08
    LocalDateTime.parse("2017-01-01 08:08:08", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
  }


  public void timeAdjusters() {
    // 开发中遇到时间加减或者调整的问题 , 使用原生时间api处理困难 , 使用新的时间 api 可以轻松解决
    LocalDateTime now = LocalDateTime.now();
    // 当前时间增加1小时
    LocalDateTime plusHours = now.plusHours(1);
    // 当前时间增加10秒
    LocalDateTime plusSeconds = now.plusSeconds(10);

    // ChronoUnit 表示时间单位 , 比如 毫秒 秒 小时 分钟 天 年 月 日 周 半天等等
    // 类似 TimeUnit 不过更丰富
    LocalDateTime plus = now.plus(10, ChronoUnit.MINUTES);

    // 时间比较
    LocalDateTime old = LocalDateTime.of(1949, 10, 1, 9, 10);
    boolean after = now.isAfter(old); // 是否早于 true
    boolean before = now.isBefore(old); // 是否晚于 false

    // 使用TemporalAdjusters实现更复杂的时间调整
    LocalDateTime adjust1 = now.with(TemporalAdjusters.next(DayOfWeek.TUESDAY)); // 到下周二
    LocalDateTime adjust2 = now.with(TemporalAdjusters.firstDayOfNextMonth());// 到下个月的第一天
    // 使用链式api调整
    // 当前时间 -> ->  本身就是礼拜3?不变:下个礼拜3 -> 这个月的最后一天
    LocalDateTime adjust3 =
        now.with(TemporalAdjusters.nextOrSame(DayOfWeek.WEDNESDAY)).with(TemporalAdjusters.lastDayOfMonth());

    // TemporalAdjuster 本身是函数接口 也可以自己实现逻辑
    // 定一个日期，计算该日期的下一个工作日（不包括星期六和星期天）
    TemporalAdjuster temporalAdjuster = t -> {
      // 当前日期
      DayOfWeek dayOfWeek = DayOfWeek.of(t.get(ChronoField.DAY_OF_WEEK));
      // 正常情况下，每次增加一天
      int dayToAdd = 1;
      // 如果是星期五，增加三天
      if (dayOfWeek == DayOfWeek.FRIDAY) {
        dayToAdd = 3;
      }
      // 如果是星期六，增加两天
      if (dayOfWeek == DayOfWeek.SATURDAY) {
        dayToAdd = 2;
      }
      return t.plus(dayToAdd, ChronoUnit.DAYS);
    };
    LocalDateTime adjust4 = now.with(temporalAdjuster);

  }

  public void go() {
    // 系统每天发通知短信限额3条 => redis 计数 , 当天凌晨失效
    redisTemplate.opsForValue().increment("sms-limit");
    // 失效时间
    LocalDateTime time = LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.MIN);
    // 当前 redisTemplate 还不支持 expireAt localDateTime
    // 所有还是要转 date
    Date date = Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    redisTemplate.expireAt("sms-limit", date);
  }

  public void go1() {
    // 一个计费的定时任务 , 每月的最后一天额外计算出当月的账单
    // 当日计费
    dayBilling();
    // 该月的最后一天
    LocalDate lastDayOfMonth  = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
    // 如果今天是该月的最后一天
    if (lastDayOfMonth.isEqual(LocalDate.now())) {
      mouthBilling();
    }
  }

  private void dayBilling() {

  }

  private void mouthBilling() {

  }


  public void go2() {
    // 微信发送消息 , 显示显示发送时间
    // 与当前时间差1天显示日期
    // 与当前时间差小于1天大于1小时显示多少小时前
    // 与当前时间差小于1小时大于1分钟显示多少分钟
    // 一分钟内显示刚刚
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime sendTime = LocalDateTime.now().plusSeconds(-1);
    Duration duration = Duration.between(sendTime, now);
    // 大于1天显示时间
    if (duration.toDays() >= 1) {
      System.out.println(sendTime);
      return;
    }
    // 大于1个小时显示 xx小时前
    if (duration.toHours() >= 1) {
      System.out.println(duration.toHours() + "小时前");
      return;
    }
    // 大于1分钟显示 xx分钟前
    if (duration.toMinutes() >= 1) {
      System.out.println(duration.toMinutes() + "分钟前");
      return;
    }
    // 显示刚刚
    System.out.println("刚刚");
  }


}
