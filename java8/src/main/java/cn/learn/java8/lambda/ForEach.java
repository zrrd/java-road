package cn.learn.java8.lambda;

import com.google.common.collect.ImmutableMap;
import java.util.Iterator;
import java.util.Map;
import org.springframework.util.StopWatch;

/**
 * 使用java8中的forEach遍历接口
 *
 * @author 邵益炯
 */
public class ForEach {

  private static void forEach() {
    Map<String, Integer> map = ImmutableMap.of("a", 1, "b", 2, "c", 3, "d", 4, "e", 6);

    StopWatch sw = new StopWatch();

    sw.start("通过entrySet遍历");
    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      System.out.println(entry.getKey() + entry.getValue());
    }
    sw.stop();

    sw.start("通过Iterator接口遍历");
    Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
    while (iterator.hasNext()) {
      Map.Entry<String, Integer> entry = iterator.next();
      System.out.println(entry.getKey() + entry.getValue());
    }
    sw.stop();

    sw.start("遍历keySet 通过key 取值");
    for (String key : map.keySet()) {
      Integer value = map.get(key);
      System.out.println(key + value);
    }
    sw.stop();

    sw.start("foreach+lambda表达式遍历");
    map.forEach((k, v) -> {
      System.out.println(k + v);
    });
    sw.stop();

    System.out.println(sw.prettyPrint());
  }

  public static void main(String[] args) {
    forEach();
  }
}
