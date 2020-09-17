package cn.learn.java8.date;

import java.time.Instant;

/**
 * 处理时刻的的api , 始终是格林零时区相关
 *
 * @author shaoyijiong
 * @date 2019/9/24
 */
public class InstantTest {

  public static void main(String[] args) {
    //创建 Instant 实例
    Instant instant = Instant.now();
    System.out.println(instant);

    Instant instant1 = Instant.ofEpochSecond(20);
    System.out.println(instant1);

    Instant instant2 = Instant.ofEpochSecond(30, 100);
    System.out.println(instant2);

    Instant instant3 = Instant.ofEpochMilli(1000);
    System.out.println(instant3);

  }
}
