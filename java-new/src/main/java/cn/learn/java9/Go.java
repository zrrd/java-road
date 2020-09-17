package cn.learn.java9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author shaoyijiong
 * @date 2020/9/17
 */
public class Go {

  /**
   * 创建不可变类
   */
  private static void collection() {
    List<String> list = List.of("a");
    Set<String> set = Set.of("hello", "world");
    Map<String, String> map = Map.of("1", "a", "2", "b");

    // 将普通集合转化为不可变集合
    List<Object> list1 = Collections.unmodifiableList(new ArrayList<>());

  }

  private static void streamSupper() {
    Stream<Integer> stream = IntStream.of(1, 2, 3, 4, 5, 1).boxed();

    // takeWhile 处理所有小于3的流 直到不符合条件
    stream.takeWhile(i -> i < 3).forEach(System.out::println);
  }

  public static void main(String[] args) throws InterruptedException {
    streamSupper();
    TimeUnit.SECONDS.sleep(10);
  }

}
