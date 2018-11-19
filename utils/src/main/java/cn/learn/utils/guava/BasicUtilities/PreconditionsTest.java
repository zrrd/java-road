package cn.learn.utils.guava.BasicUtilities;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.ArrayList;

/**
 * 处理参数
 *
 * @author 邵益炯
 * @date 2018/9/28
 */
public class PreconditionsTest {

  /**
   * 校验参数 就跟断言一样不对就抛异常 IllegalArgumentException
   */
  private static void test1() {
    //Exception in thread "main" java.lang.IllegalArgumentException
    Preconditions.checkArgument(1 > 2);
    //Exception in thread "main" java.lang.IllegalArgumentException: 你错了 [a] a这个位置是用来放参数的
    Preconditions.checkArgument(1 > 2, "你错了", "a");
  }

  /**
   * 校验一个对象是否为空 NullPointerException
   */
  private static void test2() {
    String a = null;
    Preconditions.checkNotNull(a, "该对象为空");
  }

  /**
   * IllegalStateException
   */
  private static void test3() {
    Preconditions.checkState(1 > 2, "状态不成立");
  }

  /**
   * IndexOutOfBoundsException
   */
  private static void test4() {
    ArrayList<String> strings = Lists.newArrayList("a", "b");
    //Preconditions.checkElementIndex(3,strings.size(),"数组越界了");
    //Preconditions.checkPositionIndex(3, strings.size(), "数组越界了");
    Preconditions.checkPositionIndexes(2, 3, strings.size());
  }

  static class Test {

    public static void main(String[] args) {
      //test1();
      //test2();
      //test3();
      test4();
    }
  }

}
