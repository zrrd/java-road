package cn.learn.collection.datastructure.collection.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * list 循环删除
 *
 * @author shaoyijiong
 * @date 2019/8/26
 */
public class RemoveList {

  static List<String> list = new ArrayList<>();

  static {
    list.add("a");
    list.add("b");
    list.add("b");
    list.add("c");
    list.add("d");
    list.add("e");
  }

  /**
   * 抛出 ConcurrentModificationException 异常
   */
  @Test
  void test1() {
    //modCount != expectedModCount
    //modCount 是list类的修改次数 expectedModCount 是Iterator类的修改次数 当删除时会检查 两个修改次数是否相等
    //是为了防止多线程 a 线程调用Iterator的修改 b 线程调用List的修改 导致数据不一致问题 fail-fast
    for (String s : list) {
      if (s.equals("b")) {
        list.remove(s);
      }
    }
    System.out.println(list);
  }


  @Test
  void test2() {
    for (int i = 0; i < list.size(); i++) {
      System.out.println("i:" + i + " size:" + list.size());
      if (list.get(i).equals("b")) {
        // 需要对 i 进行自减 否则可能导致少删
        // 调用 list 的remove后 , 后面的元素都会往前一位
        list.remove(i--);
        //list.remove(i);
      }
    }
    System.out.println(list);
  }

  @Test
  void test3() {
    // 通过iterator remove 可以简化为 list.removeIf(next -> next.equals("b"));
    Iterator<String> iterator = list.iterator();
    while (iterator.hasNext()) {
      String next = iterator.next();
      if (next.equals("b")) {
        iterator.remove();
      }
    }
    System.out.println(list);
  }

}
