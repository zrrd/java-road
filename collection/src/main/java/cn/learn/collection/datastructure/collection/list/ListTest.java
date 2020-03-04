package cn.learn.collection.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import org.springframework.util.StopWatch;

/**
 * @author 邵益炯
 * @date 2018/8/9
 */
public class ListTest {

  public static void main(String[] args) {
    List<Integer> list = new ArrayList<>();
    Random random = new Random();
    for (int i = 0; i < 100000; i++) {
      list.add(random.nextInt(100000));
    }
    List<Integer> linkList = new LinkedList<>(list);
    List<Integer> arrayList = new ArrayList<>(list);
    //移出偶数
    StopWatch stopWatch = new StopWatch();
    //linkList 移出的所需时间(线性)
    stopWatch.start("链表");
    removeEvent(linkList);
    stopWatch.stop();
    System.out.println(stopWatch.getLastTaskName() + stopWatch.getLastTaskTimeMillis());
    //arrayList 移出的所需时间(二次)
    stopWatch.start("数组");
    removeEvent(arrayList);
    stopWatch.stop();
    System.out.println(stopWatch.getLastTaskName() + stopWatch.getLastTaskTimeMillis());

  }

  private static void removeEvent(List<Integer> list) {
    Iterator<Integer> iterator = list.iterator();

    //listIterator 相比普通iterator多了向前和增加功能
    ListIterator<Integer> listIterator = list.listIterator();
    //这里不能用增强for循环  在增强for循环中增加删除数组会抛出异常
    while (iterator.hasNext()) {
      if (iterator.next() % 2 == 0) {
        iterator.remove();
      }
    }
  }
}
