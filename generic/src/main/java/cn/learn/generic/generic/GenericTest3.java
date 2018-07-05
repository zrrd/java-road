package cn.learn.generic.generic;

import java.util.List;

/**
 * @author jiudao 定义带类型参数的方法 ，限定返回类型， 返回为T 或者 S S 是要继承于T的
 */

public class GenericTest3 {

  public <T> T testGenericMethodDefine(T t, List<T> list2) {
    System.out.println(t.toString());
    list2.forEach(a -> System.out.println(a.toString()));
    return null;
  }
}
