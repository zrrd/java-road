package cn.learn.utils.guava.collections;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
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
    // 通过构造器新建
    ImmutableList.builder().add("a").addAll(new ArrayList<>()).build();
    List<String> a = Lists.newArrayList();
    String item = "hello";
    //构造
    ImmutableList<String> list = ImmutableList.of(item);
    //拷贝 copy方法时完全拷贝一个新的对象方法 下面还是引用的旧对象(无法改变的视图) 增减集合时抛出异常
    ImmutableList<String> list1 = ImmutableList.copyOf(strings);
    // 转换成不可变集合
    strings.asList();
    List<String> list3 = Collections.unmodifiableList(a);
    //加了排序功能
    ImmutableList<String> list2 = ImmutableList.sortedCopyOf(a);

  }
}
