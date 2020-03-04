package cn.learn.generic.generc2;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author shaoyijiong
 * @date 2019/7/11
 */
public class Go {


  /**
   * 类上的泛型
   */
  private static void t1() {
    Box<Integer> box = new Box<>();
    Box<String> box1 = new Box<>();
  }

  private static void t2() {
    Pair<Integer, String> p1 = new Pair<>(1, "apple");

    Pair<Integer, String> p2 = new Pair<>(2, "pear");

    //方法泛型 泛型加载方法前
    Util.<Integer, String>compare(p1, p2);
    //java 1.7 与 1.8 利用type inference，让Java自动推导出相应的类型参数
    Util.compare(p1, p2);

  }

  private static void t3() {
    Integer a[] = new Integer[]{1,2,3,4,5,6,7,8,9,10};

    Util.<Integer>countGreaterThan(a, 5);

    Util.countGreaterThan(a, 5);

  }


  public static void main(String[] args) {
    t3();
  }
}
