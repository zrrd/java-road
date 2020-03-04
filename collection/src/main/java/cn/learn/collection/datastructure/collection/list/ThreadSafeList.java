package cn.learn.collection.datastructure.collection.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 线程安全的列表
 *
 * @author 邵益炯
 * @date 2018/11/9
 */
public class ThreadSafeList {

  /**
   * 通过在每个方法申明处添加synchronized关键字来实现线程安全
   */
  private static Vector<String> vector = new Vector<>();
  /**
   * 通过在每个方法内部添加synchronized关键字来实现线程安全
   */
  private static List<String> list = Collections.synchronizedList(new ArrayList<>());
  /**
   * <p>如果有多个调用者（callers）同时请求相同资源（如内存或磁盘上的数据存储），他们会共同获取相同的指针指向相同的资源,</p>
   * <p>直到某个调用者试图修改资源的内容时，系统才会真正复制一份专用副本（private copy）给该调用者,</p>
   * <p>而其他调用者所见到的最初的资源仍然保持不变。优点是如果调用者没有修改该资源，就不会有副本（private copy）被建立，</p>
   * 因此多个调用者只是读取操作时可以共享同一份资源。
   */
  private static CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();

  public static void main(String[] args) {
    //在遍历vector时如果有其他线程修改了vector长度会抛出异常

    //copyOnWriteArrayList
    //在修改时，复制出一个新数组，修改的操作在新数组中完成，最后将新数组交由array变量指向。
    //写加锁，读不加锁

    //在迭代copyOnWriteArrayList时只是迭代的原来的数组 如果有另一个线程修改了原来的数组是不会同步的
  }

}
