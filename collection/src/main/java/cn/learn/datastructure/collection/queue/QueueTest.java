package cn.learn.datastructure.collection.queue;

import com.sun.jmx.remote.internal.ArrayQueue;
import java.util.ArrayDeque;

/**
 * @author shaoyijiong
 * @date 2019/3/28
 */
public class QueueTest {

  public static void main(String[] args) {
    //数组实现 循环利用
    ArrayQueue<String> arrayQueue = new ArrayQueue<>(10);
    ArrayDeque<String> arrayDeque = new ArrayDeque<>();
  }
}
