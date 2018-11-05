package cn.learn.java8.lambda;

import java.util.HashMap;
import java.util.Map;

/**
 * map的新方法
 *
 * @author 邵益炯
 * @date 2018/11/5
 */
public class MapMethod {

  @SuppressWarnings("all")
  public static void main(String[] args) {
    Map<Integer, String> map = new HashMap<>(10);
    for (int i = 0; i < 10; i++) {
      //如果传入key对应的value已经存在，就返回存在的value，不进行替换。如果不存在，就添加key和value，返回null
      map.putIfAbsent(i, "val" + i);
    }

    //存在key value变成计算后的结果 返回value  否则返回null
    map.computeIfPresent(3, (key, val) -> val + key);
    map.get(3);     // val33

    //变成null后key也移出了
    map.computeIfPresent(9, (key, val) -> null);
    map.containsKey(9);     // false

    //原来不存在 新建一个key value 注意参数只有key
    map.computeIfAbsent(23, key -> "val" + key);
    map.containsKey(23);     // true

    //原来存在 不修改
    map.computeIfAbsent(3, key -> "bam");
    map.get(3);     // val33

    //返回是否修改成功
    map.remove(3, "val3");
    map.get(3);             // val33

    map.remove(3, "val33");
    map.get(3);   // null

    map.getOrDefault(42, "not found");  // not found

    //合并操作先看map中是否没有特定的key/value存在，如果是，则把key/value存入map，否则merging函数就会被调用，对现有的数值进行修改
    map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
    map.get(9);             // val9

    map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
    map.get(9);             // val9concat
  }
}
