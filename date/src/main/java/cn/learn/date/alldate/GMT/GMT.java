package cn.learn.date.alldate.GMT;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * GMT 格林尼治时间，方便互联网时间统一
 *
 * @author shaoyijiong
 */
public class GMT {

  public boolean isBadyBommer() {
    Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
    gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
    Date boomStart = gmtCal.getTime();
    gmtCal.set(1964, Calendar.JANUARY, 1, 0, 0, 0);
    Date boomEnd = gmtCal.getTime();
    return false;
  }
}
