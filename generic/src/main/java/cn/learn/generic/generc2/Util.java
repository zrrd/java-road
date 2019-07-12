package cn.learn.generic.generc2;

/**
 * @author shaoyijiong
 * @date 2019/7/11
 */
public class Util {

  public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {

    return p1.getKey().equals(p2.getKey()) && p1.getValue().equals(p2.getValue());
  }


  /**
   * 数组中大于 需要比较元素的个数  边界符
   */
  public static <T extends Comparable<T>> int countGreaterThan(T[] anArray, T elem) {
    int count = 0;
    for (T e : anArray) {
      if (e.compareTo(elem) > 0) {
        ++count;
      }
    }
    return count;
  }
}
