package cn.learn.generic.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * 定义带类型参数的方法 ，限定返回类型， 返回为T 或者 S S 是要继承于T的
 *
 * @author shaoyijiong
 */
@SuppressWarnings("unused")
public class GenericTest3 {

  public <T> T testGenericMethodDefine(T t, List<T> list2) {
    System.out.println(t.toString());
    list2.forEach(a -> System.out.println(a.toString()));
    return null;
  }

  public static void main(String[] args) {
    GenericTest3 test3 = new GenericTest3();
    // 泛型String 由于参数中带了泛型 所以返回值上的泛型可以擦除
    final String a = test3.<String>testGenericMethodDefine("a", new ArrayList<>());
  }
}
