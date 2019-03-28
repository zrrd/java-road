package cn.learn.datastructure.collection.queue;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * 优先级队列(二叉堆)
 *
 * @author 邵益炯
 * @date 2018/9/21
 */
public class PriorityQueueTest {

  /**
   * 优先级队列是一颗完全二叉树 它的存储形式是数组的形式 左孩子 2i 右孩子 2i+1
   */
  public static void main(String[] args) {
    Comparator<Integer> comparator = (o1, o2) -> o2 - o1;
    //通过比较器构造
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(comparator);
    Random random = new Random();
    for (int i = 0; i < 10; i++) {
      int a = random.nextInt(100);
      System.out.println("插入" + a);
      priorityQueue.offer(a);
    }
    while (!priorityQueue.isEmpty()) {
      //删除元素并取出 每次拿出最小的元素
      int b = priorityQueue.poll();
      System.out.println(b);
    }
  }
}
