package cn.learn.generic.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型只是编译时使用的  在运行时吧<>中的信息 擦除 泛型变成固定类型
 *
 * @author shaoyijiong
 */
public class GenericTest4 {

  List<?> unknowList;
  List<? extends Number> unknowNumberList;

  public void genericIns() {
    List<String> list = new ArrayList<>();
    List<Integer> list1 = new ArrayList<>();
    System.out.println(list.getClass() == list1.getClass());
  }
}
