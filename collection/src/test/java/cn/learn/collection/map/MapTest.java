package cn.learn.collection.map;

import java.util.Collection;
import java.util.HashMap;
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
    String w1 = new String("one");
    String w2 = new String("two");
    String w3 = new String("three");

    Map<String, String> weakHashMap = new WeakHashMap<>();
    weakHashMap.put(w1, "w1");
    weakHashMap.put(w2, "w2");
    weakHashMap.put(w3, "w3");

    System.out.println(weakHashMap.containsKey("one"));

    w1 = null;
    System.gc();

    System.out.println(weakHashMap.containsKey("one"));

  }
}
