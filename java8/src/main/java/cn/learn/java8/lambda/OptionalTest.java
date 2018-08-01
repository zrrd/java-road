package cn.learn.java8.lambda;

import java.util.Optional;

/**
 * Optional避免空指针异常.
 *
 * @author shaoyijiong
 * @date 2018/7/24
 */
public class OptionalTest {

  public static void main(String[] args) {
    String str = "hello";
    String test = Optional.ofNullable(str).map(s -> s+"a").orElse("空");
    System.out.println(test);
  }
}
