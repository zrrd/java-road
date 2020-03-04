package cn.learn.annotation.common;

import org.junit.Test;

/**
 * @author shaoyijiong
 */
public class Sample2 {

  @Test
  @ExceptionTest(ArithmeticException.class)
  public static void m1() {
    int i = 0;
    i = i / i;
  }

  public static void main(String args[]) {
    m1();
  }
}
