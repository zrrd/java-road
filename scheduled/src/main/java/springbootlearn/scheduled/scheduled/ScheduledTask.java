package springbootlearn.scheduled.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务学习
 *
 * @author shaoyijiong
 * @since 2018/7/2
 */
@Component
public class ScheduledTask {


  /**
   * 任务1 *：表示匹配该域的任意值。
   * cron 秒0-59 分0-59 小时0-23 日期1-31 月份1-12 星期1-7
   */
  @Scheduled(cron = "1 * * * * *")
  public void task1(){
    System.out.println("task1");
  }
}
