package cn.learn.guava.Collections;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

/**
 * 集合工具类
 *
 * @author 邵益炯
 * @date 2018/9/29
 */
public class UtilityClasses {

  private static void iterablesTest() {
    ImmutableList list = ImmutableList.of("a", "v", "c", "d");
    //返回a出现的次数
    int a = Iterables.frequency(list, "a");
    //拿第一个  否则拿默认值
    String b = Iterables.getFirst(list, "a");
    b = Iterables.getLast(list, "a");
    //Iterables.all();Iterables.any() 全部存在  或者存在一个
  }
}
