package cn.learn.collection.map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.WeakHashMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author 邵益炯
 * @date 2018/9/20
 */
@RunWith(JUnit4.class)
public class MapTest {

  public void test1() {
    //初始化hashmap时可以设置初始容量和加载因子
    Map<String, String> map = new HashMap<>(12, 0.55f);
    //键集
    Set<String> stringSet = map.keySet();
    //值集
    Collection<String> values = map.values();
    //键值对集
    Set<Entry<String, String>> entries = map.entrySet();

    TreeMap<String, String> treeMap = new TreeMap<>();
    //正序集合
    NavigableSet<String> stringNavigableSet = treeMap.navigableKeySet();
    //反序集合
    NavigableSet<String> stringNavigableSet1 = treeMap.descendingKeySet();
  }

  @Test
  public void weakHashMapTest() {
    String w1 = "one";
    String w2 = "two";
    String w3 = "three";

    Map<String, String> weakHashMap = new WeakHashMap<>();
    weakHashMap.put(w3, "w3");

    weakHashMap.put(w1, "w1");
    weakHashMap.put(w2, "w2");
    System.out.println(weakHashMap.containsKey("one"));

    w1 = null;
    System.gc();

    System.out.println(weakHashMap.containsKey("one"));

  }

  @Test
  public void mapMethod() {
    Map<String, String> map = new HashMap<>();
    map.put("a", "a");

    //putIfAbsent 如过key不存在就放入 返回的是已存在的key对应的value 如果 key不存在返回null
    String a = map.putIfAbsent("a", "b");
    System.out.println(a);

    //computeIfAbsent 如果key不存在就计算后放入 返回的key对应的数据 List<String>
    //一般用于map中嵌套集合的初始化
    Map<String, List<String>> map1 = new HashMap<>();
    map1.computeIfAbsent("b", k -> new ArrayList<>()).add("c");
    System.out.println(map1);

    //computeIfPresent 如果key存在计算后放入 返回计算后的值
    List<String> b = map1.computeIfPresent("b", (k, v) -> {
      ArrayList<String> list = new ArrayList<>();
      list.add("l1");
      list.add("l2");
      return list;
    });
    System.out.println(b + " --- " + map1);

    //不管key是否存在
    map1.compute("b", (k, v) -> new ArrayList<>());
    System.out.println(map1);

    //不存在的化返回一个默认值
    List<String> c = map1.getOrDefault("c", new ArrayList<>());
  }
}
