package cn.learn.generic.generic;

/**
 * 定义带类型参数的方法 ，限定返回类型， 返回为T 或者 S S 是要继承于T的
 *
 * @author shaoyijiong
 */
@SuppressWarnings("unused")
public class GenericTest2 {

  public <T, S extends T> S testGenericMethodDefine(T t, S s) {
    System.out.println(t);
    return s;
  }
}
