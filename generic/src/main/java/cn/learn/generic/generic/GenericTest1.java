package cn.learn.generic.generic;

/**
 * 类后面的泛型，用于类的任何地方 S 是要继承于T的 限定S的范围
 *
 * @author shaoyijiong
 */
public class GenericTest1<T, S extends T> {

  public void printThis(T t, S s) {
    System.out.println(t.toString());
    System.out.println(s.toString());
  }


}
