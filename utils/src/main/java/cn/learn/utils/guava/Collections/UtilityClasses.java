package cn.learn.utils.guava.Collections;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.primitives.Ints;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 集合工具类
 *
 * @author 邵益炯
 * @date 2018/9/29
 */
public class UtilityClasses {

  @SuppressWarnings("all")
  private static void iterablesTest() {
    ImmutableList<String> list1 = ImmutableList.of("a", "v", "c", "d");
    ImmutableList<String> list2 = ImmutableList.of("a", "v", "c", "d");

    //返回惰性视图
    Iterable<Integer> concatenated = Iterables.concat(
        Ints.asList(1, 2, 3),
        Ints.asList(4, 5, 6));
    //返回a出现的次数
    Iterables.frequency(list1, "a");
    //拿第一个  为空 否则拿默认值
    Iterables.getFirst(list1, "a");
    Iterables.getLast(list1, "a");
    //Iterables.all();Iterables.any() 全部存在  或者存在一个 返回ture或false
    Iterables.all(list1, "a"::equals);
    //相同元素相同顺序返回ture
    Iterables.elementsEqual(list1, ImmutableList.of("a", "v", "c", "d"));
    //返回前i个元素
    Iterables.limit(list1, 2);

    //指定大小分隔
    Iterables.partition(list1, 2);

    //与Collection相似的方法
    Iterables.addAll(list1, list2);
    Iterables.contains(list1, "a");
    Iterables.removeAll(list1, list2);
    Iterables.retainAll(list1, list2);
    Iterables.size(list1);
    Iterables.toArray(list1, String.class);
    Iterables.isEmpty(list1);
    Iterables.get(list1, 2);
  }

  @SuppressWarnings("all")
  private static void listsTest() {
    ImmutableList<String> list1 = ImmutableList.of("a", "v", "c", "d");
    ImmutableList<String> list2 = ImmutableList.of("a", "v", "c", "d");

    List<Integer> countUp = Ints.asList(1, 2, 3, 4, 5);
    List<Integer> countDown = Lists.reverse(countUp); // {5, 4, 3, 2, 1}
    List<List<Integer>> parts = Lists.partition(countUp, 2); // {{1, 2}, {3, 4}, {5}}

    //ArrayList
    Lists.newArrayList();
    Lists.newCopyOnWriteArrayList();
    Lists.newArrayListWithCapacity(2);//带初始化的
    Lists.newArrayListWithExpectedSize(2);//预期
    //LinkList
    Lists.newLinkedList();

    Lists.asList("a", new String[]{"a", "b"});
    //求笛卡尔积
    Lists.cartesianProduct(list1, list2);
    //按字母分隔
    Lists.charactersOf("aaa");
    //倒序
    Lists.reverse(list1);
    //每个加个'a'
    Lists.transform(list1, s -> s + "a");

    //按照指定大小分隔
    Lists.partition(list1, 10);
    //与Collection相似的方法与上面的相同
  }

  @SuppressWarnings("all")
  private static void setsTest() {
    Set<String> set1 = ImmutableSet
        .of("one", "two", "three", "six", "seven", "eight");
    Set<String> set2 = ImmutableSet.of("two", "three", "five", "seven");

    //求并集
    Sets.union(set1, set2);
    //求交集
    Sets.intersection(set1, set2);
    //求相同的元素
    Sets.intersection(set1, set2);
    //求不同的元素
    Sets.difference(set1, set2); //只取set1不同元素
    Sets.symmetricDifference(set1, set2); //取set1与set2的不同元素

    //所有可能的交集
    Sets.cartesianProduct(set1, set2);
    //所有可能的子集
    Sets.powerSet(set1);

    //创建
    Sets.newHashSet();
    Sets.newConcurrentHashSet();
    Sets.newLinkedHashSet();
    Sets.newTreeSet();
    Sets.newCopyOnWriteArraySet();
    Sets.newHashSetWithExpectedSize(2);
    Sets.newLinkedHashSetWithExpectedSize(2);
  }

  @Data
  @AllArgsConstructor
  static class TestBean {

    private Integer id;
    private String name;



    public static List<TestBean> testBaenArray() {
      TestBean a = new TestBean(1,"a");
      TestBean b = new TestBean(2,"b");
      TestBean c = new TestBean(3,"c");
      TestBean d = new TestBean(4,"d");
      List<TestBean> list = new ArrayList<>();
      list.add(a);
      list.add(b);
      list.add(c);
      list.add(d);
      return list;
    }
  }

  @SuppressWarnings("all")
  private static void mapsTest() {
    //List<String> strings = ImmutableList.of("a", "b", "c");
    //可以通过长度查询对应的单词  注意泛型的顺序
    //ImmutableMap<Integer, String> stringsByIndex = Maps.uniqueIndex(strings, String::length);

    Map<String, Integer> map1 = ImmutableMap.of("a", 1, "b", 2, "c", 3);
    Map<String, Integer> map2 = ImmutableMap.of("b", 2, "c", 4, "d", 5);

    //相同键不同值
    Maps.difference(map1, map2).entriesDiffering(); // {"c" => (3, 4)}
    //相同键和值
    Maps.difference(map1, map2).entriesInCommon(); // {"b" => 2}
    //返回其键位于左侧但不在右侧映射中的条目。
    Maps.difference(map1, map2).entriesOnlyOnLeft(); // {"a" => 1}
    //返回其键位于右侧但不在左侧映射中的条目。
    Maps.difference(map1, map2).entriesOnlyOnRight(); // {"d" => 5}

    List<TestBean> list = TestBean.testBaenArray();
    //把id作为key 对象作为value
    ImmutableMap<Integer, TestBean> map = Maps
        .uniqueIndex(list, TestBean::getId);



  }

  private static void multisetsTest() {
    //Multisets的很多方法
  }

  public static void main(String[] args) {
    mapsTest();
  }
}
