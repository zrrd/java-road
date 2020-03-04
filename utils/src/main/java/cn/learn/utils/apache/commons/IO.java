package cn.learn.utils.apache.commons;

import java.util.Date;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * apache IO 工具类
 *
 * @author 邵益炯
 * @date 2018/11/22
 */
public class IO {

  public static void main(String[] args) {
    // 加一个月 下一个月
    final Date date1 = DateUtils.addMonths(new Date(), 1);
    // 减一个月 下一个月
    final Date date2 = DateUtils.addMonths(new Date(), -1);
    final Date date = DateUtils.setDays(new Date(), 1);
    System.out.println(DateFormatUtils.format(date,"yyyy-MM-dd"));
  }
}
