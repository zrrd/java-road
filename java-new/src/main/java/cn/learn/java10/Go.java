package cn.learn.java10;

import java.util.ArrayList;

/**
 * @author shaoyijiong
 * @date 2020/9/18
 */
public class Go {

  /**
   * 局部类型推断
   */
  private static void  infer() {
    // 局部类型推断

    // ArrayList<String>
    var list = new ArrayList<String>();
    // Stream<String>
    var stream = list.stream();

    for (var s : list) {
      System.out.println(s);
    }
  }
}
