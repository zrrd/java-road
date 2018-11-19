package cn.learn.utils.guava.Collections;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import java.util.List;

/**
 * 不可变集合
 *
 * @author 邵益炯
 * @date 2018/9/29
 */
public class ImmutableCollections {

  public static void main(String[] args) {
    ImmutableSet<String> strings = ImmutableSet.of(
        "red",
        "orange",
        "yellow",
        "green",
        "blue",
        "purple");
    List a = Lists.newArrayList();
    //构造
    ImmutableList<List> list = ImmutableList.of(a);
    //拷贝
    ImmutableList list1 = ImmutableList.copyOf(a);
    //加了排序功能
    ImmutableList list2 = ImmutableList.<String>sortedCopyOf(a);

  }
}
