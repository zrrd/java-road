package cn.learn.java8.lambda;

import java.util.Optional;
import java.util.Random;

/**
 * Optional避免空指针异常.
 *
 * @author shaoyijiong
 * @date 2018/7/24
 */
public class OptionalTest {

  public static void main(String[] args) {
    Random random = new Random();
    int i = random.nextInt(100);

    String str = i > 50 ? "hello" : null;
    String test = Optional.ofNullable(str).map(s -> s + "a").orElse("空");
    System.out.println(test);
  }
}
