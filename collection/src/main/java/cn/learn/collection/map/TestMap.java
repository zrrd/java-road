package cn.learn.collection.map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

/**
 * map的使用
 *
 * @author 邵益炯
 * @date 2018/8/15
 */
public class TestMap {

  public static void main(String[] args) {
    Map<String, String> map = new HashMap<>(10);
    if (map.containsKey("a")) {
      System.out.println(map.get("a"));
    }

    //map的三种迭代器
    //键
    Set<String> keySet = map.keySet();
    //键值对
    Set<Entry<String, String>> entrySet = map.entrySet();
    //值
    Collection<String> values = map.values();

    //通过红黑数实现
    TreeMap<String,String> treeMap = new TreeMap<>();
  }
}
